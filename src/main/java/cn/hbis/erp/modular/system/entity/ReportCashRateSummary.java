package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 客户兑现率汇总
 * </p>
 *
 *
 *
 */
public class ReportCashRateSummary implements Serializable {

    private static final long serialVersionUID = 4013992525944989718L;

    private String productGrade;//产品等级
    @TableId
    private Long companyId;//子公司
    private String companyName;
    private BigDecimal deliveryWeight;//发货总量
    private BigDecimal contractWeight;//合同总量
    private BigDecimal cashRate;//兑现率
    private BigDecimal deliveryWeightJan;//一月份发货量
    private BigDecimal contractWeightJan;//一月份合同量
    private BigDecimal cashRateJan;//一月兑现率
    private BigDecimal deliveryWeightFeb;//二月份发货量
    private BigDecimal contractWeightFeb;//二月份合同量
    private BigDecimal cashRateFeb;//二月兑现率
    private BigDecimal deliveryWeightMar;//三月份发货量
    private BigDecimal contractWeightMar;//三月份合同量
    private BigDecimal cashRateMar;//三月兑现率
    private BigDecimal deliveryWeightApr;//四月份发货量
    private BigDecimal contractWeightApr;//四月份合同量
    private BigDecimal cashRateApr;//四月兑现率
    private BigDecimal deliveryWeightMay;//五月份发货量
    private BigDecimal contractWeightMay;//五月份合同量
    private BigDecimal cashRateMay;//五月兑现率
    private BigDecimal deliveryWeightJun;//六月份发货量
    private BigDecimal contractWeightJun;//六月份合同量
    private BigDecimal cashRateJun;//六月兑现率
    private BigDecimal deliveryWeightJul;//七月份发货量
    private BigDecimal contractWeightJul;//七月份合同量
    private BigDecimal cashRateJul;//七月兑现率
    private BigDecimal deliveryWeightAug;//八月份发货量
    private BigDecimal contractWeightAug;//八月份合同量
    private BigDecimal cashRateAug;//八月兑现率
    private BigDecimal deliveryWeightSept;//九月份发货量
    private BigDecimal contractWeightSept;//九月份合同量
    private BigDecimal cashRateSept;//九月兑现率
    private BigDecimal deliveryWeightOct;//十月份发货量
    private BigDecimal contractWeightOct;//十月份合同量
    private BigDecimal cashRateOct;//十月兑现率
    private BigDecimal deliveryWeightNov;//十一月份发货量
    private BigDecimal contractWeightNov;//十一月份合同量
    private BigDecimal cashRateNov;//十一月兑现率
    private BigDecimal deliveryWeightDec;//十二月份发货量
    private BigDecimal contractWeightDec;//十二月份合同量
    private BigDecimal cashRateDec;//十二月兑现率
    private String orderStopDate;//交货截止日期


