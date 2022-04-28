package pe.com.bootcamp.favorite.routes;

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

import pe.com.bootcamp.favorite.handlers.FavoriteHandler;

@Configuration
public class RouterConfiguration {

	@Bean
	public RouterFunction<ServerResponse> transactionRoutes(FavoriteHandler favoriteHandler) {
		return RouterFunctions.nest(RequestPredicates.path("/favorites"),
				RouterFunctions.route(POST("").and(contentType(MediaType.APPLICATION_JSON)), favoriteHandler::save)
				.andRoute(GET(""), favoriteHandler::findAll));
	}

}
