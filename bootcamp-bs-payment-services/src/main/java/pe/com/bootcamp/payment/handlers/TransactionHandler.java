package pe.com.bootcamp.payment.handlers;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.bootcamp.payment.entities.Transaction;
import pe.com.bootcamp.payment.services.TransactionService;
import reactor.core.publisher.Mono;

@Component
public class TransactionHandler {

	@Autowired
	private TransactionService transactionService;

	public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
		return this.transactionService.findAll().collectList()
				.flatMap(s -> ServerResponse.ok().body(Mono.just(s), Transaction.class));
	}

	public Mono<ServerResponse> save(ServerRequest serverRequest) {
		Mono<Transaction> transactionRequest = serverRequest.bodyToMono(Transaction.class);
		return transactionRequest.flatMap(tx -> this.transactionService.saveWithValidation(tx))
				.flatMap(a -> ServerResponse.ok().contentType(APPLICATION_JSON).body(Mono.just(a), Transaction.class));

	}

}
