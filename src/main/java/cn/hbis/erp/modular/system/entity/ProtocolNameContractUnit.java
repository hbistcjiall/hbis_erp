package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 协议户名与合同单位对照 实体类
 * </p>
 *
 */
@Data
@TableName("scm_protocolname_contractunit")
public class ProtocolNameContractUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "NAME_CONTRACT_UNIT_ID", type = IdType.UUID)
    private String nameContractUnitId;
    /**
     *协议户名称
     */
    @TableField(value = "ACCOUNTNAME")
    private String accontName;
    /**
     *合同单位
     */
    @TableField(value = "CONTRACTUNIT")
    private String contractUnit;
    /**
     *备注
     */
    @TableField(value = "NOTE")
    private String note;
    /**
     *删除状态（0未删除，1删除）
     */
    @TableField("DELETESTATE")
    private String deleteState;

    public String getNameContractUnitId() {
        return nameContractUnitId;
    }

    public void setNameContractUnitId(String nameContractUnitId) {
        this.nameContractUnitId = nameContractUnitId;
    }

    public String getAccontName() {
        return accontName;
    }

    public void setAccontName(String accontName) {
        this.accontName = accontName;
    }

    public String getContractUnit() {
        return contractUnit;
    }

    public void setContractUnit(String contractUnit) {
        this.contractUnit = contractUnit;
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
        return "ProtocolNameContractUnit{" +
                "nameContractUnitId='" + nameContractUnitId + '\'' +
                ", accontName='" + accontName + '\'' +
                ", contractUnit='" + contractUnit + '\'' +
                ", note='" + note + '\'' +
                ", deleteState='" + deleteState + '\'' +
                '}';
    }
}
