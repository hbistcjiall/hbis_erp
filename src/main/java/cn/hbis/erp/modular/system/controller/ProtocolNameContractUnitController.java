package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.service.ProtocolNameContractUnitService;
import cn.hbis.erp.modular.system.warpper.ProtocolNameContractUnitWrapper;
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

/**
 * 协议户名与合同单位对照表控制器
 *
 *
 */
@RestController
@RequestMapping("/ProtocolNameContractUnit")
public class ProtocolNameContractUnitController {

    @Autowired
    private ProtocolNameContractUnitService protocolNameContractUnitService;

    /**
     * 查询协议户名与合同单位对照表列表
     *
     *
     */
    @ApiOperation(value = "查询协议户名与合同单位对照表列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accontName", value = "协议户名称", dataType = "String"),
            @ApiImplicitParam(name = "contractUnit", value = "合同单位", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping(value = "list")
    public Object list(String accontName, String contractUnit, String limit, String page) {
        Page<Map<String, Object>> protocolNameContractUnit = protocolNameContractUnitService.List(accontName, contractUnit);
        Page wrapped = new ProtocolNameContractUnitWrapper(protocolNameContractUnit).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
    /**
     * 添加协议户名与合同单位对照
     *
     *
     */
    @ApiOperation(value = "添加协议户名与合同单位对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accontName", value = "协议户名称", dataType = "String"),
            @ApiImplicitParam(name = "contractUnit", value = "合同单位", dataType = "String"),
            @ApiImplicitParam(name = "note" ,value = "备注",dataType ="String" )
    })
    @PostMapping(value = "insert")
    public Map insert(String accontName, String contractUnit, String note) {
        Map map = new HashMap();
        boolean state = protocolNameContractUnitService.insert(accontName, contractUnit, note);
        if (state){
            map.put("message","成功");
        }else {
            map.put("message","失败");
        }
        return map;
    }
    /**
     * 修改协议户名与合同单位对照
     *
     *
     */
    @ApiOperation(value = "修改协议户名与合同单位对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nameContractUnitId", value = "id", dataType = "String"),
            @ApiImplicitParam(name = "accontName", value = "协议户名称", dataType = "String"),
            @ApiImplicitParam(name = "contractUnit", value = "合同单位", dataType = "String"),
            @ApiImplicitParam(name = "note" ,value = "备注",dataType ="String" )
    })
    @PostMapping(value = "update")
    public Map update(String nameContractUnitId, String accontName, String contractUnit, String note) {
        Map map = new HashMap();
        boolean state = protocolNameContractUnitService.update(nameContractUnitId, accontName, contractUnit, note);
        if (state){
            map.put("message","成功");
        }else {
            map.put("message","失败");
        }
        return map;
    }
    /**
     * 删除协议户名与合同单位对照
     *
     *
     */
    @ApiOperation(value = "删除协议户名与合同单位对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nameContractUnitId", value = "id", dataType = "String")
    })
    @PostMapping(value = "delete")
    public Map delete(String nameContractUnitId) {
        Map map = new HashMap();
        boolean state = protocolNameContractUnitService.delete(nameContractUnitId);
        if (state){
            map.put("message","成功");
        }else {
            map.put("message","失败");
        }
        return map;
    }
}
