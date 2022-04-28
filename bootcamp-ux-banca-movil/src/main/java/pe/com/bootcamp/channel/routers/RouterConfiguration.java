package pe.com.bootcamp.channel.routers;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bootcamp.channel.handlers.ProductHandler;

@Configuration
public class RouterConfiguration {

	@Bean
	public RouterFunction<ServerResponse> productRoutes(ProductHandler productHandler) {
		return RouterFunctions.nest(RequestPredicates.path("/products"),
				RouterFunctions.route(GET(""), productHandler::findAll));
	}


}
