package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.service.ProtocolAccountDetailsStatisticsService;
import cn.hbis.erp.modular.system.warpper.ProtocolAccountDetailsStatisticsWrapper;
import cn.hbis.erp.modular.system.warpper.ProtocolAccountDetailsWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 协议户明细统计控制器
 *
 *
 */
@RestController
@RequestMapping("/protocolAccountDetailsStatistics")
public class ProtocolAccountDetailsStatisticsController {

    @Autowired
    private ProtocolAccountDetailsStatisticsService protocolAccountDetailsStatisticsService;

    /**
     * 查询协议户明细统计列表
     *
     *
     */
    @ApiOperation(value = "查询协议户明细统计列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "统计月份开始", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "统计月份结束", dataType = "String"),
            @ApiImplicitParam(name = "supplyMode", value = "供货方式", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping(value = "list")
    public Map list(String varieties, String beginTime, String endTime, String supplyMode, @RequestParam(value = "idList") List<String> companyIdList, String limit, String page) {
        beginTime = DateUtil.getFirstDayOfMonth(beginTime);
        endTime = DateUtil.getLastDayOfMonth(endTime);
        System.out.println(beginTime+"::"+endTime);
        Map map = new HashMap();
        Page<Map<String, Object>> protocolAccountDetailsStatistics = protocolAccountDetailsStatisticsService.List(varieties, beginTime, endTime, supplyMode, companyIdList);
        Page wrapped = new ProtocolAccountDetailsStatisticsWrapper(protocolAccountDetailsStatistics).wrap();
        map.put("list",PageFactory.createPageInfo(wrapped));
        List list = protocolAccountDetailsStatisticsService.sumList(varieties, beginTime, endTime, supplyMode, companyIdList);
        map.put("sum",list);
        return map;
    }
}
