package cn.hbis.erp.core.util;

import java.math.BigDecimal;

/**
 * @ClassName:
 * @Description:
 * @author: xupeng
 * @date: 2019/3/20 15:29
 */
public class DoubleUtil {
    /**
     * 格式化double型的数字
     * @param v
     * @param scale：小数点后保留的位数
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal b = new BigDecimal(Double.toString(v));
            BigDecimal one = new BigDecimal("1");
            return b.divide(one, scale, 4).doubleValue();
        }
    }
}
