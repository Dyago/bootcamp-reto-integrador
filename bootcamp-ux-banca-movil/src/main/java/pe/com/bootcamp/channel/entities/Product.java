package pe.com.bootcamp.channel.entities;

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
public class Product {
	
    private Integer id;
    private String productCode;
    private String productName;
    private String channelCode;
}
