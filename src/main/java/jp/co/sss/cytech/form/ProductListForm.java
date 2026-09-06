package jp.co.sss.cytech.form;

public class ProductListForm {
	
	private Integer productId;
    
    private String imagePath;
    
	private String productName;
    
	private String companyName;

    private Integer price;

    private Integer includeTax;
    
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

    
    
}