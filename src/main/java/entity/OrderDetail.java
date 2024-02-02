package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetail {
	
private int id;
private int orderId;
private int productId;
private int quantity;
private double price;
private double subTotal;

}
