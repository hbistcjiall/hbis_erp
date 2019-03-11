package cn.hbis.erp.modular.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER_TEST")
@ApiModel(value="UserTest对象", description="")
public class UserTest extends Model<UserTest> {

    private static final long serialVersionUID = 1L;

    @TableField("userid")
    private String userid;

    @TableField("userName")
    private String userName;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
