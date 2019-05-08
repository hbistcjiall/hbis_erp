package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.service.ProductSalesProtocolAccountSalesService;
import cn.hbis.erp.modular.system.warpper.ProductSalesProtocolAccountSalesWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.hbis.erp.modular.system.controller.ExportController.export;

/**
 * 产品总销量及销售公司协议户销量统计控制器
 *
 *
 */
@RestController
@RequestMapping("/productSalesProtocolAccountSales")
public class ProductSalesProtocolAccountSalesController {

    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";

    @Autowired
    private ProductSalesProtocolAccountSalesService productSalesProtocolAccountSalesService;
    /**
     * 查询产品总销量及销售公司协议户销量统计
     *
     *
     */
    @ApiOperation(value = "查询产品总销量及销售公司协议户销量统计")
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
        Page<Map<String, Object>> productSalesProtocolAccountSales = productSalesProtocolAccountSalesService.list(varieties, beginTime, endTime);
        Page wrapped = new ProductSalesProtocolAccountSalesWrapper(productSalesProtocolAccountSales).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
    /**
     * 导出  产品总销量及销售公司协议户销量统计
     *
     *
     */
    @ApiOperation(value = "导出产品总销量及销售公司协议户销量统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "统计月份开始", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "统计月份结束", dataType = "String")
    })
    @GetMapping(value = "export")
    public void exportVarietySteelState(String varieties, String beginTime, String endTime, HttpServletResponse response) {
        beginTime = DateUtil.getFirstDayOfMonth(beginTime);
        endTime = DateUtil.getLastDayOfMonth(endTime);
        List<Map<String, Object>> productSalesProtocolAccountSales = productSalesProtocolAccountSalesService.exportList(varieties, beginTime, endTime);
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < productSalesProtocolAccountSales.size(); i++) {
            Map<String,Object> map = productSalesProtocolAccountSales.get(i);
            Map<String, Object> temp = new HashMap<>();
            if (map.get("COMPANYNAME") != null){
                temp.put("companyName", map.get("COMPANYNAME"));
            }else{
                temp.put("companyName", "合计");
            }
            if(map.get("VARIETIES") != null){
                temp.put("varieties", map.get("VARIETIES"));
            }else{
                temp.put("varieties", "小计");
            }
            temp.put("totalSales", map.get("TOTALSALES"));
            temp.put("protocolAccontNum", map.get("PROTOCOLACCOUNTNUM"));
            temp.put("protocolSalesYear", map.get("PROTOCOLSALESYEAR"));
            temp.put("totalProtocolSales", map.get("TOTALPROTOCOLSALES"));
            temp.put("totalProtocolSalesOfYear", map.get("TOTALPROTOCOLSALESOFYEAR"));
            temp.put("totalProtocolSalesOfProduct", map.get("TOTALPROTOCOLSALESOFPRODUCT"));
            temp.put("zibanProtocolSales", map.get("ZIBANPROTOCOLSALES"));
            temp.put("zibanProtocolSalesOfTotal", map.get("ZIBANPROTOCOLSALESOFTOTAL"));
            temp.put("xieyiProtocolSales", map.get("XIEYIPROTOCOLSALES"));
            temp.put("xieyiProtocolSalesOfTotal", map.get("XIEYIPROTOCOLSALESOFTOTAL"));
            list.add(temp);
        }
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C12", "产品总销量及销售公司协议户销量统计表");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "子公司");
        map.put("column2", "产品类别");
        map.put("column3", "总销量");
        map.put("column4", "协议户数");
        map.put("column5", "年协议量");
        map.put("column6", "总公司协议户总销量");
        map.put("column7", "总公司协议户总销量占年协议量比");
        map.put("column8", "总公司协议户总销量占产品总销量比");
        map.put("column9", "协议户中自办公司销售量");
        map.put("column10", "协议户中自办公司销量占协议总销量比");
        map.put("column11", "协议户中执行协议政策总销量");
        map.put("column12", "协议户中执行协议政策总销量占协议总销量比");
        listmap.add(map);
        String[] colOrder={"companyName", "varieties", "totalSales","protocolAccontNum","protocolSalesYear", "totalProtocolSales",
                "totalProtocolSalesOfYear","totalProtocolSalesOfProduct","zibanProtocolSales","zibanProtocolSalesOfTotal","xieyiProtocolSales","xieyiProtocolSalesOfTotal"};
        String[] mergeCols= {};
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        filename = new StringBuffer();
        filename.append("产品总销量及销售公司协议户销量统计表");
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
    public void exportXlsx(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();
        export(out, fileName, headListMap, dataListMap, mergeCols, colOrder, wb);
    }
}
