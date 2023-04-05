package model;

public class Product {
	
	private String id;
	private String type;
	private String brand;
	private Integer price;
	private Integer qty;
	
	public Product(String id, String type, String brand, Integer price, Integer qty) {
		super();
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.price = price;
		this.qty = qty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
	
	
}

