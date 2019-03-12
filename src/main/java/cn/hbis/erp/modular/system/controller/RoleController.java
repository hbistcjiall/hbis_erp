
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.annotion.Permission;
import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.constant.dictmap.RoleDict;
import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.node.ZTreeNode;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.log.LogObjectHolder;
import cn.hbis.erp.modular.system.entity.Role;
import cn.hbis.erp.modular.system.entity.User;
import cn.hbis.erp.modular.system.model.RoleDto;
import cn.hbis.erp.modular.system.service.RoleService;
import cn.hbis.erp.modular.system.service.UserService;
import cn.hbis.erp.modular.system.warpper.RoleWrapper;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static String PREFIX = "/modular/system/role";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 跳转到角色列表页面
     *
     *
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/role.html";
    }

    /**
     * 跳转到添加角色
     *
     *
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
        return PREFIX + "/role_add.html";
    }

    /**
     * 跳转到修改角色
     *
     *
     */
    @Permission
    @RequestMapping(value = "/role_edit")
    public String roleEdit(@RequestParam String roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleService.getById(roleId);
        LogObjectHolder.me().set(role);
        return PREFIX + "/role_edit.html";
    }

    /**
     * 跳转到权限分配
     *
     *
     */
    @Permission
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") String roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        return PREFIX + "/role_assign.html";
    }

    /**
     * 获取角色列表
     *
     *
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "roleName", required = false) String roleName) {
        Page<Map<String, Object>> roles = this.roleService.selectRoles(roleName);
        Page<Map<String, Object>> wrap = new RoleWrapper(roles).wrap();
        return PageFactory.createPageInfo(wrap);
    }

    /**
     * 角色新增
     *
     *
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData add(Role role) {
        this.roleService.addRole(role);
        return SUCCESS_TIP;
    }

    /**
     * 角色修改
     *
     *
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData edit(RoleDto roleDto) {
        this.roleService.editRole(roleDto);
        return SUCCESS_TIP;
    }

    /**
     * 删除角色
     *
     *
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除角色", key = "roleId", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData remove(@RequestParam String roleId) {
        this.roleService.delRoleById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 查看角色
     *
     *
     */
    @RequestMapping(value = "/view/{roleId}")
    @ResponseBody
    public ResponseData view(@PathVariable String roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleService.getById(roleId);
        Map<String, Object> roleMap = BeanUtil.beanToMap(role);

        String  pid = role.getPid();
        String pName = ConstantFactory.me().getSingleRoleName(pid);
        roleMap.put("pName", pName);

        return ResponseData.success(roleMap);
    }

    /**
     * 配置权限
     *
     *
     */
    @RequestMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData setAuthority(@RequestParam("roleId") String roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 获取角色列表
     *
     *
     */
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleService.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色列表，通过用户id
     *
     *
     */
    @RequestMapping(value = "/roleTreeListByUserId",method = RequestMethod.POST)
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@RequestParam("userId") String userId) {
        User theUser = this.userService.getById(userId);
        String roleId = theUser.getRoleId();
        if (ToolUtil.isEmpty(roleId)) {
            return this.roleService.roleTreeList();
        } else {
            String[] strArray = roleId.split(",");
            return this.roleService.roleTreeListByRoleId(strArray);
        }
    }

}
