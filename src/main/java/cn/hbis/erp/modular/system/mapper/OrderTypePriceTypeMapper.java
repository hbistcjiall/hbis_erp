package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.OrderTypePriceTypeControl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 订单类型与价格类型对照表 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface OrderTypePriceTypeMapper extends BaseMapper<OrderTypePriceTypeControl> {

    /**
     * 根据条件查询订单类型与价格类型对照表
     */
    Page<Map<String, Object>> List(@Param("page") Page page, @Param("companyId") String companyId,
                                   @Param("varieties") String varieties, @Param("orderType") String orderType,
                                   @Param("priceType") String priceType);
}
