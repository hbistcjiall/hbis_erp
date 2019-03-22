package cn.hbis.erp.modular.system.controller;



import cn.hbis.erp.modular.system.service.TargetQuantityManagementService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/protocolAccountDetails")
public class ResourcebalanceplanController extends BaseController {
    @Autowired
    private TargetQuantityManagementService tqService;

    @ApiOperation(value = "资源平衡计划1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type" ,value = "类型汉字",dataType ="String" )

    })
    @PostMapping(value = "resourceplanone")
    public List<Map> resourceplanone(String type){
        List<Map> list = tqService.salesmain(type);
        return list;
    }
    @ApiOperation(value = "资源平衡计划2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type" ,value = "类型汉字",dataType ="String" )

    })
    @PostMapping(value = "resourceplantwo")
    public List<Map> resourceplantwo(String type){
        List<Map> list = tqService.mills(type);
        return list;
    }
    @ApiOperation(value = "资源平衡计划3")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type" ,value = "类型汉字",dataType ="String" )

    })
    @PostMapping(value = "resourceplanthrid")
    public List<Map> resourceplanthrid(String type){
        List<Map> list = tqService.typesa(type);
        return list;
    }
    @ApiOperation(value = "资源平衡计划4")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type" ,value = "类型汉字",dataType ="String" )

    })
    @PostMapping(value = "resourceplanfour")
    public List<Map> resourceplanfour(String type){
        List<Map> list = tqService.settl(type);
        return list;
    }
}
