package entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {
	private int id;
	private int userId;
	private Date submitDate;
	private double total;
	
	public Order(int userId, Date submitDate, double total) {
		super();
		this.userId = userId;
		this.submitDate = submitDate;
		this.total = total;
	}
	
	
	}
	
	

