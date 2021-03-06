package pe.com.bootcamp.payment.exception;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

	private ObjectMapper objectMapper;

	public GlobalExceptionHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

		DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();

		if (ex instanceof BusinessException) {
			var internalException = (BusinessException) ex;
			exchange.getResponse().setStatusCode(internalException.getHttpStatus());
			DataBuffer dataBuffer = null;
			try {
				dataBuffer = bufferFactory
						.wrap(objectMapper.writeValueAsBytes(new HttpError(internalException.getMessage())));
			} catch (JsonProcessingException e) {
				dataBuffer = bufferFactory.wrap("".getBytes());
			}
			exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
			return exchange.getResponse().writeWith(Mono.just(dataBuffer));

		}

		exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		DataBuffer dataBuffer = bufferFactory.wrap("Unknown error".getBytes());
		return exchange.getResponse().writeWith(Mono.just(dataBuffer));
	}

	public class HttpError {

		private String message;

		HttpError(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}
}