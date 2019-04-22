/**
 * FileName: ScmFilterWrapper
 * Author:   zhangb
 * Date:     2019/4/18 17:11
 */
package cn.hbis.erp.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public class ScmFilterWrapper extends BaseControllerWrapper {

    public ScmFilterWrapper(Page<Map<String, Object>> page) {
        super(page);
    }
    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
