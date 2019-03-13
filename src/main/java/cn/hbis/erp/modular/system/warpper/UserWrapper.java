
package cn.hbis.erp.modular.system.warpper;

import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 *
 */
public class UserWrapper extends BaseControllerWrapper {

    public UserWrapper(Map<String, Object> single) {
        super(single);
    }

    public UserWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public UserWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public UserWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("SEXNAME", ConstantFactory.me().getSexName((String) map.get("SEX")));
        map.put("ROLENAME", ConstantFactory.me().getRoleName((String) map.get("ROLEID")));
        map.put("DEPTNAME", ConstantFactory.me().getDeptName((String)map.get("DEPTID")));
        map.put("STATUSNAME", ConstantFactory.me().getStatusName((String) map.get("STATUS")));
    }

}
