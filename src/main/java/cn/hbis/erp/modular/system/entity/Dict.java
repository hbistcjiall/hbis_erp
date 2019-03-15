package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典表
 * </p>
 *
 *
 *
 */
@TableName("sys_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "DICT_ID", type = IdType.UUID)
    private String dictId;
    /**
     * 父级字典id
     */
    @TableField("PID")
    private String pid;
    /**
     * 字典名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 字典的编码
     */
    @TableField("CODE")
    private String code;
    /**
     * 字典描述
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 创建人
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;
    /**
     * 修改人
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private String updateUser;


    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "Dict{" +
                ", dictId=" + dictId +
                ", pid=" + pid +
                ", name=" + name +
                ", code=" + code +
                ", description=" + description +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                "}";
    }
}
