package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 协议户明细表
 * </p>
 *
 *
 *
 */
@TableName("scm_protocol_account_details")
public class ProtocolAccountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "PROTOCOL_ACCOUNT_ID", type = IdType.UUID)
    private String protocolAccountId;
    /**
     * 上传时间
     */
    @TableField(value = "UPLOAD_TIME", fill = FieldFill.INSERT)
    private Date uploadTime;
    /**
     * 协议年份
     */
    @TableField("PROTOCOL_YEAR")
    private String protocolYear;
    /**
     * 用户名称（全称）
     */
    @TableField("ACCOUNT_NAME")
    private String accountName;
    /**
     * 供货方式
     */
    @TableField("SUPPLY_MODE")
    private String supplyMode;
    /**
     * 品种
     */
    @TableField("VARIETIES")
    private String varieties;
    /**
     * 主销售区域
     */
    @TableField("MAIN_SALES_REGIONAL")
    private String mainSalesRegional;
    /**
     * 辅助销售区域一
     */
    @TableField("AIDED_SALES_REGIONAL_ONE")
    private String aidedSalesRegionalOne;
    /**
     * 辅助销售区域二
     */
    @TableField("AIDED_SALES_REGIONAL_TWO")
    private String aidedSalesRegionalTwo;
    /**
     * 钢厂
     */
    @TableField("STEEL_MILLS")
    private String steelMills;
    /**
     * 年协议量（吨）
     */
    @TableField("ANNUAL_AGREEMENT_VOLUME")
    private String annualAgreementVolume;
    /**
     * 删除状态
     */
    @TableField("DELETESTATUS")
    private String deleteStatus;


    public String getProtocolAccountId() {
        return protocolAccountId;
    }

    public void setProtocolAccountId(String protocolAccountId) {
        this.protocolAccountId = protocolAccountId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getProtocolYear() {
        return protocolYear;
    }

    public void setProtocolYear(String protocolYear) {
        this.protocolYear = protocolYear;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSupplyMode() {
        return supplyMode;
    }

    public void setSupplyMode(String supplyMode) {
        this.supplyMode = supplyMode;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public String getMainSalesRegional() {
        return mainSalesRegional;
    }

    public void setMainSalesRegional(String mainSalesRegional) {
        this.mainSalesRegional = mainSalesRegional;
    }

    public String getAidedSalesRegionalOne() {
        return aidedSalesRegionalOne;
    }

    public void setAidedSalesRegionalOne(String aidedSalesRegionalOne) {
        this.aidedSalesRegionalOne = aidedSalesRegionalOne;
    }

    public String getAidedSalesRegionalTwo() {
        return aidedSalesRegionalTwo;
    }

    public void setAidedSalesRegionalTwo(String aidedSalesRegionalTwo) {
        this.aidedSalesRegionalTwo = aidedSalesRegionalTwo;
    }

    public String getSteelMills() {
        return steelMills;
    }

    public void setSteelMills(String steelMills) {
        this.steelMills = steelMills;
    }

    public String getAnnualAgreementVolume() {
        return annualAgreementVolume;
    }

    public void setAnnualAgreementVolume(String annualAgreementVolume) {
        this.annualAgreementVolume = annualAgreementVolume;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        return "ProtocolAccountDetails{" +
                "protocolAccountId='" + protocolAccountId + '\'' +
                ", uploadTime=" + uploadTime +
                ", protocolYear='" + protocolYear + '\'' +
                ", accountName='" + accountName + '\'' +
                ", supplyMode='" + supplyMode + '\'' +
                ", varieties='" + varieties + '\'' +
                ", mainSalesRegional='" + mainSalesRegional + '\'' +
                ", aidedSalesRegionalOne='" + aidedSalesRegionalOne + '\'' +
                ", aidedSalesRegionalTwo='" + aidedSalesRegionalTwo + '\'' +
                ", steelMills='" + steelMills + '\'' +
                ", annualAgreementVolume='" + annualAgreementVolume + '\'' +
                ", deleteStatus='" + deleteStatus + '\'' +
                '}';
    }
}
