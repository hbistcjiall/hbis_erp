package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ReportVarietySteelBean;
import cn.hbis.erp.modular.system.service.ReportTechnologyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子公司品种钢情况
 *
 *
 *
 */
@RestController
@RequestMapping("/reportTechnology")
public class ReportTechnologyController {

    @Autowired
    private ReportTechnologyService reportTechnologyService;

    /*****************************************************1.子公司品种钢情况(河钢集团各子分公司品种钢完成情况)*****************************************/
    /**
     * 获取子公司品种钢情况列表
     *
     *
     */
    @ApiOperation(value = "获取子公司品种钢情况列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryDate" ,value = "月份",dataType ="String" )
    })
    @PostMapping(value = "subsidiaryVarietySteel")
    public Map subsidiaryVarietySteel(String queryDate){
        Map map = new HashMap();
        if(null == queryDate ||"".equals(queryDate)){
            //map.put("message","请选择条件查看报表！");
            queryDate = DateUtil.getNowMonth();
            String startDate = DateUtil.getFirstDayOfMonth(queryDate);
            String endDate = DateUtil.getLastDayOfMonth(queryDate);
        }else{
            String startDate = DateUtil.getFirstDayOfMonth(queryDate);
            String endDate = DateUtil.getLastDayOfMonth(queryDate);
            List<ReportVarietySteelBean> list = reportTechnologyService.subsidiaryVarietySteel(startDate, endDate);
            map.put("list", list);
        }
        //model.addAttribute("query", query);
        return map;
    }
    /**
     * @Title: exportVarietySteelState
     * @Description: 导出  河钢集团各子分公司品种钢完成情况
     * @author: zhangry12988
     * @date: 2017-1-16 下午5:02:57
     * @param
     * @return: void
     */
    @ApiOperation(value = "导出  河钢集团各子分公司品种钢完成情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryDate" ,value = "月份",dataType ="String" )
    })
    @PostMapping(value = "exportSubsidiaryVarietySteel")
    public void exportVarietySteelState(String queryDate, HttpServletRequest request, HttpServletResponse response){
        if(null == queryDate ||"".equals(queryDate)){
            queryDate = DateUtil.getNowMonth();
            String startDate = DateUtil.getFirstDayOfMonth(queryDate);
            String endDate = DateUtil.getLastDayOfMonth(queryDate);
        }else{
            String startDate = DateUtil.getFirstDayOfMonth(queryDate);
            String endDate = DateUtil.getLastDayOfMonth(queryDate);
        }
        //开始导出
        //reportTechnologyService.exportSubsidiaryVarietySteel(startDate, endDate, request, response);
    }
    /**
     * @Title subsidiaryVarietySteelItem
     * @Description 子分公司品种钢情况明细
     * @param
     * @param
     * @return String
     * @throws
     */
    @ApiOperation(value = "获取子分公司品种钢情况明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId" ,value = "子公司",dataType ="String" ),
            @ApiImplicitParam(name = "queryDate" ,value = "月份",dataType ="String" )
    })
    @PostMapping(value = "itemSubsidiaryVarietySteel")
    public Map subsidiaryVarietySteelItem(String companyId, String queryDate){
        Map map = new HashMap();
        if(null==queryDate||"".equals(queryDate)){
            map.put("message","请选择条件查看报表！");
            //query.setQueryDate(DateUtil.getNowMonth());
        }else{
            String startDate = DateUtil.getFirstDayOfMonth(queryDate);
            String endDate = DateUtil.getLastDayOfMonth(queryDate);
            List list = reportTechnologyService.subsidiaryVarietySteelItemPage(companyId, startDate, endDate);
            map.put("data", list);
        }
//        model.addAttribute("company", EnumCompany.toMap());
//        model.addAttribute("query", query);
        return map;
    }
    /**
     * @Title exportItemSubsidiaryVarietySteel
     * @Description  导出子分公司品种钢情况明细
     * @param query
     * @param request
     * @param response
     * @return void
     * @throws
     */
    /*@RequestMapping("/exportItemSubsidiaryVarietySteel")
    public void exportItemSubsidiaryVarietySteel(ReportTechnologyQuery query,HttpServletRequest request, HttpServletResponse response){
        if(null==query.getQueryDate()||"".equals(query.getQueryDate())){
            query.setQueryDate(DateUtil.getNowMonth());
        }else{
            query.setStartDate(DateUtil.getFirstDayOfMonth(query.getQueryDate()));
            query.setEndDate(DateUtil.getLastDayOfMonth(query.getQueryDate()));
        }
        //开始导出
        reportTechnologyManager.exportItemSubsidiaryVarietySteel(query, request, response);
    }*/
}
