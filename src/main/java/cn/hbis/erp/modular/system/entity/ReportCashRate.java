package cn.hbis.erp.modular.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 *
 *
 */
public class ReportCashRate implements Serializable {

    private static final long serialVersionUID = 4013992525944989718L;

    private Long id;// 自增序列SEQ_REPORT_CUSTOMER_CASH_RATE
    private String customerManager;// 客户经理
    private String endUser;// 终端用户
    private String contractUnit;// 合同单位
    private Long companyId;// 子公司
    private String variety;// 品种
    private String orderNo;// 合同号
    private String grade;// 牌号
    private String standard;// 规格
    private BigDecimal contractWeight;// 合同重量
    private Date orderCreate;// 合同签订日期
    private Date orderStopDate;// 交货截止日期
    private Date djDate;// 定价日期
    private BigDecimal scheduledWeight;// 按期交货量
    private BigDecimal delayedWeight;// 延期交货量
    private BigDecimal deliveryWeight;// 发货量汇总
    private BigDecimal cashRete;// 兑现率情况
    private BigDecimal scheduledCashRate;// 按期兑现率
    private BigDecimal actualCashRate;// 实际兑现率
    private String summaryType;// 发货量汇总方式：0-去0统计；1-不去0统计
    private Date gmtCreate;// 创建时间
    private Date gmtModify;// 修改时间
    private String remark;// 备注
    private Date recordDate;// 记录日期

    private BigDecimal contractNum;

    private String productClass;//产品类别
    private String productGrade;//产品等级

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public String getEndUser() {
        return endUser;
    }

    public void setEndUser(String endUser) {
        this.endUser = endUser;
    }

    public String getContractUnit() {
        return contractUnit;
    }

    public void setContractUnit(String contractUnit) {
        this.contractUnit = contractUnit;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public BigDecimal getContractWeight() {
        return contractWeight;
    }

    public void setContractWeight(BigDecimal contractWeight) {
        this.contractWeight = contractWeight;
    }

    public Date getOrderCreate() {
        return orderCreate;
    }

    public void setOrderCreate(Date orderCreate) {
        this.orderCreate = orderCreate;
    }

    public Date getOrderStopDate() {
        return orderStopDate;
    }

    public void setOrderStopDate(Date orderStopDate) {
        this.orderStopDate = orderStopDate;
    }

    public Date getDjDate() {
        return djDate;
    }

    public void setDjDate(Date djDate) {
        this.djDate = djDate;
    }

    public BigDecimal getScheduledWeight() {
        return scheduledWeight;
    }

    public void setScheduledWeight(BigDecimal scheduledWeight) {
        this.scheduledWeight = scheduledWeight;
    }

    public BigDecimal getDelayedWeight() {
        return delayedWeight;
    }

    public void setDelayedWeight(BigDecimal delayedWeight) {
        this.delayedWeight = delayedWeight;
    }

    public BigDecimal getDeliveryWeight() {
        return deliveryWeight;
    }

    public void setDeliveryWeight(BigDecimal deliveryWeight) {
        this.deliveryWeight = deliveryWeight;
    }

    public BigDecimal getCashRete() {
        return cashRete;
    }

    public void setCashRete(BigDecimal cashRete) {
        this.cashRete = cashRete;
    }

    public BigDecimal getScheduledCashRate() {
        return scheduledCashRate;
    }

    public void setScheduledCashRate(BigDecimal scheduledCashRate) {
        this.scheduledCashRate = scheduledCashRate;
    }

    public BigDecimal getActualCashRate() {
        return actualCashRate;
    }

    public void setActualCashRate(BigDecimal actualCashRate) {
        this.actualCashRate = actualCashRate;
    }

    public String getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(String summaryType) {
        this.summaryType = summaryType;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public BigDecimal getContractNum() {
        return contractNum;
    }

    public void setContractNum(BigDecimal contractNum) {
        this.contractNum = contractNum;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getProductGrade() {
        return productGrade;
    }

    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade;
    }
}
