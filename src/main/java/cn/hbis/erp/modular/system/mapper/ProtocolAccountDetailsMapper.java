package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ProtocolAccountDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 协议户明细表 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface ProtocolAccountDetailsMapper extends BaseMapper<ProtocolAccountDetails> {
    /**
     * 根据条件查询协议户明细列表
     */
    Page<Map<String, Object>> searchProtocolAccountDetailList(@Param("page") Page page, @Param("mainSalesRegional") String mainSalesRegional,
                                         @Param("beginTime") String beginTime, @Param("endTime") String endTime,
                                         @Param("protocolYear") String protocolYear, @Param("steelMills") String steelMills);
}
