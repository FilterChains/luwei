/*
package com.luwei.entity;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TdOrder{

	private Integer id;
    private String orderNo;

    private Integer orderType;

    private Integer userId;
    */
/**
     * 下单药店ID
     *//*

    private Integer uaid;
	*/
/**
	 * 供应商ID
	 *//*

	private Integer supplierId;
    */
/**
     * 推荐人id
     *//*

    private Integer recommendid;

    private Integer itemNum;

    private BigDecimal totalAmount;

    private Integer usedPoint;

    private BigDecimal pointAmount;

    private BigDecimal payAmount;

    private BigDecimal postage;

    private BigDecimal productAmount;

    private BigDecimal discountAmount;
    private BigDecimal fodiscountamount;
    private BigDecimal usecouponamount;

    private BigDecimal refundAmount;

    private Integer paymentId;

    private String userMessage;

    private Integer orderStatus;

    private Integer payStatus;

    private Integer shipmentStatus;
    private Integer invoiceType;
    private Integer couponid;
    private String remark;

    private Date payTime;
    private Date confirmTime;
    private Date shipTime;
    private Date receptTime;
    private Date completeTime;	//完成时间（定时器使用）

    private Integer gainPoints;
    private Integer gainCoins;

    private Boolean commented;

    private Integer benefited;

    private BigDecimal benefitAmount;
    private BigDecimal gainCouponamount;

    private String custno;

    private Integer sync; //是否已同步采购，0-否，1-是
    private Integer utype;	//下单药店类型:0-普通客户，1-直营门店，2-加盟门店

    private Integer corpid;	//公司id
    private Boolean commissioned;	//提成标志
    private BigDecimal commissionAmount;	//提成金额

    private String taxcode; //药店编码

    private BigDecimal actualAmount;	//订单实际商品价格：退货后
    private BigDecimal backAmount;	//订单总退款金额

	private String cname;//公司名称

	*/
/**
	 * 订单对公打款待审核条数
	 *//*

	private Integer pendingReview;
	*/
/**
	 * 订单对公打款已审核条数
	 *//*

	private Integer alreadyReview;
	//审核状态
	private Integer approvalStauts;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}


	private String discountStr;//

	*/
/**
	 * 授信是否还款
	 *//*

	private Boolean paidCredit;

	*/
/**
	 * 最后一次退货退款的id
	 *//*

	private Integer latestOrderReturnId;

    */
/**
     * 退款信息集合
     *//*

    private List<TdOrderRefund> orderRefundList;

    */
/**
     * 退货信息集合
     *//*

    private List<TdOrderReturn> orderReturnList;

    */
/**
     * 优惠券信息
     *//*

    private TdCoupon coupon;

    */
/**
     * 购买店铺信息
     *//*

    private TdUserAddress userAddress;

    */
/**
     * 购买人信息
     *//*

    private TdUser orderUser;

    */
/**
     * 推荐人信息
     *//*

    private TdUser recommendUser;

    */
/**
     * 订单货品集合
     *//*

    private List<TdOrderSku> skuList;

    */
/**
     * 订单商品（产品包使用）
     *//*

    private List<TdOrderProduct> productList;

    */
/**
     * 订单收货/退货单
     *//*

    private List<TdOrderShipment> shipList;

    */
/**
     * 订单收货地址
     *//*

    private TdOrderAddress orderAddress;

    */
/**
     * 供应商信息
     *//*

    private TdUserSupply userSupply;

    */
/**
     * 可以使用钱包余额支付
     *//*

    private boolean canUserAccount;
    */
/**
     * 可以使用授信资金余额支付
     *//*

    private boolean canUserCredit;

    private String errMsg;
	*/
/**
	 *  免手续费标示
	 *//*

    private Integer free;
	*/
/**
	 * 退还云采豆数
	 *//*

	private Integer refundCoin;
	*/
/**
	 * 退还云采豆金额
	 *//*

	private BigDecimal refundCoinAmount;
	*/
/**
	 * 退还平台折扣金额
	 *//*

	private BigDecimal refundDiscountAmount;


    private Date time;

    private String fullname;

    private BigDecimal diacount;
	*/
/**
	 * 省名
	 *//*

	private String province;
	*/
/**
	 * 市名
	 *//*

	private String city;
	*/
/**
	 * 区名
	 *//*

	private String region;

	private BigDecimal amount;

	private String status;
	*/
