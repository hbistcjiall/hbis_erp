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
 * @author jiarsi
 * @since 2019-03-16
 */
@RestController
@RequestMapping("/scm-steel-settle")
public class ScmSteelSettleController {
    @Autowired
    private ScmSteelSettleService scmSteelSettleService;

    @ApiOperation(value = "月度产线报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dw", value = "单位", dataType = "String"),
            @ApiImplicitParam(name = "cx", value = "产线", dataType = "String"),
    })
    @PostMapping("getcx")
    public  List<ScmSteelSettle>  getcx(String dw,String cx) {
        List<ScmSteelSettle> getcx=scmSteelSettleService.getcx(dw,cx);
        return getcx ;
    }
    @ApiOperation(value = "月度品种报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
    })
    @PostMapping("getpz")
    public  List<ScmSteelSettle>  getpz(String pz) {
        List<ScmSteelSettle> getpz=scmSteelSettleService.getpz(pz);
        return getpz;
    }

    @ApiOperation(value = "年度度产线报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dw", value = "单位", dataType = "String"),
            @ApiImplicitParam(name = "cx", value = "产线", dataType = "String"),
    })
    @PostMapping("getndcx")
    public  List<ScmSteelSettle>  getndcx(String dw,String cx) {
        List<ScmSteelSettle> getcx=scmSteelSettleService.getndcx(dw,cx);
        return getcx ;
    }
    @ApiOperation(value = "年度品种报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
    })
    @PostMapping("getndpz")
    public  List<ScmSteelSettle>  getndpz(String pz) {
        List<ScmSteelSettle> getpz=scmSteelSettleService.getndpz(pz);
        return getpz;
    }

    @ApiOperation(value = "责任部门报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zrbm", value = "责任部门", dataType = "String"),
    })
    @PostMapping("getzrbm")
    public  List<ScmSteelSettle>  getzrbm(String zrbm) {
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getzrbm(zrbm);
        return getzrbm;
    }



}

