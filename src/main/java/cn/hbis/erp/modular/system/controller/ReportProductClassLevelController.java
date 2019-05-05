package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.service.ReportProductClassLevelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    })
    @PostMapping("getcxfb")
    @Async
    public List<Map> getcxfb(@RequestParam(required = false) List<String> cx, String startTime, String endTime){

        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);

        List<Map> getcxfb = reportProductClassLevelService.getcxfb(cx,startTime1+" 00:00:00",endTime1+" 23:59:59");
//            Object b = 2;
//            double fkimg = 0;
//            double fkimg1=0;
//            int i=0;
//            for(Map map:  getcxfb) {
//                i++;
//                if(i==1 || map.get("CXNAME").equals(b)) {
//                    fkimg = fkimg + fkimg1+Double.parseDouble(map.get("FKIMG").toString());
//                }else{
//                    map.put("fkimg",fkimg);
//                    System.out.println(b);
//                    System.out.println(fkimg);
//                    fkimg = 0;
//                    fkimg1=Double.parseDouble(map.get("FKIMG").toString());
//                }
//                b = map.get("CXNAME");
//            }
//        System.out.println(getcxfb);
        return getcxfb;
    }



}