    public String getProductGrade() {
        return productGrade;
    }
    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade;
    }


    public Long getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public BigDecimal getDeliveryWeight() {
        return deliveryWeight;
    }
    public void setDeliveryWeight(BigDecimal deliveryWeight) {
        this.deliveryWeight = deliveryWeight;
    }
    public BigDecimal getContractWeight() {
        return contractWeight;
    }
    public void setContractWeight(BigDecimal contractWeight) {
        this.contractWeight = contractWeight;
    }
    public BigDecimal getCashRate() {
        return cashRate;
    }
    public void setCashRate(BigDecimal cashRate) {
        this.cashRate = cashRate;
    }
    public BigDecimal getDeliveryWeightJan() {
        return deliveryWeightJan;
    }
    public void setDeliveryWeightJan(BigDecimal deliveryWeightJan) {
        this.deliveryWeightJan = deliveryWeightJan;
    }
    public BigDecimal getContractWeightJan() {
        return contractWeightJan;
    }
    public void setContractWeightJan(BigDecimal contractWeightJan) {
        this.contractWeightJan = contractWeightJan;
    }
    public BigDecimal getCashRateJan() {
        return cashRateJan;
    }
    public void setCashRateJan(BigDecimal cashRateJan) {
        this.cashRateJan = cashRateJan;
    }
    public BigDecimal getDeliveryWeightFeb() {
        return deliveryWeightFeb;
    }
    public void setDeliveryWeightFeb(BigDecimal deliveryWeightFeb) {
        this.deliveryWeightFeb = deliveryWeightFeb;
    }
    public BigDecimal getContractWeightFeb() {
        return contractWeightFeb;
    }
    public void setContractWeightFeb(BigDecimal contractWeightFeb) {
        this.contractWeightFeb = contractWeightFeb;
    }
    public BigDecimal getCashRateFeb() {
        return cashRateFeb;
    }
    public void setCashRateFeb(BigDecimal cashRateFeb) {
        this.cashRateFeb = cashRateFeb;
    }
    public BigDecimal getDeliveryWeightMar() {
        return deliveryWeightMar;
    }
    public void setDeliveryWeightMar(BigDecimal deliveryWeightMar) {
        this.deliveryWeightMar = deliveryWeightMar;
    }
    public BigDecimal getContractWeightMar() {
        return contractWeightMar;
    }
    public void setContractWeightMar(BigDecimal contractWeightMar) {
        this.contractWeightMar = contractWeightMar;
    }
    public BigDecimal getCashRateMar() {
        return cashRateMar;
    }
    public void setCashRateMar(BigDecimal cashRateMar) {
        this.cashRateMar = cashRateMar;
    }
    public BigDecimal getDeliveryWeightApr() {
        return deliveryWeightApr;
    }
    public void setDeliveryWeightApr(BigDecimal deliveryWeightApr) {
        this.deliveryWeightApr = deliveryWeightApr;
    }
    public BigDecimal getContractWeightApr() {
        return contractWeightApr;
    }
    public void setContractWeightApr(BigDecimal contractWeightApr) {
        this.contractWeightApr = contractWeightApr;
    }
    public BigDecimal getCashRateApr() {
        return cashRateApr;
    }
    public void setCashRateApr(BigDecimal cashRateApr) {
        this.cashRateApr = cashRateApr;
    }
    public BigDecimal getDeliveryWeightMay() {
        return deliveryWeightMay;
    }
    public void setDeliveryWeightMay(BigDecimal deliveryWeightMay) {
        this.deliveryWeightMay = deliveryWeightMay;
    }
    public BigDecimal getContractWeightMay() {
        return contractWeightMay;
    }
    public void setContractWeightMay(BigDecimal contractWeightMay) {
        this.contractWeightMay = contractWeightMay;
    }
    public BigDecimal getCashRateMay() {
        return cashRateMay;
    }
    public void setCashRateMay(BigDecimal cashRateMay) {
        this.cashRateMay = cashRateMay;
    }
    public BigDecimal getDeliveryWeightJun() {
        return deliveryWeightJun;
    }
    public void setDeliveryWeightJun(BigDecimal deliveryWeightJun) {
        this.deliveryWeightJun = deliveryWeightJun;
    }
    public BigDecimal getContractWeightJun() {
        return contractWeightJun;
    }
    public void setContractWeightJun(BigDecimal contractWeightJun) {
        this.contractWeightJun = contractWeightJun;
    }
    public BigDecimal getCashRateJun() {
        return cashRateJun;
    }
    public void setCashRateJun(BigDecimal cashRateJun) {
        this.cashRateJun = cashRateJun;
    }
    public BigDecimal getDeliveryWeightJul() {
        return deliveryWeightJul;
    }
    public void setDeliveryWeightJul(BigDecimal deliveryWeightJul) {
        this.deliveryWeightJul = deliveryWeightJul;
    }
    public BigDecimal getContractWeightJul() {
        return contractWeightJul;
    }
    public void setContractWeightJul(BigDecimal contractWeightJul) {
        this.contractWeightJul = contractWeightJul;
    }
    public BigDecimal getCashRateJul() {
        return cashRateJul;
    }
    public void setCashRateJul(BigDecimal cashRateJul) {
        this.cashRateJul = cashRateJul;
    }
    public BigDecimal getDeliveryWeightAug() {
        return deliveryWeightAug;
    }
    public void setDeliveryWeightAug(BigDecimal deliveryWeightAug) {
        this.deliveryWeightAug = deliveryWeightAug;
    }
    public BigDecimal getContractWeightAug() {
        return contractWeightAug;
    }
    public void setContractWeightAug(BigDecimal contractWeightAug) {
        this.contractWeightAug = contractWeightAug;
    }
    public BigDecimal getCashRateAug() {
        return cashRateAug;
    }
    public void setCashRateAug(BigDecimal cashRateAug) {
        this.cashRateAug = cashRateAug;
    }
    public BigDecimal getDeliveryWeightSept() {
        return deliveryWeightSept;
    }
    public void setDeliveryWeightSept(BigDecimal deliveryWeightSept) {
        this.deliveryWeightSept = deliveryWeightSept;
    }
    public BigDecimal getContractWeightSept() {
        return contractWeightSept;
    }
    public void setContractWeightSept(BigDecimal contractWeightSept) {
        this.contractWeightSept = contractWeightSept;
    }
    public BigDecimal getCashRateSept() {
        return cashRateSept;
    }
    public void setCashRateSept(BigDecimal cashRateSept) {
        this.cashRateSept = cashRateSept;
    }
    public BigDecimal getDeliveryWeightOct() {
        return deliveryWeightOct;
    }
    public void setDeliveryWeightOct(BigDecimal deliveryWeightOct) {
        this.deliveryWeightOct = deliveryWeightOct;
    }
    public BigDecimal getContractWeightOct() {
        return contractWeightOct;
    }
    public void setContractWeightOct(BigDecimal contractWeightOct) {
        this.contractWeightOct = contractWeightOct;
    }
    public BigDecimal getCashRateOct() {
        return cashRateOct;
    }
    public void setCashRateOct(BigDecimal cashRateOct) {
        this.cashRateOct = cashRateOct;
    }
    public BigDecimal getDeliveryWeightNov() {
        return deliveryWeightNov;
    }
    public void setDeliveryWeightNov(BigDecimal deliveryWeightNov) {
        this.deliveryWeightNov = deliveryWeightNov;
    }
    public BigDecimal getContractWeightNov() {
        return contractWeightNov;
    }
    public void setContractWeightNov(BigDecimal contractWeightNov) {
        this.contractWeightNov = contractWeightNov;
    }
    public BigDecimal getCashRateNov() {
        return cashRateNov;
    }
    public void setCashRateNov(BigDecimal cashRateNov) {
        this.cashRateNov = cashRateNov;
    }
    public BigDecimal getDeliveryWeightDec() {
        return deliveryWeightDec;
    }
    public void setDeliveryWeightDec(BigDecimal deliveryWeightDec) {
        this.deliveryWeightDec = deliveryWeightDec;
    }
    public BigDecimal getContractWeightDec() {
        return contractWeightDec;
    }
    public void setContractWeightDec(BigDecimal contractWeightDec) {
        this.contractWeightDec = contractWeightDec;
    }
    public BigDecimal getCashRateDec() {
        return cashRateDec;
    }
    public void setCashRateDec(BigDecimal cashRateDec) {
        this.cashRateDec = cashRateDec;
    }
    public String getOrderStopDate() {
        return orderStopDate;
    }
    public void setOrderStopDate(String orderStopDate) {
        this.orderStopDate = orderStopDate;
    }

}
