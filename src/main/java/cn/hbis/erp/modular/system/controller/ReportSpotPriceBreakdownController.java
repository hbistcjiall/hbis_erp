package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;

import cn.hbis.erp.modular.system.entity.ReportSpotPriceBreakdown;
import cn.hbis.erp.modular.system.service.ReportSpotPriceBreakdownService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 建材北京市场现货价格
 *
 *
 *
 */
@RestController
@RequestMapping("/reportSpotPriceBreakdown")
public class ReportSpotPriceBreakdownController {
    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";
    @Autowired
    private ReportSpotPriceBreakdownService reportSpotPriceBreakdownService;

    /**
     * 获取建材北京市场现货价格列表
     *
     *
     */
    @ApiOperation(value = "查询建材北京市场现货价格列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "endMonth" ,value = "记录日期",dataType ="String" ),
            @ApiImplicitParam(name = "company" ,value = "钢厂",dataType ="String" )
    })
    @PostMapping(value = "list")
    public Map list(String endMonth, String company) {
        Map map = new HashMap();
        // 默认查询当日
        if (null == endMonth || "".equals(endMonth)) {
            Calendar calendar = Calendar.getInstance();
            endMonth = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        }
        if (null == company) {
            company = "0";
        }
        String startMonth = DateUtil.getFirstDayOfMonth(endMonth);
        List<ReportSpotPriceBreakdown> dayList = reportSpotPriceBreakdownService.queryDayList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> xunList = reportSpotPriceBreakdownService.queryXunList(startMonth, endMonth, company);
        List<ReportSpotPriceBreakdown> monthList = reportSpotPriceBreakdownService.queryMonthList(startMonth, endMonth, company);
        startMonth = DateUtil.getFirstDayDateOfYear(endMonth);
        List<ReportSpotPriceBreakdown> yearList = reportSpotPriceBreakdownService.queryYearList(startMonth, endMonth, company);
        map.put("dayList", dayList);
        map.put("xunList", xunList);
        map.put("monthList", monthList);
        map.put("yearList", yearList);
        return map;
    }
}
