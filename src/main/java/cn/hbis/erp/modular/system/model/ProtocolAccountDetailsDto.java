package cn.hbis.erp.modular.system.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 协议户明细信息
 *
 *
 */
@Data
public class ProtocolAccountDetailsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String protocolAccountId;
    /**
     * 协议年份
     */
    private String protocolYear;
    /**
     * 用户名称（全称）
     */
    private String accountName;
    /**
     * 品种
     */
    private String varieties;
    /**
     * 主销售区域
     */
    private String mainSalesRegional;
    /**
     * 辅助销售区域一
     */
    private String aidedSalesRegionalOne;
    /**
     * 辅助销售区域二
     */
    private String aidedSalesRegionalTwo;
    /**
     * 钢厂
     */
    private String steelMills;
    /**
     * 年协议量（吨）
     */
    private String annualAgreementVolume;
}