/**
	 * 原邮费金额
	 *//*

	private BigDecimal originalPostage;
	*/
/**
	 * 已退款邮费
	 *//*

	private BigDecimal refundPostage;
	*/
/**
	 * 订单服务费规则
	 *//*

	private TdOrderServiceFeeRule orderServiceFeeRule;
	*/
/**
	 * 退款前订单状态
	 *//*

	private Integer preOrderStatus;

	*/
/**
	 * 折扣
	 *//*

	private Integer ratio;

	*/
/**
	 * 普控状态
	 *//*

	private Integer vipStatus;

	*/
/**
	 * 精控状态
	 *//*

	private Integer vipStatusForSupply;

	*/
/**
	 * 优惠券信息
	 *//*

	private List<TdCoupon> couponList;

	*/
/**
	 * 云采豆选中
	 *//*

	private Boolean coinChecked;

	*/
/**
	 * 云采豆
	 *//*

	private BigDecimal coinAmount;

	*/
/**
	 * 不包含秒杀金额的商品价格
	 *//*

	private BigDecimal totalPriceNoSeckill;

	*/
/**
	 * 秒杀商品金额
	 *//*

	private BigDecimal seckillPrice;
	*/
/**
	 * 是否省内：0-省外，1-省内
	 *//*

	private Boolean theProvince;
	*/
/**
	 * 是否允许商家收货
	 *//*

	private Boolean supplyReceiveOrder;

	*/
/**
	 * 集采退差金额
	 *//*

	private BigDecimal collectReturnAmount;


	public BigDecimal getCollectReturnAmount() {
		return collectReturnAmount;
	}

	public void setCollectReturnAmount(BigDecimal collectReturnAmount) {
		this.collectReturnAmount = collectReturnAmount;
	}

	*/
/**
	 * 电子发票地址
	 *//*

	private String invoiceUrl;
	*/
/**
	 * 平台优惠券金额
	 *//*

	private BigDecimal platformCouponAmount;
	*/
/**
	 * 退款平台优惠金额
	 *//*

	private BigDecimal refundPlatformCouponAmount;
	*/
/**
	 * 平台优惠券使用ID（未使用为0）
	 *//*

	private Integer usePlatformCouponId;

	*/
/**
	 * 商家名称
	 *//*

	private String shortname;

	*/
/**
	 * 使用优惠总和
	 *//*

	private BigDecimal platformCouponAmountSum;

	*/
/**
	 * 退款金额优惠总和
	 *//*

	private BigDecimal refundPlatformCouponAmountSum;

	*/
/**
	 * 使用金额
	 *//*

	private BigDecimal useAmount;

	*/
/**
	 * 折扣
	 *//*

	private BigDecimal discount;

	*/
/**
	 * 优惠券分类[1-满减券 2-折扣券]
	 *//*

	private Integer category;
	*/
/**
	 * 是否他人代付
	 *//*

	private Boolean paidByOthers;
	*/
/**
	 * 下单药店名称
	 *//*

	private String shopname;

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public BigDecimal getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(BigDecimal useAmount) {
		this.useAmount = useAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public BigDecimal getPlatformCouponAmountSum() {
		return platformCouponAmountSum;
	}

	public void setPlatformCouponAmountSum(BigDecimal platformCouponAmountSum) {
		this.platformCouponAmountSum = platformCouponAmountSum;
	}

	public BigDecimal getRefundPlatformCouponAmountSum() {
		return refundPlatformCouponAmountSum;
	}

	public void setRefundPlatformCouponAmountSum(BigDecimal refundPlatformCouponAmountSum) {
		this.refundPlatformCouponAmountSum = refundPlatformCouponAmountSum;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	*/
/**
	 * 业务员信息
	 *//*

	private SalesmanVO salesmanVO;

	public BigDecimal getSeckillPrice() {
		return seckillPrice;
	}

	public void setSeckillPrice(BigDecimal seckillPrice) {
		this.seckillPrice = seckillPrice;
	}

	public BigDecimal getTotalPriceNoSeckill() {
		return totalPriceNoSeckill;
	}

	public void setTotalPriceNoSeckill(BigDecimal totalPriceNoSeckill) {
		this.totalPriceNoSeckill = totalPriceNoSeckill;
	}

	public BigDecimal getCoinAmount() {
		return coinAmount;
	}

	public void setCoinAmount(BigDecimal coinAmount) {
		this.coinAmount = coinAmount;
	}

	public Boolean getCoinChecked() {
		return coinChecked;
	}

	public void setCoinChecked(Boolean coinChecked) {
		this.coinChecked = coinChecked;
	}

	public List<TdCoupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<TdCoupon> couponList) {
		this.couponList = couponList;
	}

	public Integer getVipStatus() {
		return vipStatus;
	}

	public void setVipStatus(Integer vipStatus) {
		this.vipStatus = vipStatus;
	}

	public Integer getVipStatusForSupply() {
		return vipStatusForSupply;
	}

	public void setVipStatusForSupply(Integer vipStatusForSupply) {
		this.vipStatusForSupply = vipStatusForSupply;
	}

	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
	*/
