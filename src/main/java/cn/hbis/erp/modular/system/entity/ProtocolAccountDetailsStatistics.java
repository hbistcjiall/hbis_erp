package cn.hbis.erp.modular.system.entity;

import java.io.Serializable;

/**
 * <p>
 * 协议户明细统计列表
 * </p>
 *
 */
public class ProtocolAccountDetailsStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    private String statisticsTime;//统计月份
    private String accountName;//用户名（全称）
    private String supplyMode;//供货方式
    private String varieties;//品种
    private String mainSalesRegional;//主销售区域
    private String aidedSalesRegionalOne;//辅助销售区域一
    private String aidedSalesRegionalTwo;//辅助销售区域二
    private String steelMills;//钢厂
    private String annualAgreementVolume;//年协议量（吨）
    private Long orderMount;//当期协议销量（吨）
    private Long protocolOrderMount;//当期执行集团协议价政销量（吨）

    public String getStatisticsTime() {
        return statisticsTime;
    }

    public void setStatisticsTime(String statisticsTime) {
        this.statisticsTime = statisticsTime;
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

    public Long getOrderMount() {
        return orderMount;
    }

    public void setOrderMount(Long orderMount) {
        this.orderMount = orderMount;
    }

    public Long getProtocolOrderMount() {
        return protocolOrderMount;
    }

    public void setProtocolOrderMount(Long protocolOrderMount) {
        this.protocolOrderMount = protocolOrderMount;
    }

    @Override
    public String toString() {
        return "ProtocolAccountDetailsStatistics{" +
                "statisticsTime='" + statisticsTime + '\'' +
                ", accountName='" + accountName + '\'' +
                ", supplyMode='" + supplyMode + '\'' +
                ", varieties='" + varieties + '\'' +
                ", mainSalesRegional='" + mainSalesRegional + '\'' +
                ", aidedSalesRegionalOne='" + aidedSalesRegionalOne + '\'' +
                ", aidedSalesRegionalTwo='" + aidedSalesRegionalTwo + '\'' +
                ", steelMills='" + steelMills + '\'' +
                ", annualAgreementVolume='" + annualAgreementVolume + '\'' +
                ", orderMount=" + orderMount +
                ", protocolOrderMount=" + protocolOrderMount +
                '}';
    }
}
