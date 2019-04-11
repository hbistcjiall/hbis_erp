package cn.hbis.erp.modular.system.entity;

import cn.hbis.erp.core.util.DoubleUtil;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 建材北京市场现货价格
 * </p>
 *
 *
 *
 */
public class ReportSpotPriceBreakdown implements Serializable {

    private static final long serialVersionUID = -642436252074588757L;

    private String            orderYear;
    private String            orderMonth;
    private String            orderXun;
    private String            orderDay;
    @TableId
    private String            company;
    private String            type;                                   //1=结算量；2=结算价
    //高线
    private Double            gxData1;                                //φ6.5mm
    private Double            gxData2;                                //φ8mm
    private Double            gxData3;                                //φ10mm
    private Double            gxData4;                                //φ12mm
    //盘螺
    private Double            plData1;                                //φ6mm
    private Double            plData2;                                //φ8mm
    private Double            plData3;                                //φ10mm
    private Double            plData4;                                //φ12mm
    private Double            plData5;                                //φ6mm
    private Double            plData6;                                //φ8mm
    private Double            plData7;                                //φ10mm
    private Double            plData8;                                //φ12mm
    //螺纹钢
    private Double            lwData1;                                //φ10mm
    private Double            lwData2;                                //φ12mm
    private Double            lwData3;                                //φ14mm
    private Double            lwData4;                                //φ16mm
    private Double            lwData5;                                //φ18mm
    private Double            lwData6;                                //φ20mm
    private Double            lwData7;                                //φ22mm
    private Double            lwData8;                                //φ25mm
    private Double            lwData9;                                //φ28mm
    private Double            lwData10;                               //φ32mm
    private Double            lwData11;                               //φ36mm
    private Double            lwData12;                               //φ40mm
    private Double            lwData13;                               //φ18—25mm
    private Double            lwData14;                               //φ10mm
    private Double            lwData15;                               //φ12mm
    private Double            lwData16;                               //φ14mm
    private Double            lwData17;                               //φ16mm
    private Double            lwData18;                               //φ18mm
    private Double            lwData19;                               //φ20mm
    private Double            lwData20;                               //φ22mm
    private Double            lwData21;                               //φ25mm
    private Double            lwData22;                               //φ28mm
    private Double            lwData23;                               //φ32mm
    private Double            lwData24;                               //φ36mm
    private Double            lwData25;                               //φ40mm
    private Double            lwData26;                               //φ18—25mm

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(String orderYear) {
        this.orderYear = orderYear;
    }

    public String getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(String orderMonth) {
        this.orderMonth = orderMonth;
    }

    public String getOrderXun() {
        return orderXun;
    }

    public void setOrderXun(String orderXun) {
        this.orderXun = orderXun;
    }

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getGxData1() {
        return gxData1;
    }

    public void setGxData1(Double gxData1) {
        if("2".equals(this.type)) {
            this.gxData1 = DoubleUtil.round(gxData1, 2);
        }else {
            this.gxData1 = gxData1;
        }
    }

    public Double getGxData2() {
        return gxData2;
    }

    public void setGxData2(Double gxData2) {
        this.gxData2 = gxData2;
    }

    public Double getGxData3() {
        return gxData3;
    }

    public void setGxData3(Double gxData3) {
        this.gxData3 = gxData3;
    }

    public Double getGxData4() {
        return gxData4;
    }

    public void setGxData4(Double gxData4) {
        this.gxData4 = gxData4;
    }

    public Double getPlData1() {
        return plData1;
    }

    public void setPlData1(Double plData1) {
        this.plData1 = plData1;
    }

    public Double getPlData2() {
        return plData2;
    }

    public void setPlData2(Double plData2) {
        if("2".equals(this.type)) {
            this.plData2 = DoubleUtil.round(plData2, 2);
        }else {
            this.plData2 = DoubleUtil.round(plData2, 3);
        }
    }

    public Double getPlData3() {
        return plData3;
    }

    public void setPlData3(Double plData3) {
        if("2".equals(this.type)) {
            this.plData3 = DoubleUtil.round(plData3, 2);
        }else {
            this.plData3 = DoubleUtil.round(plData3, 3);
        }
    }

    public Double getPlData4() {
        return plData4;
    }

    public void setPlData4(Double plData4) {
        this.plData4 = plData4;
    }

    public Double getPlData5() {
        return plData5;
    }

    public void setPlData5(Double plData5) {
        this.plData5 = plData5;
    }

    public Double getPlData6() {
        return plData6;
    }

    public void setPlData6(Double plData6) {
        this.plData6 = plData6;
    }

    public Double getPlData7() {
        return plData7;
    }

    public void setPlData7(Double plData7) {
        this.plData7 = plData7;
    }

    public Double getPlData8() {
        return plData8;
    }

    public void setPlData8(Double plData8) {
        this.plData8 = plData8;
    }

    public Double getLwData1() {
        return lwData1;
    }

    public void setLwData1(Double lwData1) {
        this.lwData1 = lwData1;
    }

    public Double getLwData2() {
        return lwData2;
    }

    public void setLwData2(Double lwData2) {
        this.lwData2 = lwData2;
    }

    public Double getLwData3() {
        return lwData3;
    }

