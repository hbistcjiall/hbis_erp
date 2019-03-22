package cn.hbis.erp.modular.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 自贡市品种钢情况明细
 * </p>
 *
 *
 *
 */
public class ReportVarietySteelItemBean implements Serializable {

    private static final long serialVersionUID = 8322790240497268823L;
    private String companyId;
    private String companyName;//子公司
    private Date actualDate;//实际发货日期
    private String delivNum;//交货单号
    private String delivItem;//交货单行号
    private String orderNo;//订单号
    private String orderRow;//订单行号
    private String productLine;//产线
    private String productLineClass;//产线类别
    private String productClassFine;//产品类别细分
    private String productClassCrude;//产品类别粗分
    private String productClass;//产品类别
    private String productGrade;//产线等级
    private String productType;//产品类别
    private String orderTypeDescribe;//订单类型描述
    private String salerName;//合同单位
    private String totalWeight;//重量
    private String variety;//品种
    private String material;//材质
    private String specification;//规格
    private String materialInfo;//物料描述
    private String productCode;//产品组编码
    private String saleGroup;//销售组织
    private String attribute9;//表面质量等级
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Date getActualDate() {
        return actualDate;
    }
    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }
    public String getDelivNum() {
        return delivNum;
    }
    public void setDelivNum(String delivNum) {
        this.delivNum = delivNum;
    }
    public String getDelivItem() {
        return delivItem;
    }
    public void setDelivItem(String delivItem) {
        this.delivItem = delivItem;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getOrderRow() {
        return orderRow;
    }
    public void setOrderRow(String orderRow) {
        this.orderRow = orderRow;
    }
    public String getProductLine() {
        return productLine;
    }
    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }
    public String getProductLineClass() {
        return productLineClass;
    }
    public void setProductLineClass(String productLineClass) {
        this.productLineClass = productLineClass;
    }
    public String getProductClassFine() {
        return productClassFine;
    }
    public void setProductClassFine(String productClassFine) {
        this.productClassFine = productClassFine;
    }
    public String getProductClassCrude() {
        return productClassCrude;
    }
    public void setProductClassCrude(String productClassCrude) {
        this.productClassCrude = productClassCrude;
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
    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getOrderTypeDescribe() {
        return orderTypeDescribe;
    }
    public void setOrderTypeDescribe(String orderTypeDescribe) {
        this.orderTypeDescribe = orderTypeDescribe;
    }
    public String getSalerName() {
        return salerName;
    }
    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }
    public String getTotalWeight() {
        return totalWeight;
    }
    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }
    public String getVariety() {
        return variety;
    }
    public void setVariety(String variety) {
        this.variety = variety;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public String getSpecification() {
        return specification;
    }
    public void setSpecification(String specification) {
        this.specification = specification;
    }
    public String getMaterialInfo() {
        return materialInfo;
    }
    public void setMaterialInfo(String materialInfo) {
        this.materialInfo = materialInfo;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getSaleGroup() {
        return saleGroup;
    }
    public void setSaleGroup(String saleGroup) {
        this.saleGroup = saleGroup;
    }
    public String getAttribute9() {
        return attribute9;
    }
    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }
}
