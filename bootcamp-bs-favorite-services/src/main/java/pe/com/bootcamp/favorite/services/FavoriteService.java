package pe.com.bootcamp.favorite.services;

import pe.com.bootcamp.favorite.entities.Favorite;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavoriteService {

	Mono<Favorite> save(Favorite favorite);
	Flux<Favorite> findAll();
}
