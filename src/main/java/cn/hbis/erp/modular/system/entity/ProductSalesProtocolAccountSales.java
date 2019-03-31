package cn.hbis.erp.modular.system.entity;

import java.io.Serializable;

/**
 * <p>
 * 产品总销量及销售公司协议户销量统计
 * </p>
 *
 */
public class ProductSalesProtocolAccountSales implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyName;//子公司名
    private String companyId;//子公司id
    private String varieties;//产品类别
    private Double totalSales;//总销量
    private Double protocolAccountNum;//协议户数
    private Double protocolSalesYear;//年协议量
    private Double totalProtocolSales;//总公司协议户销量 销售量
    private Double totalProtocolSalesOfYear;//总公司协议户销量 占年协议量比
    private Double totalProtocolSalesOfProduct;//总公司协议户销量 占产品总销量
    private Double zibanProtocolSales;//协议户中自办公司销量 销售量
    private Double zibanProtocolSalesOfTotal;//协议户中自办公司销量 占协议总销量比
    private Double xieyiProtocolSales;//协议户中执行协议政策销量 销售量
    private Double xieyiProtocolSalesOfTotal;//协议户中执行协议政策销量 占协议总销量比

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    public Double getProtocolAccountNum() {
        return protocolAccountNum;
    }

    public void setProtocolAccountNum(Double protocolAccountNum) {
        this.protocolAccountNum = protocolAccountNum;
    }

    public Double getProtocolSalesYear() {
        return protocolSalesYear;
    }

    public void setProtocolSalesYear(Double protocolSalesYear) {
        this.protocolSalesYear = protocolSalesYear;
    }

    public Double getTotalProtocolSales() {
        return totalProtocolSales;
    }

    public void setTotalProtocolSales(Double totalProtocolSales) {
        this.totalProtocolSales = totalProtocolSales;
    }

    public Double getTotalProtocolSalesOfYear() {
        return totalProtocolSalesOfYear;
    }

    public void setTotalProtocolSalesOfYear(Double totalProtocolSalesOfYear) {
        this.totalProtocolSalesOfYear = totalProtocolSalesOfYear;
    }

    public Double getTotalProtocolSalesOfProduct() {
        return totalProtocolSalesOfProduct;
    }

    public void setTotalProtocolSalesOfProduct(Double totalProtocolSalesOfProduct) {
        this.totalProtocolSalesOfProduct = totalProtocolSalesOfProduct;
    }

    public Double getZibanProtocolSales() {
        return zibanProtocolSales;
    }

    public void setZibanProtocolSales(Double zibanProtocolSales) {
        this.zibanProtocolSales = zibanProtocolSales;
    }

    public Double getZibanProtocolSalesOfTotal() {
        return zibanProtocolSalesOfTotal;
    }

    public void setZibanProtocolSalesOfTotal(Double zibanProtocolSalesOfTotal) {
        this.zibanProtocolSalesOfTotal = zibanProtocolSalesOfTotal;
    }

    public Double getXieyiProtocolSales() {
        return xieyiProtocolSales;
    }

    public void setXieyiProtocolSales(Double xieyiProtocolSales) {
        this.xieyiProtocolSales = xieyiProtocolSales;
    }

    public Double getXieyiProtocolSalesOfTotal() {
        return xieyiProtocolSalesOfTotal;
    }

    public void setXieyiProtocolSalesOfTotal(Double xieyiProtocolSalesOfTotal) {
        this.xieyiProtocolSalesOfTotal = xieyiProtocolSalesOfTotal;
    }

    @Override
    public String toString() {
        return "ProductSalesProtocolAccountSales{" +
                "companyName='" + companyName + '\'' +
                ", companyId='" + companyId + '\'' +
                ", varieties='" + varieties + '\'' +
                ", totalSales=" + totalSales +
                ", protocolAccountNum=" + protocolAccountNum +
                ", protocolSalesYear=" + protocolSalesYear +
                ", totalProtocolSales=" + totalProtocolSales +
                ", totalProtocolSalesOfYear=" + totalProtocolSalesOfYear +
                ", totalProtocolSalesOfProduct=" + totalProtocolSalesOfProduct +
                ", zibanProtocolSales=" + zibanProtocolSales +
                ", zibanProtocolSalesOfTotal=" + zibanProtocolSalesOfTotal +
                ", xieyiProtocolSales=" + xieyiProtocolSales +
                ", xieyiProtocolSalesOfTotal=" + xieyiProtocolSalesOfTotal +
                '}';
    }
}
