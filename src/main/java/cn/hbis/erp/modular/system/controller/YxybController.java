/**
 * FileName: YxybController
 * Author:   zhangb
 * Date:     2019/4/16 18:59
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import cn.hbis.erp.modular.system.service.ScmSteelSettleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    })
    @PostMapping("getyxybpz")
    @Async
    public List<ScmSteelSettle> getyxybpz(String zt, String startTime, String endTime) {
        List<ScmSteelSettle> getyxyb = new ArrayList<>();
        if(zt.equals("1")){
            getyxyb=scmSteelSettleService.getyxybpz(startTime,endTime);
        }else{
            //getzrbm=scmSteelSettleService.getzgsjswc(startTime,endTime);
        }

        return getyxyb;
    }
}
