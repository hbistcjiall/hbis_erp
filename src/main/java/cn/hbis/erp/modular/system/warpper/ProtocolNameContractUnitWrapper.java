package cn.hbis.erp.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 协议户名与合同单位对照的包装
 *
 *
 */
public class ProtocolNameContractUnitWrapper extends BaseControllerWrapper {

    public ProtocolNameContractUnitWrapper(Map<String, Object> single) {
        super(single);
    }

    public ProtocolNameContractUnitWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public ProtocolNameContractUnitWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public ProtocolNameContractUnitWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
