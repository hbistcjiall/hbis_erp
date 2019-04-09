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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
        List list = protocolAccountDetailsStatisticsService.sumList(varieties, beginTime, endTime, supplyMode, companyIdList);
        map.put("sum",list);
        return map;
    }
    /**
     * 导出  协议户明细统计列表
     *
     *
     */
    @ApiOperation(value = "导出协议户明细统计列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "统计月份开始", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "统计月份结束", dataType = "String"),
            @ApiImplicitParam(name = "supplyMode", value = "供货方式", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @GetMapping(value = "exportSubsidiaryVarietySteel")
    public void exportVarietySteelState(String varieties, String beginTime, String endTime, String supplyMode, @RequestParam(value = "idList") List<String> idList,
                                        String limit, String page, HttpServletResponse response) {
        beginTime = DateUtil.getFirstDayOfMonth(beginTime);
        endTime = DateUtil.getLastDayOfMonth(endTime);
            List<Map<String, Object>> protocolAccountDetailsStatistics = protocolAccountDetailsStatisticsService.searchList(varieties, beginTime, endTime, supplyMode, idList);
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < protocolAccountDetailsStatistics.size(); i++) {
            Map<String, Object> temp = new HashMap<>();
            Map<String,Object> map = protocolAccountDetailsStatistics.get(i);
            temp.put("statisticsTime", map.get("STATISTICSTIME"));
            temp.put("accountName", map.get("ACCOUNTNAME"));
            temp.put("supplyMode", map.get("SUPPLYMODE"));
            temp.put("varieties", map.get("VARIETIES"));
            temp.put("mainSalesRegional", map.get("MAINSALESREGIONAL"));
            temp.put("aidedSalesRegionalOne", map.get("AIDEDSALESREGIONALONE"));
            temp.put("aidedSalesRegionalTwo", map.get("AIDEDSALESREGIONALTWO"));
            temp.put("steelMills", map.get("STEELMILLS"));
            temp.put("annualagreementvolume", map.get("ANNUALAGREEMENTVOLUME"));
            temp.put("orderMount", map.get("ORDERMOUNT"));
            temp.put("protocolOrderMount", map.get("PROTOCOLORDERMOUNT"));
            list.add(temp);
        }
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C11", "销售总公司协议户销量明细统计表");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "统计月份");
        map.put("column2", "用户名称(全称)");
        map.put("column3", "供货方式");
        map.put("column4", "品种");
        map.put("column5", "主要销售区域");
        map.put("column6", "辅助销售区域一");
        map.put("column7", "辅助销售区域二");
        map.put("column8", "钢厂");
        map.put("column9", "年协议量（吨）");
        map.put("column10", "当期协议销量（吨）");
        map.put("column11", "当期执行集团协议价值销量（吨）");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"statisticsTime", "accountName", "supplyMode","varieties","mainSalesRegional", "aidedSalesRegionalOne",
                "aidedSalesRegionalTwo","steelMills","annualagreementvolume","orderMount","protocolOrderMount"};
        String[] mergeCols= {};
        StringBuffer filename = null;
        filename = new StringBuffer();
        filename.append("销售总公司明细");
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
