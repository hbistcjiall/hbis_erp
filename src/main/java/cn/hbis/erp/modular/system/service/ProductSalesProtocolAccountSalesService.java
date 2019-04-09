package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.ProductSalesProtocolAccountSales;
import cn.hbis.erp.modular.system.mapper.ProductSalesProtocolAccountSalesMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品总销量及销售公司协议户销量统计 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ProductSalesProtocolAccountSalesService extends ServiceImpl<ProductSalesProtocolAccountSalesMapper, ProductSalesProtocolAccountSales> {

    @Resource
    private ProductSalesProtocolAccountSalesMapper productSalesProtocolAccountSalesMapper;

    /**
     * 查询产品总销量及销售公司协议户销量统计
     *
     *
     */
    @Async
    public Page<Map<String, Object>> list(String varieties, String beginTime, String endTime) {
        Page page = PageFactory.defaultPage();
        if (beginTime != null){
            beginTime = beginTime.replace("-","");
        }
        if (endTime != null){
            endTime = endTime.replace("-","");
        }
        return this.baseMapper.list(page, varieties, beginTime, endTime);
    }
    /**
     * 导出 产品总销量及销售公司协议户销量统计
     *
     *
     */
    public List<Map<String, Object>> exportList(String varieties, String beginTime, String endTime) {
        if (beginTime != null){
            beginTime = beginTime.replace("-","");
        }
        if (endTime != null){
            endTime = endTime.replace("-","");
        }
        return this.baseMapper.list(varieties, beginTime, endTime);

    }
}
