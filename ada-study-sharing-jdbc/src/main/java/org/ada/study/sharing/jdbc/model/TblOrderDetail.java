package org.ada.study.sharing.jdbc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;

/**
 * <p>
 * 
 * </p>
 *
 * @author czd
 * @since 2018-04-17
 */
@TableName("tbl_order_detail")
public class TblOrderDetail extends Model<TblOrderDetail> {

    private static final long serialVersionUID = 1L;
    @Version
    private Integer version;
	@TableField("order_no")
	private Long orderNo;
	@TableField("product_no")
	private Long productNo;
	@TableField("product_sku_no")
	private Long productSkuNo;
	@TableField("product_name")
	private String productName;
	@TableField("city_id")
	private Long cityId;
	@TableField("subscribe_time")
	private Date subscribeTime;
	@TableField("product_num")
	private BigDecimal productNum;
    /**
     * 小时，平米，个，台
     */
	@TableField("product_unit")
	private String productUnit;
	private BigDecimal price;
	@TableField("increment_amount")
	private BigDecimal incrementAmount;
	@TableField("discount_amount")
	private BigDecimal discountAmount;
	@TableField("actual_pay_amount")
	private BigDecimal actualPayAmount;
	@TableField("order_status")
	private Integer orderStatus;

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getProductNo() {
		return productNo;
	}

	public void setProductNo(Long productNo) {
		this.productNo = productNo;
	}

	public Long getProductSkuNo() {
		return productSkuNo;
	}

	public void setProductSkuNo(Long productSkuNo) {
		this.productSkuNo = productSkuNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Date getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public BigDecimal getProductNum() {
		return productNum;
	}

	public void setProductNum(BigDecimal productNum) {
		this.productNum = productNum;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getIncrementAmount() {
		return incrementAmount;
	}

	public void setIncrementAmount(BigDecimal incrementAmount) {
		this.incrementAmount = incrementAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getActualPayAmount() {
		return actualPayAmount;
	}

	public void setActualPayAmount(BigDecimal actualPayAmount) {
		this.actualPayAmount = actualPayAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	protected Serializable pkVal() {
		return this.orderNo;
	}

	@Override
	public String toString() {
		return "TblOrderDetail{" +
			" version=" + version +
			", orderNo=" + orderNo +
			", productNo=" + productNo +
			", productSkuNo=" + productSkuNo +
			", productName=" + productName +
			", cityId=" + cityId +
			", subscribeTime=" + subscribeTime +
			", productNum=" + productNum +
			", productUnit=" + productUnit +
			", price=" + price +
			", incrementAmount=" + incrementAmount +
			", discountAmount=" + discountAmount +
			", actualPayAmount=" + actualPayAmount +
			", orderStatus=" + orderStatus +
			"}";
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
