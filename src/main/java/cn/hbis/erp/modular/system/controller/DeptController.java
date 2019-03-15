
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.annotion.Permission;
import cn.hbis.erp.core.common.constant.dictmap.DeptDict;
import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.core.common.node.TreeviewNode;
import cn.hbis.erp.core.common.node.ZTreeNode;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.log.LogObjectHolder;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.hbis.erp.core.shiro.ShiroUser;
import cn.hbis.erp.modular.system.entity.Dept;
import cn.hbis.erp.modular.system.model.DeptDto;
import cn.hbis.erp.modular.system.service.DeptService;
import cn.hbis.erp.modular.system.warpper.DeptTreeWrapper;
import cn.hbis.erp.modular.system.warpper.DeptWrapper;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.treebuild.DefaultTreeBuildFactory;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 部门控制器
 *
 *
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {


    @Autowired
    private DeptService deptService;


    /**
     * 获取部门的tree列表，ztree格式
     *
     *
     */
    @ApiOperation(value = "获取部门的tree列表，ztree格式")
    @PostMapping("tree")
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 获取部门的tree列表，treeView格式
     *
     *
     */
    @ApiOperation(value = "获取部门的tree列表，treeView格式")
    @PostMapping("treeView")
    public List<TreeviewNode> treeView() {
        List<TreeviewNode> treeViewNodes = this.deptService.treeviewNodes();

        //构建树
        DefaultTreeBuildFactory<TreeviewNode> factory = new DefaultTreeBuildFactory<>();
        factory.setRootParentId("0");
        List<TreeviewNode> results = factory.doTreeBuild(treeViewNodes);

        //把子节点为空的设为null
        DeptTreeWrapper.clearNull(results);

        return results;
    }

    /**
     * 新增部门
     *
     *
     */
    @ApiOperation(value = "添加部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门ID", dataType = "String"),
            @ApiImplicitParam(name = "pid", value = "父ID", dataType = "String"),
            @ApiImplicitParam(name = "simpleName", value = "部门简称", dataType = "String"),
            @ApiImplicitParam(name = "fullName", value = "部门全称", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "部门描述", dataType = "String")
    })
    @PostMapping("add")
    @Permission
    @BussinessLog(value = "添加部门", key = "simpleName", dict = DeptDict.class)
    public ResponseData add(Dept dept) {
        System.out.println();
        this.deptService.addDept(dept);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有部门列表
     *
     *
     */
    @ApiOperation(value = "获取所有部门列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "部门简称或全称", dataType = "String"),
            @ApiImplicitParam(name = "deptId", value = "部门ID", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping("list")
    @Permission
    public Object list(String condition, String deptId, String limit, String page) {
        Page<Map<String, Object>> list = this.deptService.list(condition, deptId);
        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return PageFactory.createPageInfo(wrap);
    }

    /**
     * 部门详情
     *
     *
     */
    @ApiOperation(value = "部门详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门ID", dataType = "String")
    })
    @PostMapping("detail")
    @Permission
    public Object detail(String deptId) {
        Dept dept = deptService.getById(deptId);
        DeptDto deptDto = new DeptDto();
        BeanUtil.copyProperties(dept, deptDto);
        deptDto.setPName(ConstantFactory.me().getDeptName(deptDto.getPid().toString()));
        return deptDto;
    }

    /**
     * 修改部门
     *
     *
     */
    @ApiOperation(value = "修改部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门ID", dataType = "String"),
            @ApiImplicitParam(name = "pid", value = "父ID", dataType = "String"),
            @ApiImplicitParam(name = "simpleName", value = "部门简称", dataType = "String"),
            @ApiImplicitParam(name = "fullName", value = "部门全称", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "部门描述", dataType = "String")
    })
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @Permission
    @BussinessLog(value = "修改部门", key = "simpleName", dict = DeptDict.class)
    public ResponseData update(Dept dept) {
        if (ToolUtil.isEmpty(dept.getDeptId())) {
            throw new RequestEmptyException();
        }

        //缓存部门修改前详细信息
        Dept dep = deptService.getById(dept.getDeptId());
        LogObjectHolder.me().set(dep);

        deptService.editDept(dept);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     *
     *
     */
    @ApiOperation(value = "删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptId", value = "部门ID", dataType = "String")
    })
    @PostMapping("delete")
    @Permission
    @BussinessLog(value = "删除部门", key = "deptId", dict = DeptDict.class)
    public ResponseData delete(String deptId) {

        //缓存被删除的部门名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));

        deptService.deleteDept(deptId);

        return SUCCESS_TIP;
    }

}
