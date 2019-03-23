package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.core.util.ExportExcel;
import cn.hbis.erp.modular.system.entity.ReportVarietySteelBean;
import cn.hbis.erp.modular.system.service.ReportTechnologyService;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 子公司品种钢情况
 *
 *
 *
 */
@RestController
@RequestMapping("/reportTechnology")
public class ReportTechnologyController {
    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";

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
    public void export(Map<String, Object> map ,String queryDate, HttpServletResponse response) {
        String startDate = DateUtil.getFirstDayOfMonth(queryDate);
        String endDate = DateUtil.getLastDayOfMonth(queryDate);
        List<ReportVarietySteelBean> resultList = reportTechnologyService.subsidiaryVarietySteel(startDate, endDate);

        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < resultList.size(); i++) { //数据库为空,遍历了100000个
            Map<String, Object> temp_ = new HashMap<>();
            temp_.put("companyName", resultList.get(i).getCompanyName());
            temp_.put("totalSteel", resultList.get(i).getTotalSteel());
            temp_.put("totalSteelVarieties", resultList.get(i).getTotalSteelVarieties());

            temp_.put("featuresProducts", resultList.get(i).getFeaturesProducts());
            temp_.put("highProducts", resultList.get(i).getHighProducts());
            temp_.put("steelVarieties", resultList.get(i).getSteelVarieties());
            list.add(temp_);
        }
        ExportExcel<List<Map<String, Object>>> exportExcel = new ExportExcel<>();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        if(ToolUtil.isEmpty(map.get("fileName"))) {
            filename = new StringBuffer();
            filename.append("子公司品种钢情况");
            filename.append(format.format(new Date()));
        } else {
            filename = ((StringBuffer)map.get("fileName")).insert(0,format.format(new Date()).substring(0,7));
        }
        if(ToolUtil.isEmpty(map.get("excel_type"))) {
            filename.append(EXPORT_XLSX_FILE_SUFFIX);
        } else {
            filename.append(map.get("excel_type"));
        }

        try {
            FileOutputStream out = new FileOutputStream("D:/"+filename.toString());
            exportExcel.exportXSExcelByColumn(filename.toString(), new String[] {"单位", "钢材总量(吨)", "品种钢总量(吨)","特色战略产品(吨)", "高端产品(吨)", "一般品种钢(吨)"},
                    new String[] {"companyName", "totalSteel", "totalSteelVarieties","featuresProducts", "highProducts", "steelVarieties"},
                    list, out ,null);
        } catch (IOException e) {
        }
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
