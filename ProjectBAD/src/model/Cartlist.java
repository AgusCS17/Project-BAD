package model;

public class Cartlist {
	
	private String productid;
	private String brand;
	private Integer qty;
	private Integer price;
	private Integer total;
	
	public Cartlist(String productid, String brand, Integer qty, Integer price, Integer total) {
		super();
		this.productid = productid;
		this.brand = brand;
		this.qty = qty;
		this.price = price;
		this.total = total;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
	
}
