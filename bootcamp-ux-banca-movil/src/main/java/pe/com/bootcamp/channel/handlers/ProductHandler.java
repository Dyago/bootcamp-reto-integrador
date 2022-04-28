package pe.com.bootcamp.channel.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bootcamp.channel.entities.Product;
import pe.com.bootcamp.channel.services.ProductService;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
	
	@Autowired
	private ProductService productService;

	public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return this.productService.findAll()
                .collectList()
                .flatMap(s-> ServerResponse.ok().body(Mono.just(s), Product.class)) ;
    }
	
}
