package cn.hbis.erp.modular.system.warpper;


import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public class TargetQuantityManagementWrapper extends BaseControllerWrapper {
    public TargetQuantityManagementWrapper(Map<String, Object> single) {
        super(single);
    }

    public TargetQuantityManagementWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public TargetQuantityManagementWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public TargetQuantityManagementWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }
    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