/**
	 * 集采活动编号
	 *//*

	private String collectNo;


	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public Integer getUaid() {
		return uaid;
	}

	public void setUaid(Integer uaid) {
		this.uaid = uaid;
	}

	public Integer getUtype() {
		return utype;
	}

	public void setUtype(Integer utype) {
		this.utype = utype;
	}

	public Integer getSync() {
		return sync;
	}

	public void setSync(Integer sync) {
		this.sync = sync;
	}

	public String getOrderNo() {
        return orderNo;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

	public String getOrderTypeStr() {
		if(this.getOrderType() == 1){
			return "普通商品订单";
		}
		if(this.getOrderType() == 2){
			return "礼品订单";
		}
		if(this.getOrderType() == 3){
			return "集采订单";
		}
		if(this.getOrderType() == 4){
			return "集团订单";
		}
		return "";
	}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getRecommendid() {
		return recommendid;
	}

	public void setRecommendid(Integer recommendid) {
		this.recommendid = recommendid;
	}

	public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getUsedPoint() {
        return usedPoint;
    }

    public void setUsedPoint(Integer usedPoint) {
        this.usedPoint = usedPoint;
    }

    public Integer getGainCoins() {
		return gainCoins;
	}

	public void setGainCoins(Integer gainCoins) {
		this.gainCoins = gainCoins;
	}

	public BigDecimal getPointAmount() {
        return pointAmount;
    }

    public BigDecimal getFodiscountamount() {
		return fodiscountamount;
	}

	public void setFodiscountamount(BigDecimal fodiscountamount) {
		this.fodiscountamount = fodiscountamount;
	}

	public BigDecimal getUsecouponamount() {
		return usecouponamount;
	}

	public void setUsecouponamount(BigDecimal usecouponamount) {
		this.usecouponamount = usecouponamount;
	}

	public void setPointAmount(BigDecimal pointAmount) {
        this.pointAmount = pointAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public BigDecimal getOriginalPostage() {
        return originalPostage;
    }

    public void setOriginalPostage(BigDecimal originalPostage) {
        this.originalPostage = originalPostage;
    }

    public BigDecimal getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(BigDecimal productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage == null ? null : userMessage.trim();
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

    public Integer getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(Integer shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	public Date getReceptTime() {
		return receptTime;
	}

	public void setReceptTime(Date receptTime) {
		this.receptTime = receptTime;
	}

    public Integer getGainPoints() {
        return gainPoints;
    }

    public void setGainPoints(Integer gainPoints) {
        this.gainPoints = gainPoints;
    }

    public Boolean getCommented() {
        return commented;
    }

    public void setCommented(Boolean commented) {
        this.commented = commented;
    }

    public Integer getBenefited() {
        return benefited;
    }

    public void setBenefited(Integer benefited) {
        this.benefited = benefited;
    }

    public BigDecimal getBenefitAmount() {
        return benefitAmount;
    }

    public void setBenefitAmount(BigDecimal benefitAmount) {
        this.benefitAmount = benefitAmount;
    }

	public BigDecimal getGainCouponamount() {
		return gainCouponamount;
	}

	public void setGainCouponamount(BigDecimal gainCouponamount) {
		this.gainCouponamount = gainCouponamount;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCouponid() {
		return couponid;
	}

	public void setCouponid(Integer couponid) {
		this.couponid = couponid;
	}

	public boolean isCanUserAccount() {
		return canUserAccount;
	}

	public void setCanUserAccount(boolean canUserAccount) {
		this.canUserAccount = canUserAccount;
	}

	public boolean isCanUserCredit() {
		return canUserCredit;
	}

	public void setCanUserCredit(boolean canUserCredit) {
		this.canUserCredit = canUserCredit;
	}

	public TdUserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(TdUserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public TdOrderAddress getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(TdOrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}

	public TdUserSupply getUserSupply() {
		return userSupply;
	}

	public void setUserSupply(TdUserSupply userSupply) {
		this.userSupply = userSupply;
	}

	public TdUser getOrderUser() {
		return orderUser;
	}

	public void setOrderUser(TdUser orderUser) {
		this.orderUser = orderUser;
	}

	public TdUser getRecommendUser() {
		return recommendUser;
	}

	public void setRecommendUser(TdUser recommendUser) {
		this.recommendUser = recommendUser;
	}

	public List<TdOrderSku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<TdOrderSku> skuList) {
		this.skuList = skuList;
	}

	public List<TdOrderProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<TdOrderProduct> productList) {
		this.productList = productList;
	}

	public Integer getCorpid() {
		return corpid;
	}

	public void setCorpid(Integer corpid) {
		this.corpid = corpid;
	}

	public Boolean getCommissioned() {
		return commissioned;
	}

	public void setCommissioned(Boolean commissioned) {
		this.commissioned = commissioned;
	}

	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(BigDecimal commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	*/
