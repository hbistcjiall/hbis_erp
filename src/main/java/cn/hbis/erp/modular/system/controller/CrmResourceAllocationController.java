package cn.hbis.erp.modular.system.controller;


import cn.hbis.erp.modular.system.entity.Allocation;
import cn.hbis.erp.modular.system.service.CrmResourceAllocationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 资源分配维护表 前端控制器
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-21
 */
@RestController
@RequestMapping("/allocation")
public class CrmResourceAllocationController {

    @Autowired
    private CrmResourceAllocationService crmResourceAllocationService;

    @ApiOperation(value = "通过品种获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "flName", value = "分类名称", dataType = "String")
    })
    @RequestMapping(value = "/selAllocation",method = RequestMethod.POST)
    @Async
    public List<Allocation> selAllocation(String date,String flName){
        return this.crmResourceAllocationService.selSchedule(date,flName);
    }

    @ApiOperation(value = "通过产线获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序", dataType = "String"),
            @ApiImplicitParam(name = "flName", value = "分类名称", dataType = "String")
    })
    @RequestMapping(value = "/selScheduleByCx",method = RequestMethod.POST)
    @Async
    public List<Allocation> selScheduleByCx(String date,String sort,String flName){
        return this.crmResourceAllocationService.selScheduleByCx(date,sort,flName);
    }

    @ApiOperation(value = "通过公司获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
    })
    @RequestMapping(value = "/selScheduleByCompany",method = RequestMethod.POST)
    @Async
    public List<Allocation> selScheduleByCompany(String date){
        return this.crmResourceAllocationService.selCompany(date);
    }

    @ApiOperation(value = "获取合同进度详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "公司名", dataType = "String")
    })
    @RequestMapping(value = "/selDetail",method = RequestMethod.POST)
    @Async
    public List<Allocation> selDetail(String date,String companyName){
        return this.crmResourceAllocationService.selByCompany(date,companyName);
    }
}

