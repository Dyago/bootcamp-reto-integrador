package pe.com.bootcamp.channel.services;

import pe.com.bootcamp.channel.entities.Product;
import reactor.core.publisher.Flux;

public interface ProductService {
	Flux<Product> findAll();
}
