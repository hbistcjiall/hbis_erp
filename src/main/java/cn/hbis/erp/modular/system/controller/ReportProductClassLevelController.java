package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.service.ReportProductClassLevelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report-product-class-level")
public class ReportProductClassLevelController {

    private static ReportProductClassLevelService reportProductClassLevelService1;
    @Autowired
    private ReportProductClassLevelService reportProductClassLevelService;


    @ApiOperation(value = "产线分布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "zl", value = "产品", dataType = "String"),

    })
    @PostMapping("getcxfb")
    @Async
    public List<List> getcxfb(@RequestParam(required = false) List<String> cx, String startTime, String endTime,String zl){

        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);

        List<Map> getcxfb = reportProductClassLevelService.getcxfb(cx,startTime1+" 00:00:00",endTime1+" 23:59:59",zl);
        List<Map> getCxNamePzg = reportProductClassLevelService.getcxzl(cx,startTime1+" 00:00:00",endTime1+" 23:59:59",zl);
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
            int i= 0;
        List<Map> list = new ArrayList<>();
        for(Map map : getCxNamePzg){
            for(Map map1 : getcxfb){
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
                double zysj = zykzwi6 / zyfkimg;
                double fgssj = fgskzwi6 / fgsfkimg;
                double sybsj = sybkzwi6 / sybfkimg;
                double xhsj = xhkzwi6 / xhfkimg;
                double zbgssj = zbgskzwi6 / zbgsfkimg;
                double zyxszb = zyfkimg / fkimg;
                double fgsxszb = fgsfkimg / fkimg;
                double sybxszb = sybfkimg / fkimg;
                double xhxszb = xhfkimg / fkimg;
                double zbgsxszb = zbgsfkimg / fkimg;
                double zsj = (zykzwi6 + fgskzwi6 + sybkzwi6 + xhkzwi6 + zbgskzwi6) / fkimg;
                Map<Object, Object> m = new HashMap<>();
                m.put("ZL", map.get("ZL"));
                m.put("PRODUCT_GRADE", "内贸合计");
                m.put("CXNAME", map.get("CXNAME"));
                m.put("FKIMG", fkimg);
                m.put("ZSJ", zsj);
                m.put("ZYFKIMG", zyfkimg);
                m.put("ZYSJ", zysj);
                m.put("ZYXSZB", zyxszb);
                m.put("FGSFKIMG", fgsfkimg);
                m.put("FGSSJ", fgssj);
                m.put("FGSXSZB", fgsxszb);
                m.put("SYBFKIMG", sybfkimg);
                m.put("SYBSJ", sybsj);
                m.put("SYBXSZB", sybxszb);
                m.put("XHFKIMG", xhfkimg);
                m.put("XHSJ", xhsj);
                m.put("XHXSZB", xhxszb);
                m.put("ZBGSFKIMG", zbgsfkimg);
                m.put("ZBGSSJ", zbgssj);
                m.put("ZBGSXSZB", zbgsxszb);
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
//            Object b = 2;
//            Object z = 3;
//            double fkimg = 0;
//            double fkimg1=0;
//            double zyfkimg=0;
//            double zyfkimg1=0;
//            double zykzwi6=0;
//            double zykzwi61=0;
//            double fgsfkimg=0;
//            double fgsfkimg1=0;
//            double fgskzwi6=0;
//            double fgskzwi61=0;
//            double sybfkimg=0;
//            double sybfkimg1=0;
//            double sybkzwi6=0;
//            double sybkzwi61=0;
//            double xhfkimg=0;
//            double xhfkimg1=0;
//            double xhkzwi6=0;
//            double xhkzwi61=0;
//            double zbgsfkimg=0;
//            double zbgsfkimg1=0;
//            double zbgskzwi6=0;
//            double zbgskzwi61=0;
//            int i=0;
//        List<Map> list = new ArrayList<>();
//        for(Map map:  getcxfb) {
//            i++;
//            if (i == 1 || map.get("CXNAME").equals(b)) {
//                fkimg = fkimg + fkimg1 + Double.parseDouble(map.get("FKIMG").toString());
//                fkimg1 = 0;
//                zyfkimg = zyfkimg + zyfkimg1 + Double.parseDouble(map.get("ZYFKIMG").toString());
//                zyfkimg1 = 0;
//                fgsfkimg = fgsfkimg + fgsfkimg1 + Double.parseDouble(map.get("FGSFKIMG").toString());
//                fgsfkimg1 = 0;
//                sybfkimg = sybfkimg + sybfkimg1 + Double.parseDouble(map.get("SYBFKIMG").toString());
//                sybfkimg1 = 0;
//                xhfkimg = xhfkimg + xhfkimg1 + Double.parseDouble(map.get("XHFKIMG").toString());
//                xhfkimg1 = 0;
//                zbgsfkimg = zbgsfkimg + zbgsfkimg1 + Double.parseDouble(map.get("ZBGSFKIMG").toString());
//                zbgsfkimg1 = 0;
//                zykzwi6 = zykzwi6 + zykzwi61 + Double.parseDouble(map.get("ZYKZWI6").toString());
//                zykzwi61 = 0;
//                fgskzwi6 = fgskzwi6 + fgskzwi61 + Double.parseDouble(map.get("FGSKZWI6").toString());
//                fgskzwi61 = 0;
//                sybkzwi6 = sybkzwi6 + sybkzwi61 + Double.parseDouble(map.get("SYBKZWI6").toString());
//                sybkzwi61 = 0;
//                xhkzwi6 = xhkzwi6 + xhkzwi61 + Double.parseDouble(map.get("XHKZWI6").toString());
//                xhkzwi61 = 0;
//                zbgskzwi6 = zbgskzwi6 + zbgskzwi61 + Double.parseDouble(map.get("ZBGSKZWI6").toString());
//                zbgskzwi61 = 0;
//            } else {
//                int y = 0;
//                for(Map map1:  getcxfb){
//                    if(map.get("CXNAME").equals(map1.get("CXNAME"))){
//                        y++;
//                    }
//                }
//                Map<Object,Object> m = new HashMap<>();
//                if(y==1){
//                    double zysj = Double.parseDouble(map.get("ZYKZWI6").toString())
//                            /Double.parseDouble(map.get("ZYFKIMG").toString());
//                    double fgssj = Double.parseDouble(map.get("FGSKZWI6").toString())
//                            /Double.parseDouble(map.get("FGSFKIMG").toString());
//                    double sybsj = Double.parseDouble(map.get("SYBKZWI6").toString())
//                            /Double.parseDouble(map.get("SYBFKIMG").toString());
//                    double xhsj = Double.parseDouble(map.get("XHKZWI6").toString())
//                            /Double.parseDouble(map.get("XHFKIMG").toString());
//                    double zbgssj = Double.parseDouble(map.get("ZBGSKZWI6").toString())
//                            /Double.parseDouble(map.get("ZBGSFKIMG").toString());
//                    double zyxszb = Double.parseDouble(map.get("ZYFKIMG").toString())
//                            /Double.parseDouble(map.get("FKIMG").toString());
//                    double fgsxszb = Double.parseDouble(map.get("FGSFKIMG").toString())
//                            /Double.parseDouble(map.get("FKIMG").toString());
//                    double sybxszb = Double.parseDouble(map.get("SYBFKIMG").toString())
//                            /Double.parseDouble(map.get("FKIMG").toString());
//                    double xhxszb = Double.parseDouble(map.get("XHFKIMG").toString())
//                            /Double.parseDouble(map.get("FKIMG").toString());
//                    double zbgsxszb = Double.parseDouble(map.get("ZBGSFKIMG").toString())
//                            /Double.parseDouble(map.get("FKIMG").toString());
//                    double zsj = (Double.parseDouble(map.get("ZYKZWI6").toString())
//                            +Double.parseDouble(map.get("FGSKZWI6").toString())
//                            +Double.parseDouble(map.get("SYBKZWI6").toString())
//                            +Double.parseDouble(map.get("XHKZWI6").toString())
//                            +Double.parseDouble(map.get("ZBGSKZWI6").toString()))
//                            /Double.parseDouble(map.get("FKIMG").toString());
//                    m.put("ZL",z);
//                    m.put("PRODUCT_GRADE","内贸合计");
//                    m.put("CXNAME",map.get("CXNAME"));
//                    m.put("FKIMG", Double.parseDouble(map.get("FKIMG").toString()));
//                    m.put("ZSJ",zsj);
//                    m.put("ZYFKIMG",Double.parseDouble(map.get("ZYFKIMG").toString()));
//                    m.put("ZYSJ",zysj);
//                    m.put("ZYXSZB",zyxszb);
//                    m.put("FGSFKIMG",Double.parseDouble(map.get("FGSFKIMG").toString()));
//                    m.put("FGSSJ",fgssj);
//                    m.put("FGSXSZB",fgsxszb);
//                    m.put("SYBFKIMG",Double.parseDouble(map.get("SYBFKIMG").toString()));
//                    m.put("SYBSJ",sybsj);
//                    m.put("SYBXSZB",sybxszb);
//                    m.put("XHFKIMG",Double.parseDouble(map.get("XHFKIMG").toString()));
//                    m.put("XHSJ",xhsj);
//                    m.put("XHXSZB",xhxszb);
//                    m.put("ZBGSFKIMG",Double.parseDouble(map.get("ZBGSFKIMG").toString()));
//                    m.put("ZBGSSJ",zbgssj);
//                    m.put("ZBGSXSZB",zbgsxszb);
//                }else {
//                    double zysj = zykzwi6/zyfkimg;
//                    double fgssj = fgskzwi6/fgsfkimg;
//                    double sybsj = sybkzwi6/sybfkimg;
//                    double xhsj = xhkzwi6/xhfkimg;
//                    double zbgssj = zbgskzwi6/zbgsfkimg;
//                    double zyxszb = zyfkimg/fkimg;
//                    double fgsxszb = fgsfkimg/fkimg;
//                    double sybxszb = sybfkimg/fkimg;
//                    double xhxszb = xhfkimg/fkimg;
//                    double zbgsxszb = zbgsfkimg/fkimg;
//                    double zsj = (zykzwi6+fgskzwi6+sybkzwi6+xhkzwi6+zbgskzwi6)/fkimg;
//                    m.put("ZL",z);
//                    m.put("PRODUCT_GRADE","内贸合计");
//                    m.put("CXNAME",b);
//                    m.put("FKIMG", fkimg);
//                    m.put("ZSJ",zsj);
//                    m.put("ZYFKIMG",zyfkimg);
//                    m.put("ZYSJ",zysj);
//                    m.put("ZYXSZB",zyxszb);
//                    m.put("FGSFKIMG",fgsfkimg);
//                    m.put("FGSSJ",fgssj);
//                    m.put("FGSXSZB",fgsxszb);
//                    m.put("SYBFKIMG",sybfkimg);
//                    m.put("SYBSJ",sybsj);
//                    m.put("SYBXSZB",sybxszb);
//                    m.put("XHFKIMG",xhfkimg);
//                    m.put("XHSJ",xhsj);
//                    m.put("XHXSZB",xhxszb);
//                    m.put("ZBGSFKIMG",zbgsfkimg);
//                    m.put("ZBGSSJ",zbgssj);
//                    m.put("ZBGSXSZB",zbgsxszb);
//
//                }
//
//                System.out.println(b);
//                System.out.println(fkimg);
//                list.add(m);
//                fkimg = 0;
//                fkimg1 = Double.parseDouble(map.get("FKIMG").toString());
//                zyfkimg = 0;
//                zyfkimg1 = Double.parseDouble(map.get("ZYFKIMG").toString());
//                fgsfkimg = 0;
//                fgsfkimg1 = Double.parseDouble(map.get("FGSFKIMG").toString());
//                sybfkimg = 0;
//                sybfkimg1 = Double.parseDouble(map.get("SYBFKIMG").toString());
//                xhfkimg = 0;
//                xhfkimg1 = Double.parseDouble(map.get("XHFKIMG").toString());
//                zbgsfkimg = 0;
//                zbgsfkimg1 = Double.parseDouble(map.get("ZBGSFKIMG").toString());
//                zykzwi6 = 0;
//                zykzwi61 = Double.parseDouble(map.get("ZYKZWI6").toString());
//                fgskzwi6 = 0;
//                fgskzwi61 = Double.parseDouble(map.get("FGSKZWI6").toString());
//                sybkzwi6 = 0;
//                sybkzwi61 = Double.parseDouble(map.get("SYBKZWI6").toString());
//                xhkzwi6 = 0;
//                xhkzwi61 = Double.parseDouble(map.get("XHKZWI6").toString());
//                zbgskzwi6 = 0;
//                zbgskzwi61 = Double.parseDouble(map.get("ZBGSKZWI6").toString());
//
//            }
//            b = map.get("CXNAME");
//            z = map.get("ZL");
//        }
            List<List> list1 = new ArrayList<>();
        list1.add(list);
        list1.add(getcxfb);
        System.out.println(list1);
        return list1;
    }
}
