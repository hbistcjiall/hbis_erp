
package cn.hbis.erp.modular.system.warpper;

import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.modular.system.entity.Dict;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 字典列表的包装
 *
 *
 */
public class DictWrapper extends BaseControllerWrapper {

    public DictWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        StringBuilder detail = new StringBuilder();
        String  id = map.get("DICTID").toString();
        List<Dict> dicts = ConstantFactory.me().findInDict(id);
        if (dicts != null) {
            for (Dict dict : dicts) {
                detail.append(dict.getCode()).append(":").append(dict.getName()).append(",");
            }
            map.put("detail", StrUtil.removeSuffix(detail.toString(), ","));
        }
    }
}
