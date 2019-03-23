package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ReportVarietySteelBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 子公司品种钢情况 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface ReportTechnologyMapper extends BaseMapper<ReportVarietySteelBean> {

    //private String NAMESPACE="REPORT_TECHNOLOGY_COMPLETION";

    List<ReportVarietySteelBean> subsidiaryVarietySteel(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List item_subsidiaryVarietySteel_count(@Param("companyId") String companyId, @Param("startDate") String startDate, @Param("endDate") String endDate);
    List item_subsidiaryVarietySteel_data(@Param("companyId") String companyId, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
