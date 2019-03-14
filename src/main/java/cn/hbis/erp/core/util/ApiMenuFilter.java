
package cn.hbis.erp.core.util;

import cn.hbis.erp.config.properties.HbisProperties;
import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.node.MenuNode;
import cn.stylefeng.roses.core.util.SpringContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * api接口文档显示过滤
 *
 *
 */
public class ApiMenuFilter extends MenuNode {

    public static List<MenuNode> build(List<MenuNode> nodes) {

        //如果关闭了接口文档,则不显示接口文档菜单
        HbisProperties hbisProperties = SpringContextHolder.getBean(HbisProperties.class);
        if (!hbisProperties.getSwaggerOpen()) {
            List<MenuNode> menuNodesCopy = new ArrayList<>();
            for (MenuNode menuNode : nodes) {
                if (Const.API_MENU_NAME.equals(menuNode.getName())) {
                    continue;
                } else {
                    menuNodesCopy.add(menuNode);
                }
            }
            nodes = menuNodesCopy;
        }

        return nodes;
    }
}
