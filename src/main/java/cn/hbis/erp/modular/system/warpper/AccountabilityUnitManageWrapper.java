package cn.hbis.erp.modular.system.warpper;


import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;
/**
 * 责任公司管理的包装类
 *
 *
 */
public class AccountabilityUnitManageWrapper extends BaseControllerWrapper {
    public AccountabilityUnitManageWrapper(Map<String, Object> single) {
        super(single);
    }

    public AccountabilityUnitManageWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public AccountabilityUnitManageWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public AccountabilityUnitManageWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }
    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
