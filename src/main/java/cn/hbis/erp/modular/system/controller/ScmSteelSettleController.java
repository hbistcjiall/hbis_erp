package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import cn.hbis.erp.modular.system.service.ScmSteelSettleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 结算清单信息接口 前端控制器
 * </p>
 *
 * @author yaojiaqi
 * @since 2019-03-16
 */
@RestController
@RequestMapping("/scm-steel-settle")
public class ScmSteelSettleController {
    @Autowired
    private ScmSteelSettleService scmSteelSettleService;

    @ApiOperation(value = "月度产线报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "dw", value = "单位", dataType = "String"),
            @ApiImplicitParam(name = "cx", value = "产线", dataType = "String"),
    })
    @PostMapping("getcx")
    public  List<ScmSteelSettle>  getcx(String dw,String cx,String startTime,String endTime) {
        List<ScmSteelSettle> getcx=scmSteelSettleService.getcx(dw,cx,startTime,endTime);
        return getcx ;
    }
    @ApiOperation(value = "月度品种报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
    })
    @PostMapping("getpz")
    public  List<ScmSteelSettle>  getpz(String pz,String startTime,String endTime) {
        List<ScmSteelSettle> getpz=scmSteelSettleService.getpz(pz,startTime,endTime);
        return getpz;
    }
    @ApiOperation(value = "责任部门报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zrbm", value = "责任部门", dataType = "String"),
            @ApiImplicitParam(name = "rq", value = "日期", dataType = "String"),
    })
    @PostMapping("getzrbm")
    public  List<ScmSteelSettle>  getzrbm(String zrbm,String startTime,String endTime) {
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getzrbm(zrbm,startTime,endTime);
        return getzrbm;
    }

    @ApiOperation(value = "结算完成情况报表")
    @ApiImplicitParams({

    })
    @PostMapping("getpzjszl")
    public  List<ScmSteelSettle>  getpzjszl(String startTime,String endTime) {
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getpzjszl(startTime,endTime);
        return getzrbm;
    }


    @ApiOperation(value = "品种钢完成情况报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "zt", value = "状态", dataType = "String"),
    })
    @PostMapping("getpzgjswc")
    public  List<ScmSteelSettle>  getpzgjswc(String zt,String startTime,String endTime) {
        List<ScmSteelSettle> getzrbm;
        if(zt.equals("1")){
            getzrbm=scmSteelSettleService.getpzgjswc(startTime,endTime);
        }else{
            getzrbm=scmSteelSettleService.getzgsjswc(startTime,endTime);
        }

        return getzrbm;
    }



}

