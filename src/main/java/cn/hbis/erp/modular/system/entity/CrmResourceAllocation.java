package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 资源分配维护表
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CRM_RESOURCE_ALLOCATION")
@ApiModel(value="CrmResourceAllocation对象", description="资源分配维护表")
public class CrmResourceAllocation extends Model<CrmResourceAllocation> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "年份")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "品种编码")
    @TableField("PZ_CODE")
    private String pzCode;

    @ApiModelProperty(value = "品种名称")
    @TableField("PZ_NAME")
    private String pzName;

    @ApiModelProperty(value = "产品类别名称")
    @TableField("CPLB_NAME")
    private String cplbName;

    @ApiModelProperty(value = "产线名称")
    @TableField("CX_NAME")
    private String cxName;

    @ApiModelProperty(value = "分类（1-内贸，2-出口）")
    @TableField("FL")
    private String fl;

    @ApiModelProperty(value = "分类名称")
    @TableField("FL_NAME")
    private String flName;

    @ApiModelProperty(value = "年目标量")
    @TableField("MBL_YEAR")
    private Double mblYear;

    @ApiModelProperty(value = "品种比")
    @TableField("PZB")
    private String pzb;

    @ApiModelProperty(value = "1月计划量")
    @TableField("JAN_MONTH")
    private Double janMonth;

    @ApiModelProperty(value = "2月计划量")
    @TableField("FEB_MONTH")
    private Double febMonth;

    @ApiModelProperty(value = "3月计划量")
    @TableField("MAR_MONTH")
    private Double marMonth;

    @ApiModelProperty(value = "4月计划量")
    @TableField("APR_MONTH")
    private Double aprMonth;

    @ApiModelProperty(value = "5月计划量")
    @TableField("MAY_MONTH")
    private Double mayMonth;

    @ApiModelProperty(value = "6月计划量")
    @TableField("JUN_MONTH")
    private Double junMonth;

    @ApiModelProperty(value = "7月计划量")
    @TableField("JUL_MONTH")
    private Double julMonth;

    @ApiModelProperty(value = "8月计划量")
    @TableField("AUG_MONTH")
    private Double augMonth;

    @ApiModelProperty(value = "9月计划量")
    @TableField("SEP_MONTH")
    private Double sepMonth;

    @ApiModelProperty(value = "10月计划量")
    @TableField("OCT_MONTH")
    private Double octMonth;

    @ApiModelProperty(value = "11月计划量")
    @TableField("NOV_MONTH")
    private Double novMonth;

    @ApiModelProperty(value = "12月计划量")
    @TableField("DEC_MONTH")
    private Double decMonth;

    @ApiModelProperty(value = "创建时间")
    @TableField("GMT_CREATE")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField("GMT_MODIFY")
    private LocalDateTime gmtModify;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "不删除1;删除0")
    @TableField("STATUS")
    private String status;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
