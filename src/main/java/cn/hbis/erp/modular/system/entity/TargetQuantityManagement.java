package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("SCM_TARGET_QUANTITY_MANAGEMENT")
@Data
public class TargetQuantityManagement implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "TARGET_QUANTITY_MANAGEMENT_ID", type = IdType.ID_WORKER_STR)
    private String TargetQuantityManagementid;
    /**
     * 删除状态
     */
    @TableField("DELETESTATUS")
    private String deletestatus;
    /**
     * 责任单位
     */
    @TableField("RESPONSIBILITY_UNIT")
    private String responsibilityunit;
    /**
     * 年份
     */
    @TableField("YEAR")
    private String year;
    /**
     * 一月份
     */
     @TableField("JANUARY")
    private String january;
    /**
     * 二月份
     */
    @TableField("FEBRUARY")
    private String february;
    /**
     * 三月份
     */
    @TableField("MARCH")
    private String march;
    /**
     * 四月份
     */
    @TableField("APRIL")
    private String april;
    /**
     * 五月份
     */
    @TableField("MAY")
    private String may;
    /**
     * 六月份
     */
    @TableField("JUNE")
    private String june;
    /**
     * 七月份
     */
    @TableField("JULY")
    private String july;
    /**
     * 八月份
     */
    @TableField("AUGUST")
    private String august;
    /**
     * 九月份
     */
    @TableField("SEPTEMBER")
    private String september;
    /**
     * 十月份
     */
    @TableField("OCTOBER")
    private String october;
    /**
     * 十一月份
     */
    @TableField("NOVEMBER")
    private String november;
    /**
     * 十二月份
     */
    @TableField("DECEMBER")
    private String december;


}
