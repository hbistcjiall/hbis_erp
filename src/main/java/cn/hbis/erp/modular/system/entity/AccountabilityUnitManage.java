package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 责任公司管理表
 * </p>
 *
 *
 *
 */
@TableName("scm_accountability_unit_manage")
@Data
public class AccountabilityUnitManage implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId(value = "ACCOUNTABILITY_UNIT_ID", type = IdType.UUID)
    private String accountabilityunitid;
    /**
     * 编码
     */
    @TableField("ACCOUNTABILITY_UNIT_CODE")
    private String accountabilityunitcode;
    /**
     * 责任单位名称
     */
    @TableField("ACCOUNTABILITY_UNIT_NAME")
    private String accountabilityunitname;
    /**
     * 删除状态
     */
    @TableField("DELETESTATUS")
    private String deletestatus;
}
