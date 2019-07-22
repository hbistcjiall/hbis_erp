/**
 * FileName: YxybController
 * Author:   zhangb
 * Date:     2019/4/16 18:59
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import cn.hbis.erp.modular.system.service.ScmSteelSettleService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yxyb")
public class YxybController {
    @Autowired
    private ScmSteelSettleService scmSteelSettleService;

    @ApiOperation(value = "品种钢完成情况报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "zt", value = "状态", dataType = "String"),
            @ApiImplicitParam(name = "gc", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String")
    })
    @PostMapping("/getyxybpz")
    @Async
    public List<ScmSteelSettle> getyxybpz(String zt, String startTime, String endTime,String pz,String gc,String jd) {
        List<ScmSteelSettle> getyxyb = new ArrayList<>();
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        if(zt.equals("1")){
            getyxyb=scmSteelSettleService.getyxybpz(startTime1 +" 00:00:00",endTime1+" 23:59:59",pz,jd);
        }else{
            getyxyb=scmSteelSettleService.getyxybgc(startTime1+" 00:00:00",endTime1+" 23:59:59",gc,jd);
        }

        return getyxyb;
    }
    @ApiOperation(value = "销售结算完成情况报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String")

    })
    @PostMapping("/getxsjswccx")
    @Async
    public List<Map> getxsjswccx(String startTime, @RequestParam(required = false) List<String> cx, String endTime, String pz,String jd) {
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map> list = new ArrayList<>();
                list = scmSteelSettleService.getxsjswccx(startTime1+" 00:00:00",endTime1+" 23:59:59",pz,cx,jd);
        return  list;
    }

    @ApiOperation(value = "集团销售结算完成情况（产线）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String"),
            @ApiImplicitParam(name = "pzg", value = "品种钢", dataType = "String")

    })
    @PostMapping("/getjtxsjscx")
    @Async
    public List<Map> getjtxsjscx(String startTime, @RequestParam(required = false) List<String> cx, String endTime, String pz,String jd,String pzg) {
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map> list = new ArrayList<>();
        list = scmSteelSettleService.getjtxsjscx(startTime1+" 00:00:00",endTime1+" 23:59:59",pz,cx,jd,pzg);

         return  list;
    }
}