    public void setLwData3(Double lwData3) {
        this.lwData3 = lwData3;
    }

    public Double getLwData4() {
        return lwData4;
    }

    public void setLwData4(Double lwData4) {
        this.lwData4 = lwData4;
    }

    public Double getLwData5() {
        return lwData5;
    }

    public void setLwData5(Double lwData5) {
        this.lwData5 = lwData5;
    }

    public Double getLwData6() {
        return lwData6;
    }

    public void setLwData6(Double lwData6) {
        this.lwData6 = lwData6;
    }

    public Double getLwData7() {
        return lwData7;
    }

    public void setLwData7(Double lwData7) {
        this.lwData7 = lwData7;
    }

    public Double getLwData8() {
        return lwData8;
    }

    public void setLwData8(Double lwData8) {
        this.lwData8 = lwData8;
    }

    public Double getLwData9() {
        return lwData9;
    }

    public void setLwData9(Double lwData9) {
        this.lwData9 = lwData9;
    }

    public Double getLwData10() {
        return lwData10;
    }

    public void setLwData10(Double lwData10) {
        this.lwData10 = lwData10;
    }

    public Double getLwData11() {
        return lwData11;
    }

    public void setLwData11(Double lwData11) {
        this.lwData11 = lwData11;
    }

    public Double getLwData12() {
        return lwData12;
    }

    public void setLwData12(Double lwData12) {
        this.lwData12 = lwData12;
    }

    public Double getLwData13() {
        return lwData13;
    }

    public void setLwData13(Double lwData13) {
        this.lwData13 = lwData13;
    }

    public Double getLwData14() {
        return lwData14;
    }

    public void setLwData14(Double lwData14) {
        this.lwData14 = lwData14;
    }

    public Double getLwData15() {
        return lwData15;
    }

    public void setLwData15(Double lwData15) {
        this.lwData15 = lwData15;
    }

    public Double getLwData16() {
        return lwData16;
    }

    public void setLwData16(Double lwData16) {
        this.lwData16 = lwData16;
    }

    public Double getLwData17() {
        return lwData17;
    }

    public void setLwData17(Double lwData17) {
        this.lwData17 = lwData17;
    }

    public Double getLwData18() {
        return lwData18;
    }

    public void setLwData18(Double lwData18) {
        this.lwData18 = lwData18;
    }

    public Double getLwData19() {
        return lwData19;
    }

    public void setLwData19(Double lwData19) {
        this.lwData19 = lwData19;
    }

    public Double getLwData20() {
        return lwData20;
    }

    public void setLwData20(Double lwData20) {
        this.lwData20 = lwData20;
    }

    public Double getLwData21() {
        return lwData21;
    }

    public void setLwData21(Double lwData21) {
        this.lwData21 = lwData21;
    }

    public Double getLwData22() {
        return lwData22;
    }

    public void setLwData22(Double lwData22) {
        this.lwData22 = lwData22;
    }

    public Double getLwData23() {
        return lwData23;
    }

    public void setLwData23(Double lwData23) {
        this.lwData23 = lwData23;
    }

    public Double getLwData24() {
        return lwData24;
    }

    public void setLwData24(Double lwData24) {
        this.lwData24 = lwData24;
    }

    public Double getLwData25() {
        return lwData25;
    }

    public void setLwData25(Double lwData25) {
        this.lwData25 = lwData25;
    }

    public Double getLwData26() {
        return lwData26;
    }

    public void setLwData26(Double lwData26) {
        this.lwData26 = lwData26;
    }

    @Override
    public String toString() {
        return "ReportSpotPriceBreakdown [company=" + company + ", orderYear=" + orderYear
                + ", orderMonth=" + orderMonth + ", orderXun=" + orderXun + ", orderDay=" + orderDay
                + ", type=" + type + ", gxData1=" + gxData1 + ", gxData2=" + gxData2 + ", gxData3="
                + gxData3 + ", gxData4=" + gxData4 + ", plData1=" + plData1 + ", plData2=" + plData2
                + ", plData3=" + plData3 + ", plData4=" + plData4 + ", plData5=" + plData5
                + ", plData6=" + plData6 + ", plData7=" + plData7 + ", plData8=" + plData8
                + ", lwData1=" + lwData1 + ", lwData2=" + lwData2 + ", lwData3=" + lwData3
                + ", lwData4=" + lwData4 + ", lwData5=" + lwData5 + ", lwData6=" + lwData6
                + ", lwData7=" + lwData7 + ", lwData8=" + lwData8 + ", lwData9=" + lwData9
                + ", lwData10=" + lwData10 + ", lwData11=" + lwData11 + ", lwData12=" + lwData12
                + ", lwData13=" + lwData13 + ", lwData14=" + lwData14 + ", lwData15=" + lwData15
                + ", lwData16=" + lwData16 + ", lwData17=" + lwData17 + ", lwData18=" + lwData18
                + ", lwData19=" + lwData19 + ", lwData20=" + lwData20 + ", lwData21=" + lwData21
                + ", lwData22=" + lwData22 + ", lwData23=" + lwData23 + ", lwData24=" + lwData24
                + ", lwData25=" + lwData25 + ", lwData26=" + lwData26 + "]";
    }
}
