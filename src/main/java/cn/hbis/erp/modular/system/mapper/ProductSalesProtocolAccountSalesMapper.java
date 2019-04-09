package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ProductSalesProtocolAccountSales;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品总销量及销售公司协议户销量统计 Mapper 接口
 * </p>
 *
 *
 */
public interface ProductSalesProtocolAccountSalesMapper extends BaseMapper<ProductSalesProtocolAccountSales> {

    /**
     * 根据条件查询产品总销量及销售公司协议户销量统计
     */
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("varieties") String varieties,
                                   @Param("beginTime") String beginTime, @Param("endTime") String endTime);
    /**
     * 导出 产品总销量及销售公司协议户销量统计
     */
    List<Map<String, Object>> list(@Param("varieties") String varieties, @Param("beginTime") String beginTime,
                                   @Param("endTime") String endTime);
}
