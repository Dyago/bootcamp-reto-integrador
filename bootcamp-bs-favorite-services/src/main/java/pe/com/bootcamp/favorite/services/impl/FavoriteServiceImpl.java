package pe.com.bootcamp.favorite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bootcamp.favorite.entities.Favorite;
import pe.com.bootcamp.favorite.repositories.FavoriteRepository;
import pe.com.bootcamp.favorite.services.FavoriteService;
import reactor.core.publisher.Mono;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Override
	public Mono<Favorite> save(Favorite favorite) {
		return favoriteRepository.save(favorite);
	}
}
