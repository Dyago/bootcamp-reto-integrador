package pe.com.bootcamp.payment.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import pe.com.bootcamp.payment.entities.Transaction;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Integer> {

}
