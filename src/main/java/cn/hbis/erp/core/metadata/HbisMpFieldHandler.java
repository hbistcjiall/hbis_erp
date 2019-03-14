
package cn.hbis.erp.core.metadata;

import cn.hbis.erp.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.metadata.CustomMetaObjectHandler;
import org.springframework.stereotype.Component;

/**
 * 字段填充器
 *
 *
 */
@Component
public class HbisMpFieldHandler extends CustomMetaObjectHandler {

    @Override
    protected Object getUserUniqueId() {
        try {

            return ShiroKit.getUser().getId();

        } catch (Exception e) {

            //如果获取不到当前用户就存空id
            return "";
        }
    }
}
