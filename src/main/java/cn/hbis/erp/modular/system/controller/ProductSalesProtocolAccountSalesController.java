package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.service.ProductSalesProtocolAccountSalesService;
import cn.hbis.erp.modular.system.warpper.ProductSalesProtocolAccountSalesWrapper;
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
 * 产品总销量及销售公司协议户销量统计控制器
 *
 *
 */
@RestController
@RequestMapping("/productSalesProtocolAccountSales")
public class ProductSalesProtocolAccountSalesController {

    @Autowired
    private ProductSalesProtocolAccountSalesService productSalesProtocolAccountSalesService;
    /**
     * 查询产品总销量及销售公司协议户销量统计
     *
     *
     */
    @ApiOperation(value = "查询协议户明细统计列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "统计月份开始", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "统计月份结束", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping(value = "list")
    public Object list(String varieties, String beginTime, String endTime,String limit, String page) {
        beginTime = DateUtil.getFirstDayOfMonth(beginTime);
        endTime = DateUtil.getLastDayOfMonth(endTime);
        System.out.println(beginTime+"::"+endTime);
        Map map = new HashMap();
        Page<Map<String, Object>> productSalesProtocolAccountSales = productSalesProtocolAccountSalesService.list(varieties, beginTime, endTime);
        Page wrapped = new ProductSalesProtocolAccountSalesWrapper(productSalesProtocolAccountSales).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
}
