package pe.com.bootcamp.payment.routes;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bootcamp.payment.handlers.ProductHandler;
import pe.com.bootcamp.payment.handlers.TransactionHandler;

@Configuration
public class RouterConfiguration {

	@Bean
	public RouterFunction<ServerResponse> productRoutes(ProductHandler productHandler) {
		return RouterFunctions.nest(RequestPredicates.path("/products"),
				RouterFunctions.route(GET("/{id}"), productHandler::findById).andRoute(GET("/channel/{channelCode}"),
						productHandler::findByChannel));
	}

	@Bean
	public RouterFunction<ServerResponse> transactionRoutes(TransactionHandler transactionHandler) {
		return RouterFunctions.nest(RequestPredicates.path("/transactions"),
				RouterFunctions.route(POST("").and(contentType(MediaType.APPLICATION_JSON)), transactionHandler::save)
				.andRoute(GET(""), transactionHandler::findAll));
	}

}
