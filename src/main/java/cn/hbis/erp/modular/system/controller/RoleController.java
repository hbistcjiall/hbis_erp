
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表
     *
     *
     */
    @ApiOperation(value = "获取角色列表")
    @Permission
    @PostMapping("list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    public Object listRole(String roleName, String limit, String page) {
        Page<Map<String, Object>> roles = this.roleService.selectRoles(roleName);
        Page<Map<String, Object>> wrap = new RoleWrapper(roles).wrap();
        return PageFactory.createPageInfo(wrap);
    }

    /**
     * 角色新增
     *
     *
     */
    @ApiOperation(value = "角色新增")
    @PostMapping("addRole")
    @BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", dataType = "String"),
            @ApiImplicitParam(name = "pid", value = "父ID", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "提示", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "序号", dataType = "Integer")
    })
    public ResponseData addRole(Role role) {
        this.roleService.addRole(role);
        return SUCCESS_TIP;
    }

    /**
     * 角色修改
     *
     *
     */
    @ApiOperation(value = "角色修改")
    @PostMapping("editRole")
    @BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", dataType = "String"),
            @ApiImplicitParam(name = "pid", value = "父ID", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "提示", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "序号", dataType = "Integer")
    })
    public ResponseData editRole(RoleDto roleDto) {
        if (ToolUtil.isEmpty(roleDto.getRoleId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.editRole(roleDto);
        return SUCCESS_TIP;
    }

    /**
     * 删除角色
     *
     *
     */
    @ApiOperation(value = "删除角色")
    @PostMapping("removeRole")
    @BussinessLog(value = "删除角色", key = "roleId", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId" ,value = "角色ID",dataType ="String" )
    })
    public ResponseData removeRole(@RequestParam String roleId) {
        this.roleService.delRoleById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 查看角色
     *
     *
     */
    @ApiOperation(value = "查看角色")
    @PostMapping(value = "viewRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId" ,value = "角色ID",dataType ="String" )
    })
    public ResponseData viewRole(@RequestParam String roleId) {
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
    @ApiOperation(value = "配置权限")
    @PostMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId" ,value = "角色ID",dataType ="String" ),
            @ApiImplicitParam(name = "ids" ,value = "权限ID集合",dataType ="String" )
    })
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
    @ApiOperation(value = "获取角色列表")
    @PostMapping("/roleTreeList")
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
    @ApiOperation(value = "获取角色列表，通过用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户ID",dataType ="String" )
    })
    @PostMapping("/roleTreeListByUserId")
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
