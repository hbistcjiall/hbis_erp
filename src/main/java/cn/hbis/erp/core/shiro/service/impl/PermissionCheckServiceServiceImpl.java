
package cn.hbis.erp.core.shiro.service.impl;

import cn.hbis.erp.core.listener.ConfigListener;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.hbis.erp.core.shiro.ShiroUser;
import cn.hbis.erp.core.shiro.service.PermissionCheckService;
import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.roses.core.util.HttpContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * 权限自定义检查
 */
@Service
@Transactional(readOnly = true)
public class PermissionCheckServiceServiceImpl implements PermissionCheckService {

    @Override
    public boolean check(Object[] permissions) {
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return false;
        }
        ArrayList<Object> objects = CollectionUtil.newArrayList(permissions);
        String join = CollectionUtil.join(objects, ",");
        if (ShiroKit.hasAnyRoles(join)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAll() {
        HttpServletRequest request = HttpContext.getRequest();
        ShiroUser user = ShiroKit.getUser();
        if (null == user) {
            return false;
        }
        String requestURI = request.getRequestURI().replaceFirst(ConfigListener.getConf().get("contextPath"), "");
        String[] str = requestURI.split("/");
        if (str.length > 3) {
            requestURI = "/" + str[1] + "/" + str[2];
        }
        if (ShiroKit.hasPermission(requestURI)) {
            return true;
        }
        return false;
    }

}
