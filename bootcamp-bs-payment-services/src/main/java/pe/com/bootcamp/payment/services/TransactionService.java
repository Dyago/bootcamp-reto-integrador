package pe.com.bootcamp.payment.services;

import pe.com.bootcamp.payment.entities.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

	Mono<Transaction> saveWithValidation(Transaction tx);

	Flux<Transaction> findAll();

}