/**
	 * 获取订单未支付金额
	 * @return
	 *//*

	public BigDecimal getUnPayAmount(){
		return this.getPayAmount();
	}
	*/
/**
	 * 获取订单实际支付金额
	 * @return
	 *//*

	public BigDecimal getOrderPayAmount(){
		BigDecimal amount = BigDecimal.ZERO;
		if(null!=this.getPayAmount()){
			amount = this.getPayAmount().subtract(this.getRefundAmount());
		}
		return amount;
	}

	*/
/**
     * 获取订单发票类型文字说明
     * @return
     *//*

    public String getInvoiceTypeStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getInvoiceType()){
    		if(this.getInvoiceType().equals(1)){
    			sb.append("专票");
    		}else if(this.getInvoiceType().equals(0)){
    			sb.append("普票");
    		}else {
    			sb.append("不要发票");
    		}
    	}
    	return sb.toString();
    }
    */
/**
     * 获取订单分润状态文字说明
     * @return
     *//*

    public String getBenefitedStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getBenefited()){
    		if(this.getBenefited().equals(1)){
    			sb.append("未提成");
    		}else if(this.getBenefited().equals(2)){
    			sb.append("已提成");
    		}
    	}
    	return sb.toString();
    }
    */
/**
     * 获取订单状态文字说明
     * @return
     *//*

    public String getOrderStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getOrderStatus()){
    		if(ConstantsUtils.ORDER_STATUS_NEW.equals(this.getOrderStatus())){
    			sb.append("新订单");
    		}else if(ConstantsUtils.ORDER_STATUS_CANCEL.equals(this.getOrderStatus())){
    			sb.append("已取消");
    		}else if(ConstantsUtils.ORDER_STATUS_PAYED.equals(this.getOrderStatus())){
    			sb.append("已支付");
    		}else if(ConstantsUtils.ORDER_STATUS_CONFIRMED.equals(this.getOrderStatus())){
    			sb.append("已确认");
    		}else if(ConstantsUtils.ORDER_STATUS_BILLED.equals(this.getOrderStatus())){
    			sb.append("已开单");
    		}else if(ConstantsUtils.ORDER_STATUS_SHIPPMENTED.equals(this.getOrderStatus())){
    			sb.append("已发货");
    		}else if(ConstantsUtils.ORDER_STATUS_RECEIPTED.equals(this.getOrderStatus())){
    			sb.append("已收货");
    		}else if(ConstantsUtils.ORDER_STATUS_APPLYREFUND.equals(this.getOrderStatus())){
    			sb.append("申请退款");
    		}else if(ConstantsUtils.ORDER_STATUS_COMPLETE.equals(this.getOrderStatus())){
    			sb.append("已完成");
    		}else if(ConstantsUtils.ORDER_STATUS_GROUP.equals(this.getOrderStatus())){
				sb.append("待审核");
			}
    	}
    	return sb.toString();
    }
    */
