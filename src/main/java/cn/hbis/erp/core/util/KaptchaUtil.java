
package cn.hbis.erp.core.util;

import cn.hbis.erp.config.properties.HbisProperties;
import cn.stylefeng.roses.core.util.SpringContextHolder;

/**
 * 验证码工具类
 */
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(HbisProperties.class).getKaptchaOpen();
    }
}