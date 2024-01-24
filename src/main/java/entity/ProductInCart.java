package entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductInCart {
	private Product product;
	private int quantity;
	private double subTotalPrice;
	


}
