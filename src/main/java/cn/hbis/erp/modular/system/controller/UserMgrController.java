
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.config.properties.HbisProperties;
import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.annotion.Permission;
import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.core.common.constant.state.ManagerStatus;
import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.hbis.erp.core.util.DataScopeS;
import cn.hbis.erp.modular.system.entity.User;
import cn.hbis.erp.modular.system.factory.UserFactory;
import cn.hbis.erp.modular.system.model.UserDto;
import cn.hbis.erp.modular.system.service.UserService;
import cn.hbis.erp.modular.system.warpper.UserWrapper;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 *
 */
@RestController
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {

    @Autowired
    private HbisProperties hbisProperties;

    @Autowired
    private UserService userService;

    /**
     * 获取用户详情
     *
     *
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new RequestEmptyException();
        }

        this.userService.assertAuth(userId);
        User user = this.userService.getById(userId);
        Map<String, Object> map = UserFactory.removeUnSafeFields(user);

        HashMap<Object, Object> hashMap = CollectionUtil.newHashMap();
        hashMap.putAll(map);
        hashMap.put("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        hashMap.put("deptName", ConstantFactory.me().getDeptName(user.getDeptId().toString()));

        return ResponseData.success(hashMap);
    }

    /**
     * 修改当前用户的密码
     *
     *
     */
    @ApiOperation(value = "修改当前用户的密码")
    @RequestMapping("/changePwd")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword" ,value = "旧密码",dataType ="String" ),
            @ApiImplicitParam(name = "newPassword" ,value = "新密码",dataType ="String" )
    })
    public Object changePwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        if (ToolUtil.isOneEmpty(oldPassword, newPassword)) {
            throw new RequestEmptyException();
        }
        this.userService.changePwd(oldPassword, newPassword);
        return SUCCESS_TIP;
    }

    /**
     * 查询管理员列表
     *
     *
     */
    @ApiOperation(value = "查询管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name" ,value = "姓名",dataType ="String" ),
            @ApiImplicitParam(name = "timeLimit" ,value = "时间区间",dataType ="String" ),
            @ApiImplicitParam(name = "deptId" ,value = "部门ID",dataType ="String" ),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @Permission
    @ResponseBody
    public Object list(String name, String timeLimit, String deptId, String limit, String page) {

        //拼接查询条件
        String beginTime = "";
        String endTime = "";

        if (ToolUtil.isNotEmpty(timeLimit)) {
            String[] split = timeLimit.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }
        String dId = null;
        if (ToolUtil.isNotEmpty(deptId)){
           dId = deptId;
        }

        if (ShiroKit.isAdmin()) {
            Page<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, dId);
            Page wrapped = new UserWrapper(users).wrap();
            return PageFactory.createPageInfo(wrapped);
        } else {
            DataScopeS dataScope = new DataScopeS(ShiroKit.getDeptDataScope());
            Page<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, dId);
            Page wrapped = new UserWrapper(users).wrap();
            return PageFactory.createPageInfo(wrapped);
        }
    }

    /**
     * 添加管理员
     *
     *
     */
    @ApiOperation(value = "添加管理员")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @BussinessLog(value = "添加管理员", key = "account")
    @Permission(Const.ADMIN_NAME)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name" ,value = "姓名",dataType ="String" ),
            @ApiImplicitParam(name = "account" ,value = "登录名",dataType ="String" ),
            @ApiImplicitParam(name = "password" ,value = "密码",dataType ="String" ),
            @ApiImplicitParam(name = "birthday" ,value = "生日",dataType ="String" ),
            @ApiImplicitParam(name = "sex" ,value = "性别",dataType ="String" ),
            @ApiImplicitParam(name = "email" ,value = "邮箱地址",dataType ="String" ),
            @ApiImplicitParam(name = "phone" ,value = "电话号",dataType ="String" ),
            @ApiImplicitParam(name = "roleId" ,value = "角色ID",dataType ="String" ),
            @ApiImplicitParam(name = "deptId" ,value = "部门ID",dataType ="String" ),
            @ApiImplicitParam(name = "avatar" ,value = "头像",dataType ="String" )
    })
    @ResponseBody
    public ResponseData add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.addUser(user);
        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     *
     *
     */
    @ApiOperation(value = "修改管理员")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @BussinessLog(value = "修改管理员", key = "account")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户ID",dataType ="String" ),
            @ApiImplicitParam(name = "name" ,value = "姓名",dataType ="String" ),
            @ApiImplicitParam(name = "account" ,value = "登录名",dataType ="String" ),
            @ApiImplicitParam(name = "password" ,value = "密码",dataType ="String" ),
            @ApiImplicitParam(name = "birthday" ,value = "生日",dataType ="String" ),
            @ApiImplicitParam(name = "sex" ,value = "性别",dataType ="String" ),
            @ApiImplicitParam(name = "email" ,value = "邮箱地址",dataType ="String" ),
            @ApiImplicitParam(name = "phone" ,value = "电话号",dataType ="String" ),
            @ApiImplicitParam(name = "roleId" ,value = "角色ID",dataType ="String" ),
            @ApiImplicitParam(name = "deptId" ,value = "部门ID",dataType ="String" ),
            @ApiImplicitParam(name = "avatar" ,value = "头像",dataType ="String" )
    })
    public ResponseData edit(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.editUser(user);
        return SUCCESS_TIP;
    }

    /**
     * 删除管理员（逻辑删除）
     *
     *
     *
     */
    @ApiOperation(value = "删除管理员（逻辑删除）")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @BussinessLog(value = "删除管理员")
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.deleteUser(userId);
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     *
     *
     *
     */
    @RequestMapping(value = "/view",method = RequestMethod.GET)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户ID",dataType ="String" )})
    public User view(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        return this.userService.getById(userId);
    }

    /**
     * 重置管理员的密码
     *
     *
     *
     */
    @ApiOperation(value = "重置管理员的密码")
    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    @BussinessLog(value = "重置管理员密码", key = "userId")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户ID",dataType ="String" )})
    public ResponseData reset(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        User user = this.userService.getById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        this.userService.updateById(user);
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     *
     *
     *
     */
    @ApiOperation(value = "冻结/解冻用户")
    @RequestMapping(value = "/freeze",method = RequestMethod.POST)
    @BussinessLog(value = "冻结/解冻用户", key = "userId")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户ID",dataType ="String" ),
            @ApiImplicitParam(name = "status" ,value = "账户状态",dataType ="String" )
    })
    public ResponseData freeze(@RequestParam String userId,@RequestParam String status) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (Long.valueOf(userId).equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        this.userService.assertAuth(userId);
        if (status.equals("FREEZE")){
            this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        }else if (status.equals("ENABLE")){ //解冻
            this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        }
        return SUCCESS_TIP;
    }

    /**
     * 分配角色
     *
     *
     *
     */
    @RequestMapping("/setRole")
    @BussinessLog(value = "分配角色", key = "userId,roleIds")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData setRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        this.userService.assertAuth(userId);
        this.userService.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }

    /**
     * 上传图片
     *
     *
     *
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = hbisProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;
    }
}
