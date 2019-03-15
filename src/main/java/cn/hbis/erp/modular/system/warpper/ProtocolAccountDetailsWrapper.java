package cn.hbis.erp.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 协议户明细列表的包装
 *
 *
 */
public class ProtocolAccountDetailsWrapper extends BaseControllerWrapper {

    public ProtocolAccountDetailsWrapper(Map<String, Object> single) {
        super(single);
    }

    public ProtocolAccountDetailsWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ProtocolAccountDetailsWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ProtocolAccountDetailsWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
