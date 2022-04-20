package pe.com.bootcamp.favorite.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import pe.com.bootcamp.favorite.entities.Favorite;

public interface FavoriteRepository extends ReactiveMongoRepository<Favorite, Long> {

}
