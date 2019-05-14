package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ReportProductClassLevel;
import cn.hbis.erp.modular.system.mapper.ReportProductClassLevelMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportProductClassLevelService extends ServiceImpl<ReportProductClassLevelMapper,ReportProductClassLevel> {
    @Resource
    private ReportProductClassLevelMapper reportProductClassLevelMapper;

    @Async
    public List<Map> getcxfb(List<String> cx, String startTime,String endTime,String zl){
        return reportProductClassLevelMapper.getcxfb(cx,startTime,endTime,zl);
    }
    @Async
    public List<Map> getcxzl(List<String> cx, String startTime,String endTime,String zl){
        return reportProductClassLevelMapper.getcxzl(cx,startTime,endTime,zl);
    }
    @Async
    public List<Map<String,Object>> getcxfb01(List<String> cx, String startTime,String endTime,String zl){

        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);

        List<Map<String,Object>> getcxfb = reportProductClassLevelMapper.getcxfb01(cx,startTime1+" 00:00:00",endTime1+" 23:59:59",zl);
        List<Map> getCxNamePzg01 = reportProductClassLevelMapper.getcxzl01(cx,startTime1+" 00:00:00",endTime1+" 23:59:59",zl);
        List<Map> getCxNamePzg = reportProductClassLevelMapper.getcxzl(cx,startTime1+" 00:00:00",endTime1+" 23:59:59",zl);
        List<Map> getCxNamePzg02 = reportProductClassLevelMapper.getcxzl02(cx,startTime1+" 00:00:00",endTime1+" 23:59:59",zl);
        double fkimg = 0;
        double zyfkimg=0;
        double zykzwi6=0;
        double fgsfkimg=0;
        double fgskzwi6=0;
        double sybfkimg=0;
        double sybkzwi6=0;
        double xhfkimg=0;
        double xhkzwi6=0;
        double zbgsfkimg=0;
        double zbgskzwi6=0;
        double zysj=0;
        double fgssj=0;
        double sybsj=0;
        double xhsj=0;
        double zbgssj=0;
        double zyxszb=0;
        double fgsxszb=0;
        double sybxszb=0;
        double xhxszb=0;
        double zbgsxszb=0;
        double zsj=0;
        int i= 0;
        List<Map<String,Object>> list = new ArrayList<>();
        if(i==0) {
            for (Map m2 : getcxfb) {
                fkimg = fkimg + Double.parseDouble(m2.get("FKIMG").toString());
                zyfkimg = zyfkimg + Double.parseDouble(m2.get("ZYFKIMG").toString());
                fgsfkimg = fgsfkimg + Double.parseDouble(m2.get("FGSFKIMG").toString());
                sybfkimg = sybfkimg + Double.parseDouble(m2.get("SYBFKIMG").toString());
                xhfkimg = xhfkimg + Double.parseDouble(m2.get("XHFKIMG").toString());
                zbgsfkimg = zbgsfkimg + Double.parseDouble(m2.get("ZBGSFKIMG").toString());
                zykzwi6 = zykzwi6 + Double.parseDouble(m2.get("ZYKZWI6").toString());
                fgskzwi6 = fgskzwi6 + Double.parseDouble(m2.get("FGSKZWI6").toString());
                sybkzwi6 = sybkzwi6 + Double.parseDouble(m2.get("SYBKZWI6").toString());
                xhkzwi6 = xhkzwi6 + Double.parseDouble(m2.get("XHKZWI6").toString());
                zbgskzwi6 = zbgskzwi6 + Double.parseDouble(m2.get("ZBGSKZWI6").toString());
            }

            if(zykzwi6 / zyfkimg>0) {
                zysj = zykzwi6 / zyfkimg;
            }else{
                zysj = 0;
            }
            if(fgskzwi6 / fgsfkimg>0) {
                fgssj = fgskzwi6 / fgsfkimg;
            }else{
                fgssj = 0;
            }
            if(sybkzwi6 / sybfkimg>0) {
                sybsj = sybkzwi6 / sybfkimg;
            }else{
                sybsj = 0;
            }
            if(xhkzwi6 / xhfkimg>0) {
                xhsj = xhkzwi6 / xhfkimg;
            }else{
                xhsj = 0;
            }
            if(zbgskzwi6 / zbgsfkimg>0) {
                zbgssj = zbgskzwi6 / zbgsfkimg;
            }else{
                zbgssj = 0;
            }
            if(zyfkimg / fkimg>0) {
                zyxszb = zyfkimg / fkimg;
            }else{
                zyxszb = 0;
            }
            if(fgsfkimg / fkimg>0) {
                fgsxszb = fgsfkimg / fkimg;
            }else{
                fgsxszb = 0;
            }
            if(sybfkimg / fkimg>0) {
                sybxszb = sybfkimg / fkimg;
            }else{
                sybxszb = 0;
            }
            if(xhfkimg / fkimg>0) {
                xhxszb = xhfkimg / fkimg;
            }else{
                xhxszb = 0;
            }
            if(zbgsfkimg / fkimg>0) {
                zbgsxszb = zbgsfkimg / fkimg;
            }else{
                zbgsxszb = 0;
            }
            if((zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg>0) {
                zsj = (zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg;
            }else{
                zsj = 0;
            }
            Map<String, Object> m = new HashMap<>();
            m.put("ZL", "内贸");
            m.put("CXNAME", "总计");
            m.put("PRODUCT_GRADE", "总计");
            m.put("FKIMG",Double.parseDouble(String.format("%.2f",fkimg)));
            m.put("ZSJ", Double.parseDouble(String.format("%.2f",zsj)));
            m.put("ZYFKIMG", Double.parseDouble(String.format("%.2f",zyfkimg)));
            m.put("ZYSJ", Double.parseDouble(String.format("%.2f",zysj)));
            m.put("ZYXSZB", Double.parseDouble(String.format("%.2f",zyxszb*100))+"%");
            m.put("FGSFKIMG", Double.parseDouble(String.format("%.2f",fgsfkimg)));
            m.put("FGSSJ", Double.parseDouble(String.format("%.2f",fgssj)));
            m.put("FGSXSZB", Double.parseDouble(String.format("%.2f",fgsxszb*100))+"%");
            m.put("SYBFKIMG", Double.parseDouble(String.format("%.2f",sybfkimg)));
            m.put("SYBSJ", Double.parseDouble(String.format("%.2f",sybsj)));
            m.put("SYBXSZB", Double.parseDouble(String.format("%.2f",sybxszb*100))+"%");
            m.put("XHFKIMG", Double.parseDouble(String.format("%.2f",xhfkimg)));
            m.put("XHSJ", Double.parseDouble(String.format("%.2f",xhsj)));
            m.put("XHXSZB", Double.parseDouble(String.format("%.2f",xhxszb*100))+"%");
            m.put("ZBGSFKIMG", Double.parseDouble(String.format("%.2f",zbgsfkimg)));
            m.put("ZBGSSJ", Double.parseDouble(String.format("%.2f",zbgssj)));
            m.put("ZBGSXSZB", Double.parseDouble(String.format("%.2f",zbgsxszb*100))+"%");
            list.add(m);
            i = 0;
            fkimg = 0;
            zyfkimg = 0;
            fgsfkimg = 0;
            sybfkimg = 0;
            xhfkimg = 0;
            zbgsfkimg = 0;
            zykzwi6 = 0;
            fgskzwi6 = 0;
            sybkzwi6 = 0;
            xhkzwi6 = 0;
            zbgskzwi6 = 0;
        }
        for (Map m1:getCxNamePzg01) {
            if (m1.get("ZL") == "其他") {
                continue;
            }
            for (Map m2 : getcxfb) {
                if (m2.get("CXNAME").equals("唐钢其他")) {
                    continue;
                }
                if (m1.get("ZL").equals(m2.get("ZL"))) {
                    i++;
                    fkimg = fkimg + Double.parseDouble(m2.get("FKIMG").toString());
                    zyfkimg = zyfkimg + Double.parseDouble(m2.get("ZYFKIMG").toString());
                    fgsfkimg = fgsfkimg + Double.parseDouble(m2.get("FGSFKIMG").toString());
                    sybfkimg = sybfkimg + Double.parseDouble(m2.get("SYBFKIMG").toString());
                    xhfkimg = xhfkimg + Double.parseDouble(m2.get("XHFKIMG").toString());
                    zbgsfkimg = zbgsfkimg + Double.parseDouble(m2.get("ZBGSFKIMG").toString());
                    zykzwi6 = zykzwi6 + Double.parseDouble(m2.get("ZYKZWI6").toString());
                    fgskzwi6 = fgskzwi6 + Double.parseDouble(m2.get("FGSKZWI6").toString());
                    sybkzwi6 = sybkzwi6 + Double.parseDouble(m2.get("SYBKZWI6").toString());
                    xhkzwi6 = xhkzwi6 + Double.parseDouble(m2.get("XHKZWI6").toString());
                    zbgskzwi6 = zbgskzwi6 + Double.parseDouble(m2.get("ZBGSKZWI6").toString());
                }
            }
            if (i != 0) {
                if(zykzwi6 / zyfkimg>0) {
                    zysj = zykzwi6 / zyfkimg;
                }else{
                    zysj = 0;
                }
                if(fgskzwi6 / fgsfkimg>0) {
                    fgssj = fgskzwi6 / fgsfkimg;
                }else{
                    fgssj = 0;
                }
                if(sybkzwi6 / sybfkimg>0) {
                    sybsj = sybkzwi6 / sybfkimg;
                }else{
                    sybsj = 0;
                }
                if(xhkzwi6 / xhfkimg>0) {
                    xhsj = xhkzwi6 / xhfkimg;
                }else{
                    xhsj = 0;
                }
                if(zbgskzwi6 / zbgsfkimg>0) {
                    zbgssj = zbgskzwi6 / zbgsfkimg;
                }else{
                    zbgssj = 0;
                }
                if(zyfkimg / fkimg>0) {
                    zyxszb = zyfkimg / fkimg;
                }else{
                    zyxszb = 0;
                }
                if(fgsfkimg / fkimg>0) {
                    fgsxszb = fgsfkimg / fkimg;
                }else{
                    fgsxszb = 0;
                }
                if(sybfkimg / fkimg>0) {
                    sybxszb = sybfkimg / fkimg;
                }else{
                    sybxszb = 0;
                }
                if(xhfkimg / fkimg>0) {
                    xhxszb = xhfkimg / fkimg;
                }else{
                    xhxszb = 0;
                }
                if(zbgsfkimg / fkimg>0) {
                    zbgsxszb = zbgsfkimg / fkimg;
                }else{
                    zbgsxszb = 0;
                }
                if((zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg>0) {
                    zsj = (zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg;
                }else{
                    zsj = 0;
                }
                Map<String, Object> m = new HashMap<>();
                m.put("ZL", m1.get("ZL"));
                m.put("CXNAME", m1.get("ZL")+"小计");
                m.put("PRODUCT_GRADE", "内贸合计");
                m.put("FKIMG",Double.parseDouble(String.format("%.2f",fkimg)));
                m.put("ZSJ", Double.parseDouble(String.format("%.2f",zsj)));
                m.put("ZYFKIMG", Double.parseDouble(String.format("%.2f",zyfkimg)));
                m.put("ZYSJ", Double.parseDouble(String.format("%.2f",zysj)));
                m.put("ZYXSZB", Double.parseDouble(String.format("%.2f",zyxszb*100))+"%");
                m.put("FGSFKIMG", Double.parseDouble(String.format("%.2f",fgsfkimg)));
                m.put("FGSSJ", Double.parseDouble(String.format("%.2f",fgssj)));
                m.put("FGSXSZB", Double.parseDouble(String.format("%.2f",fgsxszb*100))+"%");
                m.put("SYBFKIMG", Double.parseDouble(String.format("%.2f",sybfkimg)));
                m.put("SYBSJ", Double.parseDouble(String.format("%.2f",sybsj)));
                m.put("SYBXSZB", Double.parseDouble(String.format("%.2f",sybxszb*100))+"%");
                m.put("XHFKIMG", Double.parseDouble(String.format("%.2f",xhfkimg)));
                m.put("XHSJ", Double.parseDouble(String.format("%.2f",xhsj)));
                m.put("XHXSZB", Double.parseDouble(String.format("%.2f",xhxszb*100))+"%");
                m.put("ZBGSFKIMG", Double.parseDouble(String.format("%.2f",zbgsfkimg)));
                m.put("ZBGSSJ", Double.parseDouble(String.format("%.2f",zbgssj)));
                m.put("ZBGSXSZB", Double.parseDouble(String.format("%.2f",zbgsxszb*100))+"%");
                list.add(m);
                i = 0;
                fkimg = 0;
                zyfkimg = 0;
                fgsfkimg = 0;
                sybfkimg = 0;
                xhfkimg = 0;
                zbgsfkimg = 0;
                zykzwi6 = 0;
                fgskzwi6 = 0;
                sybkzwi6 = 0;
                xhkzwi6 = 0;
                zbgskzwi6 = 0;
            }
        }
        for (Map m1:getCxNamePzg02) {
            if(m1.get("CXNAME").equals("唐钢其他")){
                continue;
            }
            for(Map m2 : getcxfb){
                if(m2.get("CXNAME").equals("唐钢其他")){
                    continue;
                }
                if(m1.get("ZL").equals(m2.get("ZL"))&&m1.get("PRODUCT_GRADE").equals(m2.get("PRODUCT_GRADE"))){
                    i++;
                    fkimg = fkimg + Double.parseDouble(m2.get("FKIMG").toString());
                    zyfkimg = zyfkimg + Double.parseDouble(m2.get("ZYFKIMG").toString());
                    fgsfkimg = fgsfkimg + Double.parseDouble(m2.get("FGSFKIMG").toString());
                    sybfkimg = sybfkimg + Double.parseDouble(m2.get("SYBFKIMG").toString());
                    xhfkimg = xhfkimg + Double.parseDouble(m2.get("XHFKIMG").toString());
                    zbgsfkimg = zbgsfkimg + Double.parseDouble(m2.get("ZBGSFKIMG").toString());
                    zykzwi6 = zykzwi6 + Double.parseDouble(m2.get("ZYKZWI6").toString());
                    fgskzwi6 = fgskzwi6 + Double.parseDouble(m2.get("FGSKZWI6").toString());
                    sybkzwi6 = sybkzwi6 + Double.parseDouble(m2.get("SYBKZWI6").toString());
                    xhkzwi6 = xhkzwi6 + Double.parseDouble(m2.get("XHKZWI6").toString());
                    zbgskzwi6 = zbgskzwi6 + Double.parseDouble(m2.get("ZBGSKZWI6").toString());
                }
            }
            if(i!=0){
                if(zykzwi6 / zyfkimg>0) {
                    zysj = zykzwi6 / zyfkimg;
                }else{
                    zysj = 0;
                }
                if(fgskzwi6 / fgsfkimg>0) {
                    fgssj = fgskzwi6 / fgsfkimg;
                }else{
                    fgssj = 0;
                }
                if(sybkzwi6 / sybfkimg>0) {
                    sybsj = sybkzwi6 / sybfkimg;
                }else{
                    sybsj = 0;
                }
                if(xhkzwi6 / xhfkimg>0) {
                    xhsj = xhkzwi6 / xhfkimg;
                }else{
                    xhsj = 0;
                }
                if(zbgskzwi6 / zbgsfkimg>0) {
                    zbgssj = zbgskzwi6 / zbgsfkimg;
                }else{
                    zbgssj = 0;
                }
                if(zyfkimg / fkimg>0) {
                    zyxszb = zyfkimg / fkimg;
                }else{
                    zyxszb = 0;
                }
                if(fgsfkimg / fkimg>0) {
                    fgsxszb = fgsfkimg / fkimg;
                }else{
                    fgsxszb = 0;
                }
                if(sybfkimg / fkimg>0) {
                    sybxszb = sybfkimg / fkimg;
                }else{
                    sybxszb = 0;
                }
                if(xhfkimg / fkimg>0) {
                    xhxszb = xhfkimg / fkimg;
                }else{
                    xhxszb = 0;
                }
                if(zbgsfkimg / fkimg>0) {
                    zbgsxszb = zbgsfkimg / fkimg;
                }else{
                    zbgsxszb = 0;
                }
                if((zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg>0) {
                    zsj = (zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg;
                }else{
                    zsj = 0;
                }
                Map<String, Object> m = new HashMap<>();
                m.put("ZL", m1.get("ZL"));
                m.put("CXNAME", m1.get("ZL")+"小计");
                m.put("PRODUCT_GRADE",m1.get("PRODUCT_GRADE"));
                m.put("FKIMG",Double.parseDouble(String.format("%.2f",fkimg)));
                m.put("ZSJ", Double.parseDouble(String.format("%.2f",zsj)));
                m.put("ZYFKIMG", Double.parseDouble(String.format("%.2f",zyfkimg)));
                m.put("ZYSJ", Double.parseDouble(String.format("%.2f",zysj)));
                m.put("ZYXSZB", Double.parseDouble(String.format("%.2f",zyxszb*100))+"%");
                m.put("FGSFKIMG", Double.parseDouble(String.format("%.2f",fgsfkimg)));
                m.put("FGSSJ", Double.parseDouble(String.format("%.2f",fgssj)));
                m.put("FGSXSZB", Double.parseDouble(String.format("%.2f",fgsxszb*100))+"%");
                m.put("SYBFKIMG", Double.parseDouble(String.format("%.2f",sybfkimg)));
                m.put("SYBSJ", Double.parseDouble(String.format("%.2f",sybsj)));
                m.put("SYBXSZB", Double.parseDouble(String.format("%.2f",sybxszb*100))+"%");
                m.put("XHFKIMG", Double.parseDouble(String.format("%.2f",xhfkimg)));
                m.put("XHSJ", Double.parseDouble(String.format("%.2f",xhsj)));
                m.put("XHXSZB", Double.parseDouble(String.format("%.2f",xhxszb*100))+"%");
                m.put("ZBGSFKIMG", Double.parseDouble(String.format("%.2f",zbgsfkimg)));
                m.put("ZBGSSJ", Double.parseDouble(String.format("%.2f",zbgssj)));
                m.put("ZBGSXSZB", Double.parseDouble(String.format("%.2f",zbgsxszb*100))+"%");
                list.add(m);
                i=0;
                fkimg=0;
                zyfkimg=0;
                fgsfkimg=0;
                sybfkimg=0;
                xhfkimg=0;
                zbgsfkimg=0;
                zykzwi6=0;
                fgskzwi6=0;
                sybkzwi6=0;
                xhkzwi6=0;
                zbgskzwi6=0;
            }
        }
        for(Map map : getCxNamePzg){
            if(map.get("CXNAME").equals("唐钢其他")){
                continue;
            }
            for(Map map1 : getcxfb){
                if(map1.get("CXNAME").equals("唐钢其他")){
                    continue;
                }
                if(map.get("CXNAME").equals(map1.get("CXNAME"))){
                    i++;
                    fkimg = fkimg + Double.parseDouble(map1.get("FKIMG").toString());
                    zyfkimg = zyfkimg + Double.parseDouble(map1.get("ZYFKIMG").toString());
                    fgsfkimg = fgsfkimg + Double.parseDouble(map1.get("FGSFKIMG").toString());
                    sybfkimg = sybfkimg + Double.parseDouble(map1.get("SYBFKIMG").toString());
                    xhfkimg = xhfkimg + Double.parseDouble(map1.get("XHFKIMG").toString());
                    zbgsfkimg = zbgsfkimg + Double.parseDouble(map1.get("ZBGSFKIMG").toString());
                    zykzwi6 = zykzwi6 + Double.parseDouble(map1.get("ZYKZWI6").toString());
                    fgskzwi6 = fgskzwi6 + Double.parseDouble(map1.get("FGSKZWI6").toString());
                    sybkzwi6 = sybkzwi6 + Double.parseDouble(map1.get("SYBKZWI6").toString());
                    xhkzwi6 = xhkzwi6 + Double.parseDouble(map1.get("XHKZWI6").toString());
                    zbgskzwi6 = zbgskzwi6 + Double.parseDouble(map1.get("ZBGSKZWI6").toString());
                }
            }
            if(i!=0) {
                if(zykzwi6 / zyfkimg>0) {
                    zysj = zykzwi6 / zyfkimg;
                }else{
                    zysj = 0;
                }
                if(fgskzwi6 / fgsfkimg>0) {
                    fgssj = fgskzwi6 / fgsfkimg;
                }else{
                    fgssj = 0;
                }
                if(sybkzwi6 / sybfkimg>0) {
                    sybsj = sybkzwi6 / sybfkimg;
                }else{
                    sybsj = 0;
                }
                if(xhkzwi6 / xhfkimg>0) {
                    xhsj = xhkzwi6 / xhfkimg;
                }else{
                    xhsj = 0;
                }
                if(zbgskzwi6 / zbgsfkimg>0) {
                    zbgssj = zbgskzwi6 / zbgsfkimg;
                }else{
                    zbgssj = 0;
                }
                if(zyfkimg / fkimg>0) {
                    zyxszb = zyfkimg / fkimg;
                }else{
                    zyxszb = 0;
                }
                if(fgsfkimg / fkimg>0) {
                    fgsxszb = fgsfkimg / fkimg;
                }else{
                    fgsxszb = 0;
                }
                if(sybfkimg / fkimg>0) {
                    sybxszb = sybfkimg / fkimg;
                }else{
                    sybxszb = 0;
                }
                if(xhfkimg / fkimg>0) {
                    xhxszb = xhfkimg / fkimg;
                }else{
                    xhxszb = 0;
                }
                if(zbgsfkimg / fkimg>0) {
                    zbgsxszb = zbgsfkimg / fkimg;
                }else{
                    zbgsxszb = 0;
                }
                if((zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg>0) {
                    zsj = (zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg;
                }else{
                    zsj = 0;
                }
                Map<String, Object> m = new HashMap<>();
                m.put("ZL", map.get("ZL"));
                m.put("PRODUCT_GRADE", "内贸合计");
                m.put("CXNAME", map.get("CXNAME"));
                m.put("FKIMG",Double.parseDouble(String.format("%.2f",fkimg)));
                m.put("ZSJ", Double.parseDouble(String.format("%.2f",zsj)));
                m.put("ZYFKIMG", Double.parseDouble(String.format("%.2f",zyfkimg)));
                m.put("ZYSJ", Double.parseDouble(String.format("%.2f",zysj)));
                m.put("ZYXSZB", Double.parseDouble(String.format("%.2f",zyxszb*100))+"%");
                m.put("FGSFKIMG", Double.parseDouble(String.format("%.2f",fgsfkimg)));
                m.put("FGSSJ", Double.parseDouble(String.format("%.2f",fgssj)));
                m.put("FGSXSZB", Double.parseDouble(String.format("%.2f",fgsxszb*100))+"%");
                m.put("SYBFKIMG", Double.parseDouble(String.format("%.2f",sybfkimg)));
                m.put("SYBSJ", Double.parseDouble(String.format("%.2f",sybsj)));
                m.put("SYBXSZB", Double.parseDouble(String.format("%.2f",sybxszb*100))+"%");
                m.put("XHFKIMG", Double.parseDouble(String.format("%.2f",xhfkimg)));
                m.put("XHSJ", Double.parseDouble(String.format("%.2f",xhsj)));
                m.put("XHXSZB", Double.parseDouble(String.format("%.2f",xhxszb*100))+"%");
                m.put("ZBGSFKIMG", Double.parseDouble(String.format("%.2f",zbgsfkimg)));
                m.put("ZBGSSJ", Double.parseDouble(String.format("%.2f",zbgssj)));
                m.put("ZBGSXSZB", Double.parseDouble(String.format("%.2f",zbgsxszb*100))+"%");
                list.add(m);
                i=0;
                fkimg=0;
                zyfkimg=0;
                fgsfkimg=0;
                sybfkimg=0;
                xhfkimg=0;
                zbgsfkimg=0;
                zykzwi6=0;
                fgskzwi6=0;
                sybkzwi6=0;
                xhkzwi6=0;
                zbgskzwi6=0;
            }
        }
        for (Map m1:getcxfb) {
            Map<String, Object> m = new HashMap<>();
            m.put("ZL", m1.get("ZL"));
            m.put("PRODUCT_GRADE", m1.get("PRODUCT_GRADE"));
            m.put("CXNAME", m1.get("CXNAME"));
            m.put("FKIMG", Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("FKIMG").toString()))));
            m.put("ZSJ", Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("ZSJ").toString()))));
            m.put("ZYFKIMG", Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("ZYFKIMG").toString()))));
            m.put("ZYSJ", Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("ZYSJ").toString()))));
            m.put("ZYXSZB",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("ZYXSZB").toString())*100))+"%");
            m.put("FGSFKIMG", Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("FGSFKIMG").toString()))));
            m.put("FGSSJ", Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("FGSSJ").toString()))));
            m.put("FGSXSZB",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("FGSXSZB").toString())*100))+"%");
            m.put("SYBFKIMG",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("SYBFKIMG").toString()))));
            m.put("SYBSJ", Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("SYBSJ").toString()))));
            m.put("SYBXSZB",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("SYBXSZB").toString())*100))+"%");
            m.put("XHFKIMG",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("XHFKIMG").toString()))));
            m.put("XHSJ",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("XHSJ").toString()))));
            m.put("XHXSZB",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("XHXSZB").toString())*100))+"%");
            m.put("ZBGSFKIMG",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("ZBGSFKIMG").toString()))));
            m.put("ZBGSSJ",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("ZBGSSJ").toString()))));
            m.put("ZBGSXSZB",Double.parseDouble(String.format("%.2f",Double.parseDouble(m1.get("ZBGSXSZB").toString())*100))+"%");
            list.add(m);
        }
        return list;
    }


    /**
     * 产线导出
     * @param startTime
     * @param endTime
     * @return
     */
    @Async
    public List cxexcel(String dw, List<String> cx ,String startTime,String endTime){
        return reportProductClassLevelMapper.cxexcel(dw,cx,startTime,endTime);
    }
    /**
     * 品种导出
     * @param startTime
     * @param endTime
     * @return
     */
    @Async
    public List pzexcel( String startTime,String endTime){
        return reportProductClassLevelMapper.pzexcel(startTime,endTime);
    }
}
