package cn.hbis.erp.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 产品总销量及销售公司协议户销量统计的包装
 *
 *
 */
public class ProductSalesProtocolAccountSalesWrapper extends BaseControllerWrapper {

    public ProductSalesProtocolAccountSalesWrapper(Map<String, Object> single) {
        super(single);
    }

    public ProductSalesProtocolAccountSalesWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ProductSalesProtocolAccountSalesWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ProductSalesProtocolAccountSalesWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
