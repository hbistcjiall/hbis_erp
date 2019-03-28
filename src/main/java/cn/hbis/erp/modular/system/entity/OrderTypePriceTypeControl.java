package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 订单类型与价格类型对照表
 * </p>
 *
 */
@Data
@TableName("scm_ordertype_pricetype")
public class OrderTypePriceTypeControl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ORDERPRICEID", type = IdType.UUID)
    private String orderPriceId;
    /**
     *品种
     */
    @TableField(value = "VARIETIES")
    private String varieties;
    /**
     *钢厂ID
     */
    @TableField(value = "COMPANYID")
    private String companyId;
    /**
     *钢厂
     */
    @TableField(value = "COMPANYNAME")
    private String companyName;
    /**
     *订单类型
     */
    @TableField("ORDERTYPE")
    private String orderType;
    /**
     *价格类型
     */
    @TableField("PRICETYPE")
    private String priceType;
    /**
     *备注
     */
    @TableField("NOTE")
    private String note;
    /**
     *删除状态（0未删除，1删除）
     */
    @TableField("DELETESTATE")
    private String deleteState;

    public String getOrderPriceId() {
        return orderPriceId;
    }

    public void setOrderPriceId(String orderPriceId) {
        this.orderPriceId = orderPriceId;
    }

    public String getVarieties() {
        return varieties;
    }

    public void setVarieties(String varieties) {
        this.varieties = varieties;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }

    @Override
    public String toString() {
        return "OrderTypePriceTypeControl{" +
                "orderPriceId='" + orderPriceId + '\'' +
                ", varieties='" + varieties + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", priceType='" + priceType + '\'' +
                ", note='" + note + '\'' +
                ", deleteState='" + deleteState + '\'' +
                '}';
    }
}
