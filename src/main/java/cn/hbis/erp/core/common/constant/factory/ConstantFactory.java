package cn.hbis.erp.core.common.constant.factory;

import cn.hbis.erp.core.common.constant.cache.Cache;
import cn.hbis.erp.core.common.constant.cache.CacheKey;
import cn.hbis.erp.core.common.constant.state.ManagerStatus;
import cn.hbis.erp.core.common.constant.state.MenuStatus;
import cn.hbis.erp.core.log.LogObjectHolder;
import cn.hbis.erp.modular.system.entity.*;
import cn.hbis.erp.modular.system.mapper.*;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量的生产工厂
 *
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
    private NoticeMapper noticeMapper = SpringContextHolder.getBean(NoticeMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    public String getUserNameById(String userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    @Override
    public String getUserAccountById(String userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    public String getRoleName(String roleIds) {
        if (ToolUtil.isEmpty(roleIds)) {
            return "";
        }
        String[] roles = Convert.toStrArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (String role : roles) {
            Role roleObj = roleMapper.selectById(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(String roleId) {
        if ("0" == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(String roleId) {
        if ("0" == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getDescription();
        }
        return "";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(String deptId) {
        if (deptId == null) {
            return "";
        } else if (deptId.equals("0")) {
            return "顶级";
        } else {
            Dept dept = deptMapper.selectById(deptId);
            if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullName())) {
                return dept.getFullName();
            }
            return "";
        }
    }

    @Override
    public String getMenuNames(String menuIds) {
        String[] menus = Convert.toStrArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (String menu : menus) {
            Menu menuObj = menuMapper.selectById(menu);
            if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    @Override
    public String getMenuName(String menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        } else {
            Menu menu = menuMapper.selectById(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    @Override
    public Menu getMenuByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return new Menu();
        } else if (code.equals("0")) {
            return new Menu();
        } else {
            Menu param = new Menu();
            param.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(param);
            Menu menu = menuMapper.selectOne(queryWrapper);
            if (menu == null) {
                return new Menu();
            } else {
                return menu;
            }
        }
    }

    @Override
    public String getMenuNameByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "";
        } else if (code.equals("0")) {
            return "顶级";
        } else {
            Menu param = new Menu();
            param.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(param);
            Menu menu = menuMapper.selectOne(queryWrapper);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    @Override
    public String  getMenuIdByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "0";
        } else if (code.equals("0")) {
            return "0";
        } else {
            Menu menu = new Menu();
            menu.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(menu);
            Menu tempMenu = this.menuMapper.selectOne(queryWrapper);
            return tempMenu.getMenuId();
        }
    }

    @Override
    public String getDictName(String dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Dict dict = dictMapper.selectById(dictId);
            if (dict == null) {
                return "";
            } else {
                return dict.getName();
            }
        }
    }

    @Override
    public String getNoticeTitle(String dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Notice notice = noticeMapper.selectById(dictId);
            if (notice == null) {
                return "";
            } else {
                return notice.getTitle();
            }
        }
    }

    @Override
    public String getDictsByName(String name, String code) {
        Dict temp = new Dict();
        temp.setName(name);
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>(temp);
        Dict dict = dictMapper.selectOne(queryWrapper);
        if (dict == null) {
            return "";
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            wrapper = wrapper.eq("PID", dict.getDictId());
            List<Dict> dicts = dictMapper.selectList(wrapper);
            for (Dict item : dicts) {
                if (item.getCode() != null && item.getCode().equals(code)) {
                    return item.getName();
                }
            }
            return "";
        }
    }

    @Override
    public String getSexName(String sexCode) {
        return getDictsByName("性别", sexCode);
    }

    @Override
    public String getStatusName(String status) {
        return ManagerStatus.getDescription(status);
    }

    @Override
    public String getMenuStatusName(String status) {
        return MenuStatus.getDescription(status);
    }

    @Override
    public List<Dict> findInDict(String id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            List<Dict> dicts = dictMapper.selectList(wrapper.eq("PID", id));
            if (dicts == null || dicts.size() == 0) {
                return null;
            } else {
                return dicts;
            }
        }
    }

    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    @Override
    public List<String> getSubDeptId(String deptId) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper = wrapper.like("PIDS", "%[" + deptId + "]%");
        List<Dept> depts = this.deptMapper.selectList(wrapper);

        ArrayList<String> deptids = new ArrayList<>();

        if (depts != null && depts.size() > 0) {
            for (Dept dept : depts) {
                deptids.add(dept.getDeptId());
            }
        }

        return deptids;
    }

    @Override
    public List<String> getParentDeptIds(String deptId) {
        Dept dept = deptMapper.selectById(deptId);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<String> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(StrUtil.removeSuffix(StrUtil.removePrefix(s, "["), "]"));
        }
        return parentDeptIds;
    }


}
