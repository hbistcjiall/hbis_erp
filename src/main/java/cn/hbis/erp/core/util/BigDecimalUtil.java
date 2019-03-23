package cn.hbis.erp.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @ClassName:
 * @Description:
 * @author: xupeng
 * @date: 2019/3/20 23:05
 */
public class BigDecimalUtil {

    private static NumberFormat percent = NumberFormat.getPercentInstance();

    /**
     * @param a 被除数
     * @param b 除数
     * @return 百分比(%)
     */
    public static String divPercent(BigDecimal a, BigDecimal b, int point) {
        percent.setMinimumFractionDigits(point);
        if (null == b || null == a) {
            return percent.format(BigDecimal.ZERO);
        } else if (b.compareTo(BigDecimal.ZERO) == 0) { // 除数为空
            return percent.format(BigDecimal.ZERO);
        } else {
            BigDecimal c = a.divide(b, point + 2, BigDecimal.ROUND_HALF_UP);
            return percent.format(c);
        }
    }
    /**
     * @Title: formatNumber
     * @Description: 格式化百分比
     * @param str
     * @return double 返回类型
     * @date 2015-7-29 上午10:42:19
     */
    public static double formatNumber(String str) {
        Double result = 0.00;
        if (str.contains("%")) {
            str = str.replace("%", "");
            result = Double.parseDouble(str) / 100;
        }
        return result;
    }
    /**
     * @param a 被除数
     * @param b 除数
     * @param num 四色五入保留几位小数
     */
    public static String divRound(BigDecimal a, BigDecimal b, int num) {
        // 除数为0
        if (null == b || null == a) {
            return "0";
        } else if (b.compareTo(BigDecimal.ZERO) == 0) { // 除数为空
            return "0";
        } else {
            BigDecimal c = a.divide(b, num, BigDecimal.ROUND_HALF_UP);
            return c + "";
        }
    }
    /**
     * @param a
     * @param num 四舍五入保留几位小数
     * @return BigDecimal
     * @author xc
     */
    public static BigDecimal keppPoint(BigDecimal a, int num) {
        if (null != a) {
            return a.setScale(num, BigDecimal.ROUND_HALF_UP);
        } else {
            return new BigDecimal(0);
        }
    }
    public static BigDecimal formart(BigDecimal bigDecimal) {
        DecimalFormat myformat = new DecimalFormat("#.00");
        String frt = "";
        BigDecimal bdl = new BigDecimal("0.00");
        if(null!=bigDecimal){
            frt = myformat.format(bigDecimal);
            bdl = new BigDecimal(frt);
        }
        return bdl;
    }
    /**
     * @param a 被除数
     * @param b 除数
     * @param point 四舍五入保留小数
     * @return 商
     * @author xc
     */
    public static BigDecimal div(BigDecimal a, BigDecimal b, int point) {
        try {
            return a.divide(b, point, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            return new BigDecimal("0");
        }
    }
    /**
     * @param a 被除数
     * @param b 除数
     * @param point 百分比保留小数
     * @return 百分比
     * @author xc
     */
    public static String getPercent(BigDecimal a, BigDecimal b, int point) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(point);
        return nf.format(div(a, b, point + 2));
    }
    public static double mul(BigDecimal b1, BigDecimal b2) { // 进行乘法运算
        try {
            return b1.multiply(b2).doubleValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public static String topercent(BigDecimal varietiesShipments) {
        String frt = "";
        DecimalFormat myformat = new DecimalFormat("0.00");
        if(null!=varietiesShipments){
            frt = myformat.format(varietiesShipments.multiply(new BigDecimal(100)));
        }else{
            frt = myformat.format(BigDecimal.ZERO);
        }
        return frt;
    }

    public static double ceil(double a, double b) {
        return Math.ceil(a / b);
    }
}
