package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.constant.cache.Cache;
import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.node.TreeviewNode;
import cn.hbis.erp.core.common.node.ZTreeNode;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.log.LogObjectHolder;
import cn.hbis.erp.core.util.CacheUtil;
import cn.hbis.erp.modular.system.entity.Relation;
import cn.hbis.erp.modular.system.entity.Role;
import cn.hbis.erp.modular.system.mapper.RelationMapper;
import cn.hbis.erp.modular.system.mapper.RoleMapper;
import cn.hbis.erp.modular.system.model.RoleDto;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RelationMapper relationMapper;

    @Resource
    private UserService userService;

    /**
     * 添加角色
     *
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void addRole(Role role) {

        if (ToolUtil.isOneEmpty(role, role.getName(), role.getPid(), role.getDescription())) {
            throw new RequestEmptyException();
        }

        role.setRoleId(null);

        this.save(role);
    }

    /**
     * 编辑角色
     *
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void editRole(RoleDto roleDto) {

        if (ToolUtil.isOneEmpty(roleDto, roleDto.getName(), roleDto.getPid(), roleDto.getDescription())) {
            throw new RequestEmptyException();
        }

        Role old = this.getById(roleDto.getRoleId());
        BeanUtil.copyProperties(roleDto, old);
        this.updateById(old);

        //删除缓存
        CacheUtil.removeAll(Cache.CONSTANT);
    }

    /**
     * 设置某个角色的权限
     *
     * @param roleId 角色id
     * @param ids    权限的id
     */
    @Transactional(rollbackFor = Exception.class)
    public void setAuthority(String roleId, String ids) {

        // 删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);

        // 添加新的权限
        for (String id : Convert.toStrArray(ids.split(","))) {
            Relation relation = new Relation();
            relation.setRoleId(roleId);
            relation.setMenuId(id);
            this.relationMapper.insert(relation);
        }

        // 刷新当前用户的权限
        userService.refreshCurrentUser();
    }

    /**
     * 删除角色
     *
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public void delRoleById(String roleId) {

        if (ToolUtil.isEmpty(roleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //不能删除超级管理员角色
        if (roleId.equals(Const.ADMIN_ROLE_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }

        //缓存被删除的角色名称
        LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));

        //删除角色
        this.roleMapper.deleteById(roleId);

        //删除该角色所有的权限
        this.roleMapper.deleteRolesById(roleId);

        //删除缓存
        CacheUtil.removeAll(Cache.CONSTANT);
    }

    /**
     * 根据条件查询角色列表
     *
     * @return
     */
    public Page<Map<String, Object>> selectRoles(String condition) {
        Page page = PageFactory.defaultPage();
        return this.baseMapper.selectRoles(page, condition);
    }

    /**
     * 删除某个角色的所有权限
     *
     * @param roleId 角色id
     * @return
     */
    public int deleteRolesById(String roleId) {
        return this.baseMapper.deleteRolesById(roleId);
    }

    /**
     * 获取角色列表树
     *
     * @return
     */
    public List<ZTreeNode> roleTreeList() {
        return this.baseMapper.roleTreeList();
    }

    /**
     * 获取角色列表树
     *
     * @return
     */
    public List<ZTreeNode> roleTreeListByRoleId(String[] roleId) {
        return this.baseMapper.roleTreeListByRoleId(roleId);
    }

    /**
     * 获取角色列表树
     *
     * @return
     */
    public List<TreeviewNode> TreeListRole() {
        return this.baseMapper.treeViewNodesRole();
    }
}
