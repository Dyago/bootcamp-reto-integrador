package pe.com.bootcamp.payment.services;

import pe.com.bootcamp.payment.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Flux<Product> findByChannel(String channel);
	Mono<Product> findById(Integer id);
}
