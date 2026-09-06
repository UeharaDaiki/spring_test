package jp.co.sss.cytech.form;

public class CartDetailForm {
    
	private String productName;
    
	private Integer quantity;

    private Integer price;

    private Integer includeTax;
    
    private String imagePath;
    
    private Integer stock;
    
    private Integer totalPrice;
    
    private Integer totalIncludeTax;
    
    private Integer cartId;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getIncludeTax() {
		return includeTax;
	}

	public void setIncludeTax(Integer includeTax) {
		this.includeTax = includeTax;
	}
	
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getTotalIncludeTax() {
		return totalIncludeTax;
	}

	public void setTotalIncludeTax(Integer totalIncludeTax) {
		this.totalIncludeTax = totalIncludeTax;
	}
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
    
    
}