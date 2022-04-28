package pe.com.bootcamp.payment.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.bootcamp.payment.entities.Product;
import pe.com.bootcamp.payment.repositories.ProductRepository;
import pe.com.bootcamp.payment.services.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Flux<Product> findByChannel(String channelCode) {
		return productRepository.findByChannelCode(channelCode);
	}
	
	@Override
	public Mono<Product> findById(Integer id) {
		return productRepository.findById(id);
	}
}
