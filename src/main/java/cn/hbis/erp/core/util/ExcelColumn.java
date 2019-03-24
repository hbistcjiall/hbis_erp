/**
 * FileName: ExcelColumn
 * Author:   zhangb
 * Date:     2019/3/23 10:38
 */
package cn.hbis.erp.core.util;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    public String value() default "";
}
