package pe.com.bootcamp.payment.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bootcamp.payment.entities.Product;
import pe.com.bootcamp.payment.services.ProductService;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
	
	@Autowired
	private ProductService productService;

	public Mono<ServerResponse> findByChannel(ServerRequest serverRequest) {
        String channel =serverRequest.pathVariable("channelCode");
        return this.productService.findByChannel(channel)
                .collectList()
                .flatMap(s-> ServerResponse.ok().body(Mono.just(s), Product.class)) ;
    }
	
	public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String id =serverRequest.pathVariable("id");
        return this.productService.findById(Integer.parseInt(id))
                .flatMap(s-> ServerResponse.ok().body(Mono.just(s), Product.class)) ;
    }
	
}
