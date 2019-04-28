package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiarsi
 * @since 2019-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SCM_FILTER")
@ApiModel(value="ScmFilter对象", description="")
public class ScmFilter extends Model<ScmFilter> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID" )
    @TableId(value = "ID",type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "业务编码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "业务名称")
    @TableField("F_NAME")
    private String fName;

    @ApiModelProperty(value = "过滤表")
    @TableField("TABLE_NAME")
    private String tableName;

    @ApiModelProperty(value = "过滤字段")
    @TableField("F_COLUMN")
    private String fColumn;

    @ApiModelProperty(value = "字段值")
    @TableField("C_VALUE")
    private String cValue;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remark;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATER")
    private String creater;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREAT_TIME")
    private Date creatTime;

    @ApiModelProperty(value = "修改人")
    @TableField("UPDATER")
    private String updater;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.code;
    }

}
