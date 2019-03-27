package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("SYS_SETTINGS")
@Data
public class Settings implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(value = "SETTING_ID", type = IdType.ID_WORKER_STR)
    private String settingid;
    /**
     * 上传时间
     */
    @TableField(value = "UPLOAD_TIME", fill = FieldFill.INSERT)
    private Date uploadTime;
    /**
     * 创建用户id
     */
    @TableField(value = "CREATE_USERID")
    private String createuserid;
    /**
     * 菜单id
     */
    @TableField(value = "MENU_ID")
    private String menuid;

}
