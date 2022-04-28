package pe.com.bootcamp.payment.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import pe.com.bootcamp.payment.entities.Transaction;
import pe.com.bootcamp.payment.exception.BusinessException;
import pe.com.bootcamp.payment.repositories.ProductRepository;
import pe.com.bootcamp.payment.repositories.TransactionRepository;
import pe.com.bootcamp.payment.services.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Mono<Transaction> saveWithValidation(Transaction tx) {
		
		return productRepository.findByProductCode(tx.getProductCode())
    			.switchIfEmpty(Mono.error(new BusinessException(HttpStatus.BAD_REQUEST,
						"El codigo de producto ingresado no se encuentra registrado")))
    	.flatMap(a -> {
    		tx.setTransactionDate(LocalDateTime.now());
    		return transactionRepository.save(tx);
    	});
		
	}

	@Override
	public Flux<Transaction> findAll() {
		return transactionRepository.findAll();
	}

}
