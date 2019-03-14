
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.node.MenuNode;
import cn.hbis.erp.core.log.LogManager;
import cn.hbis.erp.core.log.factory.LogTaskFactory;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.hbis.erp.core.shiro.ShiroUser;
import cn.hbis.erp.modular.system.entity.User;
import cn.hbis.erp.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;

/**
 * 登录控制器
 *
 *
 */
@RestController
@RequestMapping("/")
@Api(value = "LoginController",description = "登录")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到主页
     *
     *
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map index() {
        Map map = new HashMap<>();
        //获取当前用户角色列表
        ShiroUser user = ShiroKit.getUserNotNull();
        List<String> roleList = user.getRoleList();

        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            map.put("msg", "该用户没有角色，无法登陆");
            map.put("page","login");
            return map;
        }

        List<MenuNode> menus = userService.getUserMenuNodes(roleList);
        map.put("menus", menus);
        map.put("page","index");

        return map;
    }

    /**
     * 跳转到登录页面
     *
     *
     */
    @ApiOperation(value = "跳转登录页")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Map login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return this.index();
        } else {
            Map map = new HashMap();
            map.put("page","login");
            return map;
        }
    }

    /**
     * 点击登录执行的动作
     *
     *
     */
    @ApiOperation(value = "执行登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username" ,value = "登录名",dataType ="String" ,required = true),
            @ApiImplicitParam(name = "password" ,value = "密码",dataType ="String" ,required = true),
            @ApiImplicitParam(name = "remember" ,value = "记住密码",dataType ="String" )
    })
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Map loginVali(String username,String password,String remember) {

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        //如果开启了记住我功能
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        //执行shiro登录操作
        currentUser.login(token);

        //登录成功，记录登录日志
        ShiroUser shiroUser = ShiroKit.getUserNotNull();
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

        if (token.isRememberMe()){
            User user = new User();
            user.setAccount(username);
            user.setPassword(password);
            ShiroKit.getSession().setAttribute("pw",user);
        }

        ShiroKit.getSession().setAttribute("sessionFlag", true);
        return this.index();
    }

    /**
     * 退出登录
     *
     *
     */
    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Map logOut() {
        Map map = new HashMap();
        User user = new User();
        user = (User) ShiroKit.getSession().getAttribute("pw");
        if (user != null){
            map.put("userName",user.getAccount());
            map.put("password",user.getPassword());
        }
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUserNotNull().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();

        map.put("msg",1000);
        return map;
    }
}
