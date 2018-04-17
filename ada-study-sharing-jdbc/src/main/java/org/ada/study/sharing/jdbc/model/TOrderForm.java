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
 * 订单记录表
 * </p>
 *
 * @author czd
 * @since 2018-04-17
 */
@TableName("t_order_form")
public class TOrderForm extends Model<TOrderForm> {

    private static final long serialVersionUID = 1L;

    /**
     *  订单编号
     */
	@TableField("order_no")
	private Long orderNo;
    /**
     * 城市id
     */
	@TableField("city_id")
	private Long cityId;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 下单时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 用户地址
     */
	@TableField("user_address")
	private String userAddress;
    /**
     * 地址经纬度
     */
	@TableField("lon_and_lat")
	private String lonAndLat;
    /**
     * 0否1是
     */
	@TableField("multiple_payment")
	private Integer multiplePayment;
    /**
     * 余款
     */
	@TableField("balance_amount")
	private BigDecimal balanceAmount;
    /**
     * 1竞单2待服务3取消4超时5服务完成6结束
     */
	@TableField("order_status")
	private Integer orderStatus;
    /**
     * 0待支付1已支付
     */
	@TableField("pay_status")
	private Integer payStatus;
    /**
     * 产品大类
     */
	@TableField("product_type")
	private String productType;
    /**
     * 产品小类
     */
	@TableField("product_sub_typte")
	private String productSubTypte;
    /**
     * 联系人
     */
	@TableField("link_user")
	private String linkUser;
    /**
     * 联系电话
     */
	@TableField("link_phone")
	private String linkPhone;
    /**
     * 用户服务要求
     */
	@TableField("user_service_require")
	private String userServiceRequire;
    /**
     * 取消时间
     */
	@TableField("canel_time")
	private Date canelTime;
    /**
     * 支付时间
     */
	@TableField("pay_time")
	private Date payTime;
    /**
     * 预计结束时间
     */
	@TableField("end_time")
	private Date endTime;
    /**
     * 订单金额
     */
	@TableField("order_amount")
	private BigDecimal orderAmount;
    /**
     * 增值服务金额
     */
	@TableField("increment_amount")
	private BigDecimal incrementAmount;
    /**
     * 增值服务金额+订单金额
     */
	@TableField("total_amount")
	private BigDecimal totalAmount;
    /**
     * 优惠金额
     */
	@TableField("discount_amount")
	private BigDecimal discountAmount;
    /**
     * 支付方式
     */
	@TableField("pay_amount")
	private Integer payAmount;
    /**
     * 实际支付金额
     */
	@TableField("actual_pay_amount")
	private BigDecimal actualPayAmount;
    /**
     * 0未结算1已结算
     */
	@TableField("settlement_flag")
	private Integer settlementFlag;
    /**
     * 对应字典
     */
	@TableField("source_code")
	private String sourceCode;
    /**
     * 来源用户id
     */
	@TableField("source_user_id")
	private Long sourceUserId;
    /**
     * 0系统派单1人工派单
     */
	@TableField("dispatch_flag")
	private Integer dispatchFlag;
    /**
     * 0客户下单1代下单
     */
	@TableField("order_flag")
	private Integer orderFlag;
    /**
     * 0否1是
     */
	@TableField("is_delete")
	private Integer isDelete;
    /**
     * 备注
     */
	private String remark;


	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getLonAndLat() {
		return lonAndLat;
	}

	public void setLonAndLat(String lonAndLat) {
		this.lonAndLat = lonAndLat;
	}

	public Integer getMultiplePayment() {
		return multiplePayment;
	}

	public void setMultiplePayment(Integer multiplePayment) {
		this.multiplePayment = multiplePayment;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductSubTypte() {
		return productSubTypte;
	}

	public void setProductSubTypte(String productSubTypte) {
		this.productSubTypte = productSubTypte;
	}

	public String getLinkUser() {
		return linkUser;
	}

	public void setLinkUser(String linkUser) {
		this.linkUser = linkUser;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getUserServiceRequire() {
		return userServiceRequire;
	}

	public void setUserServiceRequire(String userServiceRequire) {
		this.userServiceRequire = userServiceRequire;
	}

	public Date getCanelTime() {
		return canelTime;
	}

	public void setCanelTime(Date canelTime) {
		this.canelTime = canelTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getIncrementAmount() {
		return incrementAmount;
	}

	public void setIncrementAmount(BigDecimal incrementAmount) {
		this.incrementAmount = incrementAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Integer getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getActualPayAmount() {
		return actualPayAmount;
	}

	public void setActualPayAmount(BigDecimal actualPayAmount) {
		this.actualPayAmount = actualPayAmount;
	}

	public Integer getSettlementFlag() {
		return settlementFlag;
	}

	public void setSettlementFlag(Integer settlementFlag) {
		this.settlementFlag = settlementFlag;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public Integer getDispatchFlag() {
		return dispatchFlag;
	}

	public void setDispatchFlag(Integer dispatchFlag) {
		this.dispatchFlag = dispatchFlag;
	}

	public Integer getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(Integer orderFlag) {
		this.orderFlag = orderFlag;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.orderNo;
	}
	 @Version
	    private Integer version;
	@Override
	public String toString() {
		return "TOrderForm{" +
			"version=" + version +
			", orderNo=" + orderNo +
			", cityId=" + cityId +
			", userId=" + userId +
			", createTime=" + createTime +
			", userAddress=" + userAddress +
			", lonAndLat=" + lonAndLat +
			", multiplePayment=" + multiplePayment +
			", balanceAmount=" + balanceAmount +
			", orderStatus=" + orderStatus +
			", payStatus=" + payStatus +
			", productType=" + productType +
			", productSubTypte=" + productSubTypte +
			", linkUser=" + linkUser +
			", linkPhone=" + linkPhone +
			", userServiceRequire=" + userServiceRequire +
			", canelTime=" + canelTime +
			", payTime=" + payTime +
			", endTime=" + endTime +
			", orderAmount=" + orderAmount +
			", incrementAmount=" + incrementAmount +
			", totalAmount=" + totalAmount +
			", discountAmount=" + discountAmount +
			", payAmount=" + payAmount +
			", actualPayAmount=" + actualPayAmount +
			", settlementFlag=" + settlementFlag +
			", sourceCode=" + sourceCode +
			", sourceUserId=" + sourceUserId +
			", dispatchFlag=" + dispatchFlag +
			", orderFlag=" + orderFlag +
			", isDelete=" + isDelete +
			", remark=" + remark +
			"}";
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
