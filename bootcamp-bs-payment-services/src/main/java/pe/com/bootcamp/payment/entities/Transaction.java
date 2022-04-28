package pe.com.bootcamp.payment.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Transaction {

	@Id
    private Integer id;
	private String productCode;
	private String supplyNumber;
	private BigDecimal amount;
	private LocalDateTime transactionDate;
}
