package pe.com.bootcamp.favorite.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bootcamp.favorite.entities.Favorite;
import pe.com.bootcamp.favorite.services.FavoriteService;
import reactor.core.publisher.Mono;

@Component
public class FavoriteHandler {

	@Autowired
	private FavoriteService favoriteService;

	public Mono<ServerResponse> save(ServerRequest serverRequest) {
		Mono<Favorite> favorite = serverRequest.bodyToMono(Favorite.class);
		return favorite.flatMap(tx -> this.favoriteService.save(tx)).flatMap(
				a -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(a), Favorite.class));
	}
	
	public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
		return this.favoriteService.findAll().collectList()
				.flatMap(s -> ServerResponse.ok().body(Mono.just(s), Favorite.class));
	}

}
