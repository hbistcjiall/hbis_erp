package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.ID_WORKER;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 *
 *
 */
@TableName("sys_relation")
public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "RELATION_ID", type = IdType.ID_WORKER_STR)
    private String relationId;
    /**
     * 菜单id
     */
    @TableField("MENU_ID")
    private String menuId;
    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private String roleId;


    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Relation{" +
                ", relationId=" + relationId +
                ", menuId=" + menuId +
                ", roleId=" + roleId +
                "}";
    }
}