/**
     * 获取客户查看订单状态文字说明
     * @return
     *//*

    public String getCusOrderStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getOrderStatus()){
    		if(ConstantsUtils.ORDER_STATUS_NEW.equals(this.getOrderStatus())){
    			sb.append("待支付");
    		}else if(ConstantsUtils.ORDER_STATUS_CANCEL.equals(this.getOrderStatus())){
    			sb.append("已取消");
    		}else if(ConstantsUtils.ORDER_STATUS_PAYED.equals(this.getOrderStatus())){
    			sb.append("待确认");
    		}else if(ConstantsUtils.ORDER_STATUS_CONFIRMED.equals(this.getOrderStatus())){
    			sb.append("待开单");
    		}else if(ConstantsUtils.ORDER_STATUS_BILLED.equals(this.getOrderStatus())){
    			sb.append("待发货");
    		}else if(ConstantsUtils.ORDER_STATUS_SHIPPMENTED.equals(this.getOrderStatus())){
    			sb.append("待收货");
    		}else if(ConstantsUtils.ORDER_STATUS_RECEIPTED.equals(this.getOrderStatus())){
    			sb.append("待评价");
    		}else if(ConstantsUtils.ORDER_STATUS_APPLYREFUND.equals(this.getOrderStatus())){
    			sb.append("申请退款中");
    		}else if(ConstantsUtils.ORDER_STATUS_COMPLETE.equals(this.getOrderStatus())){
    			sb.append("交易完成");
    		}
    	}
    	return sb.toString();
    }
    */
/**
     * 获取订单支付状态文字说明
     * @return
     *//*

    public String getPayStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getPayStatus()){
    		if(ConstantsUtils.ORDER_PAY_STATUS_UNPAY.equals(this.getPayStatus())){
    			sb.append("未支付");
    		}else if(ConstantsUtils.ORDER_PAY_STATUS_PAYED.equals(this.getPayStatus())){
    			sb.append("已支付");
    		}else if(ConstantsUtils.ORDER_PAY_STATUS_PART_REFUND.equals(this.getPayStatus())){
    			sb.append("部分退款");
    		}else if(ConstantsUtils.ORDER_PAY_STATUS_ALL_REFUND.equals(this.getPayStatus())){
    			sb.append("全额退款");
    		}
    	}
    	return sb.toString();
    }
    */
/**
     * 获取订单发货状态文字说明
     * @return
     *//*

    public String getShipmentStatusStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getShipmentStatus()){
    		if(ConstantsUtils.ORDER_SHIPMENT_STATUS_UNSHIPPED.equals(this.getShipmentStatus())){
    			sb.append("未发货");
    		}else if(ConstantsUtils.ORDER_SHIPMENT_STATUS_PART_SHIPPED.equals(this.getShipmentStatus())){
    			sb.append("部分发货");
    		}else if(ConstantsUtils.ORDER_SHIPMENT_STATUS_ALL_SHIPPED.equals(this.getShipmentStatus())){
    			sb.append("全部发货");
    		}else if(ConstantsUtils.ORDER_SHIPMENT_STATUS_PART_RETURN.equals(this.getShipmentStatus())){
    			sb.append("部分退货");
    		}else if(ConstantsUtils.ORDER_SHIPMENT_STATUS_RECEIPT.equals(this.getShipmentStatus())){
    			sb.append("已收货");
    		}else if(ConstantsUtils.ORDER_SHIPMENT_STATUS_ALL_RETURN.equals(this.getShipmentStatus())){
    			sb.append("全部退货");
    		}
    	}
    	return sb.toString();
    }
    */
