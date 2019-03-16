
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.annotion.Permission;
import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.constant.dictmap.MenuDict;
import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.node.ZTreeNode;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.common.page.PageInfo;
import cn.hbis.erp.core.log.LogObjectHolder;
import cn.hbis.erp.modular.system.entity.Menu;
import cn.hbis.erp.modular.system.model.MenuDto;
import cn.hbis.erp.modular.system.service.MenuService;
import cn.hbis.erp.modular.system.service.UserService;
import cn.hbis.erp.modular.system.warpper.MenuWrapper;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 *
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    /**
     * 修该菜单
     *
     *
     */
    @ApiOperation(value = "修改菜单")
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @BussinessLog(value = "修改菜单", key = "name", dict = MenuDict.class)
    public ResponseData edit(MenuDto menu) {
        if (ToolUtil.isEmpty(menu.getMenuId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        Menu menuM = this.menuService.getById(menu.getMenuId());
        LogObjectHolder.me().set(menuM);

        //如果修改了编号，则该菜单的子菜单也要修改对应编号
        this.menuService.updateMenu(menu);

        //刷新当前用户菜单
        this.userService.refreshCurrentUser();

        return SUCCESS_TIP;
    }

    /**
     * 获取菜单列表
     *
     *
     */
    @ApiOperation(value = "获取菜单列表")
    @Permission(Const.ADMIN_NAME)
    @PostMapping("list")
    public Object list(@RequestParam(required = false) String menuName,
                       @RequestParam(required = false) String level,
                       @RequestParam(required = false) String menuId,
                       @RequestParam String limit, @RequestParam String page) {
        Page<Map<String, Object>> menus = this.menuService.selectMenus(menuName, level, menuId);
        Page<Map<String, Object>> wrap = new MenuWrapper(menus).wrap();
        return PageFactory.createPageInfo(wrap);
    }

    /**
     * 获取菜单列表（s树形）
     *
     *
     */
    @ApiOperation(value = "获取菜单列表（s树形）")
    @Permission(Const.ADMIN_NAME)
    @PostMapping("listTree")
    public Object listTree(@RequestParam(required = false) String menuName,
                           @RequestParam(required = false) String level) {
        List<Map<String, Object>> menus = this.menuService.selectMenuTree(menuName, level);
        List<Map<String, Object>> menusWrap = new MenuWrapper(menus).wrap();

        PageInfo result = new PageInfo();
        result.setData(menusWrap);
        return result;
    }

    /**
     * 新增菜单
     *
     *
     */
    @ApiOperation(value = "新增菜单")
    @Permission(Const.ADMIN_NAME)
    @PostMapping("add")
    @BussinessLog(value = "菜单新增", key = "name", dict = MenuDict.class)
    public ResponseData add(MenuDto menu) {
        this.menuService.addMenu(menu);
        return SUCCESS_TIP;
    }

    /**
     * 删除菜单
     *
     *
     */
    @ApiOperation(value = "删除菜单")
    @Permission(Const.ADMIN_NAME)
    @PostMapping("remove")
    @BussinessLog(value = "删除菜单", key = "menuId", dict = MenuDict.class)
    public ResponseData remove(@RequestParam String menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //缓存菜单的名称
        LogObjectHolder.me().set(ConstantFactory.me().getMenuName(menuId));

        this.menuService.delMenuContainSubMenus(menuId);

        return SUCCESS_TIP;
    }

    /**
     * 查看菜单
     *
     *
     */
    @ApiOperation(value = "查看菜单")
    @PostMapping("view")
    public ResponseData view(String menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Menu menu = this.menuService.getById(menuId);
        return ResponseData.success(menu);
    }

    /**
     * 获取菜单信息
     *
     *
     */
    @ApiOperation(value = "查看菜单")
    @PostMapping("getMenuInfo")
    @Permission(Const.ADMIN_NAME)
    public ResponseData getMenuInfo(@RequestParam String menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        Menu menu = this.menuService.getById(menuId);
        LogObjectHolder.me().set(menu);

        MenuDto menuDto = new MenuDto();
        BeanUtil.copyProperties(menu, menuDto);

        //设置pid和父级名称
        menuDto.setPid(ConstantFactory.me().getMenuIdByCode(menuDto.getPcode()));
        menuDto.setPcodeName(ConstantFactory.me().getMenuNameByCode(menuDto.getPcode()));

        return ResponseData.success(menuDto);
    }

    /**
     * 获取菜单列表(首页用)
     *
     *
     */
    @ApiOperation(value = "获取菜单列表(首页用)")
    @PostMapping("menuTreeList")
    public List<ZTreeNode> menuTreeList() {
        return this.menuService.menuTreeList();
    }

    /**
     * 获取菜单列表(选择父级菜单用)
     *
     *
     */
    @ApiOperation(value = "获取菜单列表(选择父级菜单用)")
    @PostMapping("selectMenuTreeList")
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取角色的菜单列表
     *
     *
     */
    @ApiOperation(value = "获取角色的菜单列表")
    @PostMapping("menuTreeListByRoleId")
    public List<ZTreeNode> menuTreeListByRoleId(@RequestParam String roleId) {
        List<String> menuIds = this.menuService.getMenuIdsByRoleId(roleId);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> menuTreeList = this.menuService.menuTreeList();
            menuTreeList.add(ZTreeNode.createParent());
            return menuTreeList;
        } else {
            List<ZTreeNode> menuTreeList = this.menuService.menuTreeListByMenuIds(menuIds);
            menuTreeList.add(ZTreeNode.createParent());
            return menuTreeList;
        }
    }

}
