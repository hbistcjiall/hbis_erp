package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ProtocolAccountDetailsStatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 协议户明细统计表 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface ProtocolAccountDetailsStatisticsMapper extends BaseMapper<ProtocolAccountDetailsStatistics> {
    /**
     * 根据条件查询协议户明细列表
     */
    Page<Map<String, Object>> List(@Param("page") Page page, @Param("varieties") String varieties,
                                   @Param("beginTime") String beginTime, @Param("endTime") String endTime,
                                   @Param("supplyMode") String supplyMode, @Param("companyIdList") List companyIdList);
    List sumList(@Param("varieties") String varieties,
                                   @Param("beginTime") String beginTime, @Param("endTime") String endTime,
                                   @Param("supplyMode") String supplyMode, @Param("companyIdList") List companyIdList);
}