/**
     * 获取订单支付方式文字说明
     * @return
     *//*

    public String getPaymentStr(){
    	StringBuffer sb = new StringBuffer();
    	if(null!=this.getPaymentId()){
    		if(ConstantsUtils.ORDER_PAYMENT_ALIPAY.equals(this.getPaymentId())){
    			sb.append("支付宝");
    		}else if(ConstantsUtils.ORDER_PAYMENT_WEIXIN.equals(this.getPaymentId())){
    			sb.append("微信");
    		}else if(ConstantsUtils.ORDER_PAYMENT_UNIONPAY.equals(this.getPaymentId())){
    			sb.append("银联支付");
    		}else if(ConstantsUtils.ORDER_PAYMENT_ACCOUNT.equals(this.getPaymentId())){
    			sb.append("钱包余额");
    		}else if(ConstantsUtils.ORDER_PAYMENT_CREDIT.equals(this.getPaymentId())){
    			sb.append("授信资金");
    		}else if(ConstantsUtils.ORDER_PAYMENT_UNIONPAYB2B.equals(this.getPaymentId())){
    			sb.append("银联企业");
    		}else if(ConstantsUtils.ORDER_PAYMENT_OFFLINE.equals(this.getPaymentId())){
    			sb.append("线下支付");
    		}else if(ConstantsUtils.ORDER_PAYMENT_CORPORATE.equals(this.getPaymentId())){
    			sb.append("对公打款");
			}
    	}
    	return sb.toString();
    }

	public List<TdOrderShipment> getShipList() {
		return shipList;
	}

	public void setShipList(List<TdOrderShipment> shipList) {
		this.shipList = shipList;
	}

	public String getTaxcode() {
		return taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public TdCoupon getCoupon() {
		return coupon;
	}

	public void setCoupon(TdCoupon coupon) {
		this.coupon = coupon;
	}

	public List<TdOrderRefund> getOrderRefundList() {
		return orderRefundList;
	}

	public void setOrderRefundList(List<TdOrderRefund> orderRefundList) {
		this.orderRefundList = orderRefundList;
	}

	public List<TdOrderReturn> getOrderReturnList() {
		return orderReturnList;
	}

	public void setOrderReturnList(List<TdOrderReturn> orderReturnList) {
		this.orderReturnList = orderReturnList;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Integer getPendingReview() {
		return pendingReview;
	}

	public void setPendingReview(Integer pendingReview) {
		this.pendingReview = pendingReview;
	}

	public Integer getAlreadyReview() {
		return alreadyReview;
	}

	public void setAlreadyReview(Integer alreadyReview) {
		this.alreadyReview = alreadyReview;
	}

	public Integer getApprovalStauts() {
		return approvalStauts;
	}

	public void setApprovalStauts(Integer approvalStauts) {
		this.approvalStauts = approvalStauts;
	}

	*/
/**
	 * 获取是否全部发货
	 * @return
	 *//*

	public boolean getIsAllShip() {
		for(TdOrderSku sku : this.skuList) {
			if(!sku.getQuantity().equals(sku.getPostQuantity())) {
				return false;
			}
		}
		return true;
	}

	*/
/**
	 * 获取是否全部退款
	 * @return
	 *//*

	public boolean getIsAllRefund() {
		for(TdOrderSku sku :this.skuList) {
			if(!sku.getQuantity().equals(sku.getBackQuantity())) {
				return false;
			}
		}
		return true;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public BigDecimal getBackAmount() {
		return backAmount;
	}

	public void setBackAmount(BigDecimal backAmount) {
		this.backAmount = backAmount;
	}

	@Override
	public String toString() {
		return "TdOrder{" +
                "orderNo='" + orderNo + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
	}

	public Integer getLatestOrderReturnId() {
		return latestOrderReturnId;
	}

	public void setLatestOrderReturnId(Integer latestOrderReturnId) {
		this.latestOrderReturnId = latestOrderReturnId;
	}

	public Boolean getPaidCredit() {
		return paidCredit;
	}

	public void setPaidCredit(Boolean paidCredit) {
		this.paidCredit = paidCredit;
	}

	*/
/**
	 * 是否线上支付
	 * @return
	 *//*

	public boolean isPaidOnline() {
		return ConstantsUtils.ORDER_PAYMENT_ALIPAY.equals(this.getPaymentId())
				|| ConstantsUtils.ORDER_PAYMENT_WEIXIN.equals(this.getPaymentId())
				|| ConstantsUtils.ORDER_PAYMENT_CREDIT.equals(this.getPaymentId())
                || ConstantsUtils.ORDER_PAYMENT_UNIONPAY.equals(this.getPaymentId());
	}

	*/
