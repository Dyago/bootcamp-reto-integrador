package pe.com.bootcamp.channel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pe.com.bootcamp.channel.entities.Product;
import pe.com.bootcamp.channel.repositories.ProductRepository;
import pe.com.bootcamp.channel.services.ProductService;
import reactor.core.publisher.Flux;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Value("${application.channel-code}") 
	private String channelCode;
	
	@Override
	public Flux<Product> findAll() {
		return productRepository.findByChannelCode(channelCode);
	}
}
