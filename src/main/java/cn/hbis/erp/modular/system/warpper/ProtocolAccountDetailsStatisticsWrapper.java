package cn.hbis.erp.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 协议户明细统计列表的包装
 *
 *
 */
public class ProtocolAccountDetailsStatisticsWrapper extends BaseControllerWrapper {

    public ProtocolAccountDetailsStatisticsWrapper(Map<String, Object> single) {
        super(single);
    }

    public ProtocolAccountDetailsStatisticsWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ProtocolAccountDetailsStatisticsWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ProtocolAccountDetailsStatisticsWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }

}