/**
	 * 实际是否已付款
	 * @return
	 *//*

	public boolean isActuallyPaid() {
		return ConstantsUtils.ORDER_PAYMENT_ALIPAY.equals(this.getPaymentId())
				|| ConstantsUtils.ORDER_PAYMENT_WEIXIN.equals(this.getPaymentId())
				|| (ConstantsUtils.ORDER_PAYMENT_CREDIT.equals(this.getPaymentId()) && this.getPaidCredit())
				|| ConstantsUtils.ORDER_PAYMENT_CORPORATE.equals(this.getPaymentId())
                || ConstantsUtils.ORDER_PAYMENT_UNIONPAY.equals(this.getPaymentId());
	}

	public Integer getRefundCoin() {
		return refundCoin;
	}

	public void setRefundCoin(Integer refundCoin) {
		this.refundCoin = refundCoin;
	}

	public BigDecimal getRefundCoinAmount() {
		return refundCoinAmount;
	}

	public void setRefundCoinAmount(BigDecimal refundCoinAmount) {
		this.refundCoinAmount = refundCoinAmount;
	}

	public BigDecimal getRefundDiscountAmount() {
		return refundDiscountAmount;
	}

	public void setRefundDiscountAmount(BigDecimal refundDiscountAmount) {
		this.refundDiscountAmount = refundDiscountAmount;
	}

	@Override
	public String getFirstKeyColumnName() {
		return "order_no";
	}


	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public BigDecimal getDiacount() {
		return diacount;
	}

	public void setDiacount(BigDecimal diacount) {
		this.diacount = diacount;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusStr(){
		StringBuffer sb = new StringBuffer();
		if(null!=this.getStatus()){
			if("1".equals(this.getStatus())){
				sb.append("已收货");
			}else if("2".equals(this.getStatus())){
				sb.append("退货完成");
			}
		}
		return sb.toString();
	}

	public String getDiscountStr() {
		return discountStr;
	}

	public void setDiscountStr(String discountStr) {
		this.discountStr = discountStr;
	}

	*/
/**
	 * 获取订单所有发货的总价格
	 * @return
	 *//*

	public BigDecimal getTotalShipAmount() {
		BigDecimal total = BigDecimal.ZERO;
		if (!CollectionUtils.isEmpty(this.getShipList())) {
			for (TdOrderShipment ship : this.getShipList()) {
				total = total.add(ship.getSingleShipAmount());
			}
		}
		return total;
	}

	*/
/**
	 * 获取订单总退款金额
	 * @return
	 *//*

	public BigDecimal getTotalReturnAmount() {
		BigDecimal total = BigDecimal.ZERO;
		if (!CollectionUtils.isEmpty(this.getSkuList())) {
			for (TdOrderSku sku : this.getSkuList()) {
				total = total.add(sku.getReturnOriginalAmount());
			}
		}
		return total;
	}

	public BigDecimal getRefundPostage() {
		return refundPostage;
	}

	public void setRefundPostage(BigDecimal refundPostage) {
		this.refundPostage = refundPostage;
	}

	public TdOrderServiceFeeRule getOrderServiceFeeRule() {
		return orderServiceFeeRule;
	}

	public void setOrderServiceFeeRule(TdOrderServiceFeeRule orderServiceFeeRule) {
		this.orderServiceFeeRule = orderServiceFeeRule;
	}

	public Integer getPreOrderStatus() {
		return preOrderStatus;
	}

	public void setPreOrderStatus(Integer preOrderStatus) {
		this.preOrderStatus = preOrderStatus;
	}

	public String getCollectNo() {
		return collectNo;
	}

	public void setCollectNo(String collectNo) {
		this.collectNo = collectNo;
	}

	public Boolean getTheProvince() {
		return theProvince;
	}

	public void setTheProvince(Boolean theProvince) {
		this.theProvince = theProvince;
	}

	public Boolean getSupplyReceiveOrder() {
		return supplyReceiveOrder;
	}

	public void setSupplyReceiveOrder(Boolean supplyReceiveOrder) {
		this.supplyReceiveOrder = supplyReceiveOrder;
	}

	public String getInvoiceUrl() {
		return invoiceUrl;
	}

	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}

	public BigDecimal getPlatformCouponAmount() {
		return platformCouponAmount;
	}

	public void setPlatformCouponAmount(BigDecimal platformCouponAmount) {
		this.platformCouponAmount = platformCouponAmount;
	}

	public BigDecimal getRefundPlatformCouponAmount() {
		return refundPlatformCouponAmount;
	}

	public void setRefundPlatformCouponAmount(BigDecimal refundPlatformCouponAmount) {
		this.refundPlatformCouponAmount = refundPlatformCouponAmount;
	}

	public Integer getUsePlatformCouponId() {
		return usePlatformCouponId;
	}

	public void setUsePlatformCouponId(Integer usePlatformCouponId) {
		this.usePlatformCouponId = usePlatformCouponId;
	}

	public SalesmanVO getSalesmanVO() {
		return salesmanVO;
	}

	public void setSalesmanVO(SalesmanVO salesmanVO) {
		this.salesmanVO = salesmanVO;
	}

	public Boolean getPaidByOthers() {
		return paidByOthers;
	}

	public void setPaidByOthers(Boolean paidByOthers) {
		this.paidByOthers = paidByOthers;
	}
}*/
