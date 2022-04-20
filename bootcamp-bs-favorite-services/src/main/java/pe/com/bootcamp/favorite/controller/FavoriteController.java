package pe.com.bootcamp.favorite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.bootcamp.favorite.entities.Favorite;
import pe.com.bootcamp.favorite.services.FavoriteService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "favorite")
public class FavoriteController {
	
	@Autowired
	private FavoriteService favoriteService;

	@PostMapping
	public Mono<Favorite> save(@RequestBody Favorite favorite) {
		return favoriteService.save(favorite);
	}

}
