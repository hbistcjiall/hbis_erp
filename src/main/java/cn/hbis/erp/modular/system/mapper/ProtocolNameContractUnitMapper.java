package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ProtocolNameContractUnit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 协议户名与合同单位对照表 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface ProtocolNameContractUnitMapper extends BaseMapper<ProtocolNameContractUnit> {

    /**
     * 根据条件查询协议户名与合同单位对照表
     */
    Page<Map<String, Object>> List(@Param("page") Page page, @Param("accontName") String accontName, @Param("contractUnit") String contractUnit);

}
