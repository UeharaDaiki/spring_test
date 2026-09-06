package jp.co.sss.cytech.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_items")
public class SalesItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer saleItemId;
	@Column
	private Integer productId;
	@Column
	private Integer companyId;
	@Column
	private String saleName;
	@Column
	private String description;
	@Column
	private Integer discountRate;
	@Column
	private String salesImgPath;
	@Column
	private Date startMonth;
	@Column
	private Date endMonth;
	
	public Integer getSaleItemId() {
		return saleItemId;
	}
	public void setSaleItemId(Integer saleItemId) {
		this.saleItemId = saleItemId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
	public String getSalesImgPath() {
		return salesImgPath;
	}
	public void setSalesImgPath(String salesImgPath) {
		this.salesImgPath = salesImgPath;
	}
	public Date getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(Date startMonth) {
		this.startMonth = startMonth;
	}
	public Date getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(Date endMonth) {
		this.endMonth = endMonth;
	}


}
