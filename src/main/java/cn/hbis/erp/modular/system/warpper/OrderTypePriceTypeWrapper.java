package cn.hbis.erp.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 订单类型与价格类型对照的包装
 *
 *
 */
public class OrderTypePriceTypeWrapper extends BaseControllerWrapper {

    public OrderTypePriceTypeWrapper(Map<String, Object> single) {
        super(single);
    }

    public OrderTypePriceTypeWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public OrderTypePriceTypeWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public OrderTypePriceTypeWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
