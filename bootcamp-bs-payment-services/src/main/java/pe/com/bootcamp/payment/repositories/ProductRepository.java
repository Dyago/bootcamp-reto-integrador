package pe.com.bootcamp.payment.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.bootcamp.payment.entities.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

	Flux<Product> findByChannelCode(String channelCode);

	Mono<Product> findByProductCode(String productCode);

}
