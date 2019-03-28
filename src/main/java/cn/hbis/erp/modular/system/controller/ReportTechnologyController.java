package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.modular.system.entity.ReportVarietySteelBean;
import cn.hbis.erp.modular.system.service.ReportTechnologyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    @Async
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
    @ApiOperation(value = "导出河钢集团各子分公司品种钢完成情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryDate" ,value = "月份",dataType ="String" )
    })
    @PostMapping(value = "exportSubsidiaryVarietySteel")
    public void exportVarietySteelState(String queryDate, HttpServletResponse response) {
        String startDate = DateUtil.getFirstDayOfMonth(queryDate);
        String endDate = DateUtil.getLastDayOfMonth(queryDate);
        List<ReportVarietySteelBean> resultList = reportTechnologyService.subsidiaryVarietySteel(startDate, endDate);
        List<Map<String, Object>> list=new ArrayList<>();//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        for(int i = 0; i < resultList.size(); i++) { //数据库为空,遍历了100个
            Map<String, Object> temp = new HashMap<>();
            temp.put("companyName", resultList.get(i).getCompanyName());
            temp.put("totalSteel", resultList.get(i).getTotalSteel());
            temp.put("totalSteelVarieties", resultList.get(i).getTotalSteelVarieties());
            temp.put("scaleSteel", resultList.get(i).getScaleSteel());
            temp.put("featuresProducts", resultList.get(i).getFeaturesProducts());
            temp.put("highProducts", resultList.get(i).getHighProducts());
            temp.put("steelVarieties", resultList.get(i).getSteelVarieties());
            list.add(temp);
        }
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        //标题
        map.put("head_C7", "子公司品种钢情况");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column0_C7", "发货日期" + queryDate);
        listmap.add(map);
        //第一行列头（_*表示表头从第几列开始，默认0，_C*表示合并*列，_R*表示合并*行）
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "单位");
        map.put("column2", "钢材总量(吨)");
        map.put("column3", "品种钢总量(吨)");
        map.put("column4", "品种钢比例(%)");
        map.put("column5", "特色战略产品(吨)");
        map.put("column6", "高端产品(吨)");
        map.put("column7", "一般品种钢(吨)");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"companyName", "totalSteel", "totalSteelVarieties","scaleSteel","featuresProducts", "highProducts", "steelVarieties"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols= {};
        /*- 文件名 -*/
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        filename = new StringBuffer();
        filename.append("子公司品种钢情况");
        filename.append(format1.format(new Date()));
        filename.append(EXPORT_XLSX_FILE_SUFFIX);

        OutputStream out = null;
        try {
            //web浏览通过MIME类型判断文件是excel类型
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.toString().getBytes("UTF-8"), "ISO8859-1"));
            out = response.getOutputStream();
            exportXlsx(out,filename.toString(),listmap,list,mergeCols,colOrder,response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
    @Async
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
     * @Title: exportItemSubsidiaryVarietySteel
     * @Description: 导出子分公司品种钢情况明细
     * @author: zhangry12988
     * @date: 2017-1-16 下午5:02:57
     * @param
     * @return: void
     */
    /*@ApiOperation(value = "导出子分公司品种钢情况明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId" ,value = "子公司",dataType ="String" ),
            @ApiImplicitParam(name = "queryDate" ,value = "月份",dataType ="String" )
    })
    @PostMapping(value = "exportItemSubsidiaryVarietySteel")
    public void exportItemSubsidiaryVarietySteel(Map<String, Object> map ,String companyId, String queryDate, HttpServletResponse response) {
        String startDate = DateUtil.getFirstDayOfMonth(queryDate);
        String endDate = DateUtil.getLastDayOfMonth(queryDate);
        List<ReportVarietySteelItemBean> resultList = reportTechnologyService.exportsubsidiaryVarietySteelItemPage(companyId, startDate, endDate);

        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < resultList.size(); i++) {
            Map<String, Object> temp_ = new HashMap<>();
            temp_.put("companyName", resultList.get(i).getCompanyName());
            temp_.put("actualDate", resultList.get(i).getActualDate());
            temp_.put("productLine", resultList.get(i).getProductLine());
            temp_.put("delivNum", resultList.get(i).getDelivNum());
            temp_.put("delivItem", resultList.get(i).getDelivItem());
            temp_.put("orderNo", resultList.get(i).getOrderNo());
            temp_.put("orderRow", resultList.get(i).getOrderRow());
            temp_.put("productLineClass", resultList.get(i).getProductLineClass());
            temp_.put("productClass", resultList.get(i).getProductClass());
            temp_.put("productClassCrude", resultList.get(i).getProductClassCrude());
            temp_.put("productClassFine", resultList.get(i).getProductClassFine());
            temp_.put("productGrade", resultList.get(i).getProductGrade());
            temp_.put("productType", resultList.get(i).getProductType());
            temp_.put("orderTypeDescribe", resultList.get(i).getOrderTypeDescribe());
            temp_.put("salerName", resultList.get(i).getSalerName());
            temp_.put("totalWeight", resultList.get(i).getTotalWeight());
            temp_.put("variety", resultList.get(i).getVariety());
            temp_.put("material", resultList.get(i).getMaterial());
            temp_.put("specification", resultList.get(i).getSpecification());
            temp_.put("materialInfo", resultList.get(i).getMaterialInfo());
            temp_.put("productCode", resultList.get(i).getProductCode());
            list.add(temp_);
        }
        ExportExcel<List<Map<String, Object>>> exportExcel = new ExportExcel<>();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        if(ToolUtil.isEmpty(map.get("fileName"))) {
            filename = new StringBuffer();
            filename.append("产线品种钢情况明细");
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
            exportExcel.exportXSExcelByColumn(filename.toString(),
                    new String[] {"单位", "实际发货日期", "产线","交货单号","交货单行", "订单号", "订单行","产线分类","产品分类","产品分类(粗分)","产品分类(细分)","产品类别(订单)"
                            ,"产品等级","订单类型描述","售达方名称","发货量","品种","材质","规格","物料描述","产品组编码"},
                    new String[] {"companyName", "actualDate", "productLine","delivNum","delivItem", "orderNo", "orderRow","productLineClass","productClass"
                            ,"productClassCrude","productClassFine","productGrade","productType","orderTypeDescribe","salerName","totalWeight","variety"
                            ,"material","specification","materialInfo","productCode"},
                    list, out ,null);
        } catch (IOException e) {
        }
    }*/
    /**
     * @Title exportItemSubsidiaryVarietySteel
     * @Description  导出子分公司品种钢情况明细
     * @param
     * @param
     * @param
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
    private void exportXlsx(OutputStream out,String fileName,List<Map<String, Object>> headListMap,List<Map<String, Object>> dataListMap,String[] mergeCols,String[] colOrder, HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            Map<String,Object> map=new HashMap<String,Object>();
            XSSFSheet sheet1 = wb.createSheet(fileName);

            //创建表头
            ExcelNewUtil.createExcelHeader(wb, sheet1, headListMap);
            //填入表内容
            ExcelNewUtil.fillExcel(headListMap.size(),mergeCols,colOrder,wb,sheet1,dataListMap);
            //导出
            wb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(wb);
            IOUtils.closeQuietly(out);
        }
    }
}
