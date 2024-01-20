package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	private int id;
	private String name;
	private int price;
	private String imgName;
	private boolean is_new;
	private int quantity;
	private String description;
	
	
	public boolean getIs_new() {
		return is_new;
	}
	
	public void setIs_New(boolean is_new) {
		this.is_new = is_new;
	}

}
