package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.service.OrderTypePriceTypeService;
import cn.hbis.erp.modular.system.warpper.OrderTypePriceTypeWrapper;
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
 * 订单类型与价格类型对照表控制器
 *
 *
 */
@RestController
@RequestMapping("/OrderTypePriceType")
public class OrderTypePriceTypeController {

    @Autowired
    private OrderTypePriceTypeService orderTypePriceTypeService;

    /**
     * 查询订单类型与价格类型对照列表
     *
     *
     */
    @ApiOperation(value = "查询订单类型与价格类型对照列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "钢厂ID", dataType = "String"),
            @ApiImplicitParam(name = "varieties", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "orderType", value = "订单类型", dataType = "String"),
            @ApiImplicitParam(name = "priceType", value = "价格类型", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping(value = "list")
    public Object list(String companyId, String varieties, String orderType, String priceType, String limit, String page) {
        Page<Map<String, Object>> orderTypePriceType = orderTypePriceTypeService.List(companyId, varieties, orderType, priceType);
        Page wrapped = new OrderTypePriceTypeWrapper(orderTypePriceType).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
    /**
     * 添加订单类型与价格类型对照
     *
     *
     */
    @ApiOperation(value = "添加订单类型与价格类型对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties" ,value = "品种",dataType ="String" ),
            @ApiImplicitParam(name = "companyId", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "orderType", value = "订单类型", dataType = "String"),
            @ApiImplicitParam(name = "priceType" ,value = "价格类型",dataType ="String" ),
            @ApiImplicitParam(name = "note" ,value = "备注",dataType ="String" )
    })
    @PostMapping(value = "insert")
    public Map insert(String varieties, String companyId, String orderType, String priceType, String note) {
        Map map = new HashMap();
        boolean state = orderTypePriceTypeService.insert(varieties, companyId, orderType, priceType, note);
        if (state){
            map.put("message","成功");
        }else {
            map.put("message","失败");
        }
        return map;
    }
    /**
     * 修改订单类型与价格类型对照
     *
     *
     */
    @ApiOperation(value = "修改订单类型与价格类型对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderPriceId" ,value = "Id",dataType ="String" ),
            @ApiImplicitParam(name = "varieties" ,value = "品种",dataType ="String" ),
            @ApiImplicitParam(name = "companyId", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "orderType", value = "订单类型", dataType = "String"),
            @ApiImplicitParam(name = "priceType" ,value = "价格类型",dataType ="String" ),
            @ApiImplicitParam(name = "note" ,value = "备注",dataType ="String" )
    })
    @PostMapping(value = "update")
    public Map update(String orderPriceId, String varieties, String companyId, String orderType, String priceType, String note) {
        Map map = new HashMap();
        boolean state = orderTypePriceTypeService.update(orderPriceId, varieties, companyId, orderType, priceType, note);
        if (state){
            map.put("message","成功");
        }else {
            map.put("message","失败");
        }
        return map;
    }
    /**
     * 删除订单类型与价格类型对照
     *
     *
     */
    @ApiOperation(value = "删除订单类型与价格类型对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderPriceId" ,value = "Id",dataType ="String" )
    })
    @PostMapping(value = "delete")
    public Map delete(String orderPriceId) {
        Map map = new HashMap();
        boolean state = orderTypePriceTypeService.delete(orderPriceId);
        if (state){
            map.put("message","删除成功");
        }else {
            map.put("message","删除失败");
        }
        return map;
    }
}
