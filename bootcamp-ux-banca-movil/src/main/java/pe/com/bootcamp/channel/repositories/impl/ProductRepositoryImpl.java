package pe.com.bootcamp.channel.repositories.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import pe.com.bootcamp.channel.entities.Product;
import pe.com.bootcamp.channel.repositories.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

@Slf4j
@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final WebClient client;

	public ProductRepositoryImpl(WebClient.Builder builder,
			@Value("${application.http-client.bs-payment:http://localhost:8080/products}") String urlApiBsPayment) {
		log.info("urlApiAuthors = " + urlApiBsPayment);

		/*
		 *  Configurar Response timeout
		 */
		HttpClient client = HttpClient.create().responseTimeout(Duration.ofSeconds(5));
		this.client = builder.baseUrl(urlApiBsPayment).clientConnector(new ReactorClientHttpConnector(client)).build();
	}

	@Override
	public Flux<Product> findByChannelCode(String channelCode) {
		return this.client.get().uri("/channel/{channelCode}", channelCode).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new RuntimeException("Server error")))
                .bodyToFlux(Product.class)
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(2))
                                .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
                                .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
                )
                .doOnError(x-> log.info("LOG ERROR"))
                .doOnSubscribe(x-> log.info("LOG SUCCESS"));
	}
}
