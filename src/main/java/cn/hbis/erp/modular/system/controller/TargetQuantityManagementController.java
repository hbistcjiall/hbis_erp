package cn.hbis.erp.modular.system.controller;


import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.service.TargetQuantityManagementService;
import cn.hbis.erp.modular.system.warpper.TargetQuantityManagementWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/TargetManage")
public class TargetQuantityManagementController extends BaseController {
    @Autowired
    private TargetQuantityManagementService targetService;
    @ApiOperation(value = "获取责任公司列表")
    @PostMapping("/selTargetManage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname" ,value = "责任公司id",dataType ="String" ),
            @ApiImplicitParam(name = "year" ,value = "年限",dataType ="String" ),
            @ApiImplicitParam(name = "limit" ,value = "每页多少条数据",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    public Object selaccountmanager(String companyname,String year,String limit, String page){
        Page<Map<String, Object>> objcet = targetService.selectTargetManeg(companyname,year);
        Page wrapped = new TargetQuantityManagementWrapper(objcet).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
    @ApiOperation(value = "逻辑删除")
    @PostMapping("/delTargetManage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,value = "责任公司Id",dataType ="String" )
    })
    public Map delete(String id){
        Map map = new HashMap();
        boolean flag = targetService.delete(id);
        if(flag){
            map.put("massage","删除成功");
        }else{
            map.put("massage","删除失败");
        }
        return map;
    }


    @ApiOperation(value = "责任公司添加和更新")
    @PostMapping("/addorupTargetManage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "targetname" ,value = "责任公司名称",dataType ="String" ),
            @ApiImplicitParam(name = "year" ,value = "年限",dataType ="String" ),
            @ApiImplicitParam(name = "jan" ,value = "一月",dataType ="String" ),
            @ApiImplicitParam(name = "feb" ,value = "二月",dataType ="String" ),
            @ApiImplicitParam(name = "mar" ,value = "三月",dataType ="String" ),
            @ApiImplicitParam(name = "apr" ,value = "四月",dataType ="String" ),
            @ApiImplicitParam(name = "may" ,value = "五月",dataType ="String" ),
            @ApiImplicitParam(name = "jun" ,value = "六月",dataType ="String" ),
            @ApiImplicitParam(name = "jul" ,value = "七月",dataType ="String" ),
            @ApiImplicitParam(name = "aug" ,value = "八月",dataType ="String" ),
            @ApiImplicitParam(name = "sep" ,value = "九月",dataType ="String" ),
            @ApiImplicitParam(name = "oct" ,value = "十月",dataType ="String" ),
            @ApiImplicitParam(name = "nov" ,value = "十一月",dataType ="String" ),
            @ApiImplicitParam(name = "dec" ,value = "十二月",dataType ="String" ),
            @ApiImplicitParam(name = "id" ,value = "责任公司Id",dataType ="String" )
    })
    public Map addORUpadte(String id,String targetname,String year,String jan,String feb,String mar,String apr,String may,String jun,String jul,String aug,String sep,String oct,String nov,String dec){
        Map map = new HashMap();
        boolean flag = targetService.addORUpadte(id,targetname,year,jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec);
        if(flag){
            map.put("massage","添加或修改成功");
        }else{
            map.put("massage","添加或修改失败");
        }
        return map;
    }
}
