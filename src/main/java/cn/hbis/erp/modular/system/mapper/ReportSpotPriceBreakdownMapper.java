package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ReportSpotPriceBreakdown;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 建材北京市场现货价格 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface ReportSpotPriceBreakdownMapper extends BaseMapper<ReportSpotPriceBreakdown> {

    //private final static String NAME_SPACE="REPORT_SPOT_PRICE_BREAKDOWN.";

    List<Map> queryDayList(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth, @Param("company") String company);

    List<Map> queryXunList(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth, @Param("company") String company);

    List<Map> queryMonthList(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth, @Param("company") String company);

    List<Map> queryYearList(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth, @Param("company") String company);
}
