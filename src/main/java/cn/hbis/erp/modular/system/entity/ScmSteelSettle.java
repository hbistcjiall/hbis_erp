package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 结算清单信息接口
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SCM_STEEL_SETTLE")
@ApiModel(value="ScmSteelSettle对象", description="结算清单信息接口")
public class ScmSteelSettle extends Model<ScmSteelSettle> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键流水")
    @TableId("ID")
    private Long id;

    @ApiModelProperty(value = "销售组织")
    @TableField("VKORG")
    private String vkorg;

    @ApiModelProperty(value = "开票日期")
    @TableField("FKDAT")
    private LocalDateTime fkdat;

    @ApiModelProperty(value = "金税发票号")
    @TableField("ZOUNR")
    private String zounr;

    @ApiModelProperty(value = "系统发票号")
    @TableField("VBELN")
    private String vbeln;

    @ApiModelProperty(value = "系统发票行")
    @TableField("POSNR")
    private String posnr;

    @ApiModelProperty(value = "售达方编码")
    @TableField("KUNAG")
    private String kunag;

    @ApiModelProperty(value = "送达方")
    @TableField("SDF_NAME")
    private String sdfName;

    @ApiModelProperty(value = "发票类型(开票类型)")
    @TableField("FKART")
    private String fkart;

    @ApiModelProperty(value = "订单号")
    @TableField("AUBEL")
    private String aubel;

    @ApiModelProperty(value = "订单行项目号")
    @TableField("AUPOS")
    private String aupos;

    @ApiModelProperty(value = "订单原因")
    @TableField("AUGRU_AUFT")
    private String augruAuft;

    @ApiModelProperty(value = "订单原因描述")
    @TableField("BEZEI")
    private String bezei;

    @ApiModelProperty(value = "物料描述")
    @TableField("ARKTX")
    private String arktx;

    @ApiModelProperty(value = "开票金额")
    @TableField("KZWI6")
    private String kzwi6;

    @ApiModelProperty(value = "最终销售价格")
    @TableField("ZXJ_COUNT")
    private String zxjCount;

    @ApiModelProperty(value = "交货单号")
    @TableField("VGBEL")
    private String vgbel;

    @ApiModelProperty(value = "交货单行")
    @TableField("VGPOS")
    private String vgpos;

    @ApiModelProperty(value = "预留字段1")
    @TableField("PREY1")
    private String prey1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("PREY2")
    private String prey2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("PREY3")
    private String prey3;

    @TableField("GMT_CREATE")
    private LocalDateTime gmtCreate;

    @TableField("GMT_MODIFY")
    private LocalDateTime gmtModify;

    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty(value = "开票数量")
    @TableField("FKIMG")
    private Double fkimg;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
