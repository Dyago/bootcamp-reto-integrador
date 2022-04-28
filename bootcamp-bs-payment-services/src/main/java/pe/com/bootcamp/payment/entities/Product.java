package pe.com.bootcamp.payment.entities;

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
public class Product {
	
	@Id
    private Integer id;
    private String productCode;
    private String productName;
    private String channelCode;
}
