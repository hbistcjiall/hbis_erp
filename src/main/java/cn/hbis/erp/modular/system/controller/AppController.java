/**
 * FileName: AppController
 * Author:   zhangb
 * Date:     2019/4/11 8:56
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.modular.system.entity.Allocation;
import cn.hbis.erp.modular.system.service.CrmResourceAllocationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appAllocation")
public class AppController {

    @Autowired
    private CrmResourceAllocationService crmResourceAllocationService;

    @ApiOperation(value = "通过品种获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "flName", value = "分类名称", dataType = "String")
    })
    @RequestMapping(value = "/selAppAllocation",method = RequestMethod.POST)
    public List<Allocation> selAppAllocation(String date, String flName){
        return this.crmResourceAllocationService.selSchedule(date,flName);
    }

    @ApiOperation(value = "通过公司获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
    })
    @RequestMapping(value = "/selScheduleByCompany",method = RequestMethod.POST)
    public List<Allocation> selScheduleByCompany(String date){
        return this.crmResourceAllocationService.selCompany(date);
    }

    @ApiOperation(value = "获取合同进度详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "公司名", dataType = "String")
    })
    @RequestMapping(value = "/selDetail",method = RequestMethod.POST)
    public List<Allocation> selDetail(String date,String companyName){
        return this.crmResourceAllocationService.selByCompany(date,companyName);
    }
}
