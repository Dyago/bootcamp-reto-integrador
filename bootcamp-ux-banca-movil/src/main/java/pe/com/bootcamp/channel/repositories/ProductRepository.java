package pe.com.bootcamp.channel.repositories;

import pe.com.bootcamp.channel.entities.Product;
import reactor.core.publisher.Flux;

public interface ProductRepository {

	Flux<Product> findByChannelCode(String channelCode);
	
}
