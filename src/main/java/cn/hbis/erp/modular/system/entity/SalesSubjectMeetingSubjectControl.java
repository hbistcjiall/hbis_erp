package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 销售主体例会主体对照表
 * </p>
 *
 */
@Data
@TableName("price_salebody_relation")
public class SalesSubjectMeetingSubjectControl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID",type = IdType.ID_WORKER)
    private Long Id;
    /**
     *子公司id
     */
    @TableField(value = "COMPANY_ID")
    private String companyId;
    /**
     *子公司名
     */
    @TableField(value = "COMPANY_NAME")
    private String companyName;
    /**
     *销售主体描述
     */
    @TableField("SALE_BODY_DES")
    private String saleBodyDes;
    /*
    * 销售主体
    */
    @TableField("SALE_BODY")
    private String saleBody;
    /*
    * 删除状态，为0表示未删除  1:已删除  2原来的对照销售主体
    */
    @TableField("IS_DELETE")
    private String isDelete;
    /*
     * 创建时间
     */
    @TableField("GMT_CREATE")
    private Date gmtCrate;
    /*
     * 修改时间
     */
    @TableField("GMT_MODIFY")
    private Date gmtModify;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getSaleBodyDes() {
        return saleBodyDes;
    }

    public void setSaleBodyDes(String saleBodyDes) {
        this.saleBodyDes = saleBodyDes;
    }

    public String getSaleBody() {
        return saleBody;
    }

    public void setSaleBody(String saleBody) {
        this.saleBody = saleBody;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getGmtCrate() {
        return gmtCrate;
    }

    public void setGmtCrate(Date gmtCrate) {
        this.gmtCrate = gmtCrate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        return "SalesSubjectMeetingSubjectControl{" +
                "Id='" + Id + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", saleBodyDes='" + saleBodyDes + '\'' +
                ", saleBody='" + saleBody + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", gmtCrate=" + gmtCrate +
                ", gmtModify=" + gmtModify +
                '}';
    }
}
