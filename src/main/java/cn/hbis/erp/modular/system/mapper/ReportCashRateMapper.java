package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ReportCashRate;
import cn.hbis.erp.modular.system.entity.ReportCashRateSummary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户兑现率 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface ReportCashRateMapper extends BaseMapper<ReportCashRateSummary> {

    List<ReportCashRateSummary> getCashRateSummary(@Param("companyId") String companyId, @Param("orderStopDateS") String orderStopDateS,
                                                   @Param("orderStopDateE") String orderStopDateE, @Param("recordDate") String recordDate,
                                                   @Param("summaryType") String summaryType);

    List<ReportCashRateSummary> getCashRateSummaryGrade(@Param("companyId") String companyId, @Param("orderStopDateS") String orderStopDateS,
                                                        @Param("orderStopDateE") String orderStopDateE, @Param("recordDate") String recordDate,
                                                        @Param("summaryType") String summaryType);

    List<String> getCashRateDetail(@Param("companyId") String companyId, @Param("orderStopDateS") String orderStopDateS,
                                   @Param("orderStopDateE") String orderStopDateE, @Param("recordDate") String recordDate,
                                   @Param("summaryType") String summaryType);

    List<ReportCashRateSummary> getCashRateCurve(@Param("companyId") String companyId, @Param("recordDate") String recordDate, @Param("summaryType") String summaryType);

    void insert(@Param("insert") String summaryType);

    void insertSummary(@Param("summaryType") String summaryType);
}
