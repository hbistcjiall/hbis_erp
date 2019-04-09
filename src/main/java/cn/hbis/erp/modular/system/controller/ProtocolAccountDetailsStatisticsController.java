package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.modular.system.service.ProtocolAccountDetailsStatisticsService;
import cn.hbis.erp.modular.system.warpper.ProtocolAccountDetailsStatisticsWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 协议户明细统计控制器
 *
 *
 */
@RestController
@RequestMapping("/protocolAccountDetailsStatistics")
public class ProtocolAccountDetailsStatisticsController {

    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";

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
        Map map = new HashMap();
        Page<Map<String, Object>> protocolAccountDetailsStatistics = protocolAccountDetailsStatisticsService.List(varieties, beginTime, endTime, supplyMode, companyIdList);
        Page wrapped = new ProtocolAccountDetailsStatisticsWrapper(protocolAccountDetailsStatistics).wrap();
        map.put("list",PageFactory.createPageInfo(wrapped));
        List<Map<String, Object>> list = protocolAccountDetailsStatisticsService.sumList(varieties, beginTime, endTime, supplyMode, companyIdList);
        map.put("sum",list);
        return map;
    }
    /**
     * 导出  销售总公司协议户销量明细统计列表
     *
     *
     */
    @ApiOperation(value = "导出销售总公司协议户销量明细统计列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "统计月份开始", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "统计月份结束", dataType = "String"),
            @ApiImplicitParam(name = "supplyMode", value = "供货方式", dataType = "String")
    })
    @GetMapping(value = "exportSubsidiaryVarietySteel")
    public void exportProtocolSalesDetailStatistics(String varieties, String beginTime, String endTime, String supplyMode, @RequestParam(value = "idList") List<String> idList, HttpServletResponse response) {
        beginTime = DateUtil.getFirstDayOfMonth(beginTime);
        endTime = DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> protocolAccountDetailsStatistics = protocolAccountDetailsStatisticsService.searchList(varieties, beginTime, endTime, supplyMode, idList);
        List<Map<String, Object>> sumlist = protocolAccountDetailsStatisticsService.sumList(varieties, beginTime, endTime, supplyMode, idList);
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < protocolAccountDetailsStatistics.size(); i++) {
            Map<String, Object> temp = new HashMap<>();
            Map<String,Object> map = protocolAccountDetailsStatistics.get(i);
            temp.put("xuhao",i + 1);
            if(map.get("STATISTICSTIME") != null){
                temp.put("statisticsTime", map.get("STATISTICSTIME"));
            }else{
                temp.put("statisticsTime", "");
            }
            if(map.get("ACCOUNTNAME") != null){
                temp.put("accountName", map.get("ACCOUNTNAME"));
            }else{
                temp.put("accountName", "");
            }
            if (map.get("SUPPLYMODE") != null){
                temp.put("supplyMode", map.get("SUPPLYMODE"));
            }else{
                temp.put("supplyMode", "");
            }
            if(map.get("VARIETIES") != null){
                temp.put("varieties", map.get("VARIETIES"));
            }else{
                temp.put("varieties", "");
            }
            if (map.get("MAINSALESREGIONAL") != null){
                temp.put("mainSalesRegional", map.get("MAINSALESREGIONAL"));
            }else{
                temp.put("mainSalesRegional","");
            }
            if (map.get("AIDEDSALESREGIONALONE") != null){
                temp.put("aidedSalesRegionalOne", map.get("AIDEDSALESREGIONALONE"));
            }else{
                temp.put("aidedSalesRegionalOne", "");
            }
            if (map.get("AIDEDSALESREGIONALTWO") != null){
                temp.put("aidedSalesRegionalTwo", map.get("AIDEDSALESREGIONALTWO"));
            }else{
                temp.put("aidedSalesRegionalTwo", "");
            }
            if(map.get("STEELMILLS") != null){
                temp.put("steelMills", map.get("STEELMILLS"));
            }else{
                temp.put("steelMills", "");
            }
            temp.put("annualagreementvolume", map.get("ANNUALAGREEMENTVOLUME"));
            temp.put("orderMount", map.get("ORDERMOUNT"));
            temp.put("protocolOrderMount", map.get("PROTOCOLORDERMOUNT"));
            list.add(temp);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("xuhao","合计");
        map1.put("statisticsTime","");
        map1.put("accountName",sumlist.get(0).get("ACCOUNTNAME"));
        map1.put("supplyMode","");
        map1.put("varieties","");
        map1.put("mainSalesRegional","");
        map1.put("aidedSalesRegionalOne","");
        map1.put("aidedSalesRegionalTwo","");
        map1.put("steelMills","");
        map1.put("annualagreementvolume",sumlist.get(0).get("ANNUALAGREEMENTVOLUME"));
        map1.put("orderMount",sumlist.get(0).get("ORDERMOUNT"));
        map1.put("protocolOrderMount",sumlist.get(0).get("PROTOCOLORDERMOUNT"));
        list.add(map1);
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C12", "销售总公司协议户销量明细统计表");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "序号");
        map.put("column2", "统计月份");
        map.put("column3", "用户名称(全称)");
        map.put("column4", "供货方式");
        map.put("column5", "品种");
        map.put("column6", "主要销售区域");
        map.put("column7", "辅助销售区域一");
        map.put("column8", "辅助销售区域二");
        map.put("column9", "钢厂");
        map.put("column10", "年协议量（吨）");
        map.put("column11", "当期协议销量（吨）");
        map.put("column12", "当期执行集团协议价值销量（吨）");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"xuhao","statisticsTime", "accountName", "supplyMode","varieties","mainSalesRegional", "aidedSalesRegionalOne", "aidedSalesRegionalTwo","steelMills","annualagreementvolume","orderMount","protocolOrderMount"};
        String[] mergeCols= {};
        StringBuffer filename = null;
        filename = new StringBuffer();
        filename.append("销售总公司明细");
        filename.append(EXPORT_XLSX_FILE_SUFFIX);
        OutputStream out = null;
        try {
            //web浏览通过MIME类型判断文件是excel类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;filename= "+new String(filename.toString().getBytes("UTF-8"), "ISO8859-1"));
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

    private void exportXlsx(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, HttpServletResponse response) {
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
