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
import org.springframework.web.bind.annotation.RestController;

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


    })
    @PostMapping("getcxfb")
    @Async
    public List<Map> getcxfb (String startTime,String endTime){

        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);

        List<Map> getcxfb = reportProductClassLevelService.getcxfb(startTime1+" 00:00:00",endTime1+" 23:59:59");
        return getcxfb;
    }
}
