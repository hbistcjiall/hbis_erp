package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.core.common.node.MenuNode;
import cn.hbis.erp.core.common.node.ZTreeNode;
import cn.hbis.erp.modular.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据条件查询菜单
     *
     * @return
     */
    Page<Map<String, Object>> selectMenus(@Param("page") Page page, @Param("condition") String condition, @Param("level") String level, @Param("menuId") String menuId, @Param("code") String code);

    /**
     * 根据条件查询菜单
     *
     * @return
     */
    List<String> getMenuIdsByRoleId(@Param("roleId") String roleId);

    /**
     * 获取菜单列表树
     *
     * @return
     */
    List<ZTreeNode> menuTreeList();

    /**
     * 获取菜单列表树
     *
     * @return
     */
    List<ZTreeNode> menuTreeListByMenuIds(List<String> menuIds);

    /**
     * 删除menu关联的relation
     *
     * @param menuId
     * @return
     */
    int deleteRelationByMenu(String menuId);

    /**
     * 获取资源url通过角色id
     *
     * @param roleId
     * @return
     */
    List<String> getResUrlsByRoleId(String roleId);

    /**
     * 根据角色获取菜单
     *
     * @param roleIds
     * @return
     */
    List<MenuNode> getMenusByRoleIds(List<String> roleIds);

    /**
     * 查询菜单树形列表
     *
     *
     */
    List<Map<String, Object>> selectMenuTree(@Param("condition") String condition, @Param("level") String level);


}
