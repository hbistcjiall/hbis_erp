package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.service.ReportProductClassLevelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report-product-class-level")
public class ReportProductClassLevelController {

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
        List<List> list1 = new ArrayList<>();
        list1.add(list);
        list1.add(getcxfb);
        return list1;
    }


    @ApiOperation(value = "集团钢材资源布港情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "子公司ID", dataType = "String"),
            @ApiImplicitParam(name = "htdw", value = "合同单位", dataType = "String"),

    })
    @PostMapping("jtgczybgqk")
    @Async
    public List jtgczybgqk (String pz,String name,String htdw,String startTime,String endTime){
        return reportProductClassLevelService.jtgczybgqk(pz,name,htdw,startTime,endTime);
    }

    @ApiOperation(value = "集团钢材资源布港情况查询品种")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "子公司ID", dataType = "String"),

    })
    @PostMapping("jtgczybgqkpz")
    @Async
    public List jtgczybgqkpz (String name,String startTime,String endTime){
        List<Map<String,Object>>  one = reportProductClassLevelService.jtgczybgqkpz(name,startTime,endTime);
        Map two = new HashMap();
        two.put("VARIETY","全部");
        List list = new ArrayList();
        list.add(two);
        for (int i =0;i<one.size();i++){
            list.add(one.get(i));
        }
        return list;
    }
}
