package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 自贡市品种钢情况
 * </p>
 *
 *
 *
 */
public class ReportVarietySteelBean implements Serializable {

    private static final long serialVersionUID = -7368686079565891847L;

    @TableId
    private String companyId;
    private String companyName;//子公司
    private String varietyClass;//品种类别
    private BigDecimal totalSteel;//钢材总量
    private BigDecimal totalSteelVarieties;//品种钢总量
    private String scaleSteel;//占比
    private BigDecimal featuresProducts;//特色产品
    private BigDecimal highProducts;//高端产品
    private BigDecimal steelVarieties;//品种钢量

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

    public String getVarietyClass() {
        return varietyClass;
    }
    public void setVarietyClass(String varietyClass) {
        this.varietyClass = varietyClass;
    }
    public BigDecimal getTotalSteel() {
        return totalSteel;
    }
    public void setTotalSteel(BigDecimal totalSteel) {
        this.totalSteel = totalSteel;
    }
    public BigDecimal getTotalSteelVarieties() {
        return totalSteelVarieties;
    }
    public void setTotalSteelVarieties(BigDecimal totalSteelVarieties) {
        this.totalSteelVarieties = totalSteelVarieties;
    }
    public String getScaleSteel() {
        return scaleSteel;
    }
    public void setScaleSteel(String scaleSteel) {
        this.scaleSteel = scaleSteel;
    }
    public BigDecimal getFeaturesProducts() {
        return featuresProducts;
    }
    public void setFeaturesProducts(BigDecimal featuresProducts) {
        this.featuresProducts = featuresProducts;
    }
    public BigDecimal getHighProducts() {
        return highProducts;
    }
    public void setHighProducts(BigDecimal highProducts) {
        this.highProducts = highProducts;
    }
    public BigDecimal getSteelVarieties() {
        return steelVarieties;
    }
    public void setSteelVarieties(BigDecimal steelVarieties) {
        this.steelVarieties = steelVarieties;
    }
}
