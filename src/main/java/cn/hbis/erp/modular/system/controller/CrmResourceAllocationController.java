package cn.hbis.erp.modular.system.controller;


import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.modular.system.entity.Allocation;
import cn.hbis.erp.modular.system.service.CrmResourceAllocationService;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 资源分配维护表 前端控制器
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-21
 */
@RestController
@RequestMapping("/allocation")
public class CrmResourceAllocationController {

    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";
    @Autowired
    private CrmResourceAllocationService crmResourceAllocationService;

    @ApiOperation(value = "通过品种获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "flName", value = "分类名称", dataType = "String")
    })
    @RequestMapping(value = "/selAllocation",method = RequestMethod.POST)
    public List<Allocation> selAllocation(String date,String flName){
        return this.crmResourceAllocationService.selSchedule(date,flName);
    }

    @ApiOperation(value = "通过产线获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序", dataType = "String"),
            @ApiImplicitParam(name = "flName", value = "分类名称", dataType = "String")
    })
    @RequestMapping(value = "/selScheduleByCx",method = RequestMethod.POST)
    public List<Allocation> selScheduleByCx(String date,String sort,String flName){
        return this.crmResourceAllocationService.selScheduleByCx(date,sort,flName);
    }

    @ApiOperation(value="测试",produces="application/octet-stream")
    @GetMapping("export")
    public void export1(String fileName, Map<String, Object> map1, HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        List<Map<String, Object>> list=new ArrayList<>();//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        String da = "河钢";
        int j =1;
        for(int i = 0; i < 100; i++) { //数据库为空,遍历了100个
            Map<String, Object> temp = new HashMap<>();
            if (i>=0&&i<10){
                if (i<5){
                    temp.put("nature", da);
                    temp.put("remark", 1);
                    temp.put("mileage", (i + 1) + "描述");
                }else {
                    temp.put("nature", da);
                    temp.put("remark", 2);
                    temp.put("mileage", (i + 1) + "描述");
                }
            }else
            if (i>=10&&i<20){
                temp.put("nature", da+"ad");
                temp.put("remark", 3);
                temp.put("mileage", (i + 2) + "描述");
            }else
            if (i>=20){
                temp.put("nature", da+"add");
                temp.put("remark", 4);
                temp.put("mileage", (i + 3) + "描述");
            }

            list.add(temp);
        }
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        //标题
        map.put("head_C11", format.format(new Date())+"年单位通车年限统计");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column0_C11", format.format(new Date())+"年单位通车年限统计");
        listmap.add(map);
        //第一行列头（_*表示表头从第几列开始，默认0，_C*表示合并*列，_R*表示合并*行）
        map=new LinkedHashMap<String,Object>();
        map.put("column1_C2_R2", "管养单位");
        map.put("column2_R2", "通车里程（km）");
        map.put("column3_C8", "通车时间（年）");
        listmap.add(map);
        //第二行列头
        map=new LinkedHashMap<String,Object>();
        map.put("column4_3", "10年以上");
        map.put("column5_4", "占比%");
        map.put("column6_5", "5-10年");
        map.put("column7_6", "占比%");
        map.put("column8_7", "3-5年");
        map.put("column9_8", "占比%");
        map.put("column10_9", "3年以下");
        map.put("column11_10", "占比%");
        listmap.add(map);
        //sql语句查询的顺序
        String[] colOrder={"nature","remark","mileage","tenMileage","tenZb","fiveToTenMileage","fiveToTenZb","threeToFiveMileage","threeToFiveZb","threeLessThanMileage","threeLessThanZb"};
        //可能需要做跨行合并的行，将某一列中相同内容的行进行合并
        String[] mergeCols={"nature","remark"};
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        if(ToolUtil.isEmpty(fileName)) {
            fileName = format1.format(new Date());
        } else {
            fileName = fileName.concat(format1.format(new Date()).substring(0,7));
        }
        StringBuffer stringB = new StringBuffer();
        stringB.append(EXPORT_XLSX_FILE_SUFFIX);
        fileName+=stringB;
        try {
            FileOutputStream out = new FileOutputStream("D:/"+fileName);
            exportXlsx(out,fileName,listmap,list,mergeCols,colOrder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportXlsx(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder) {
        XSSFWorkbook wb = new XSSFWorkbook();
        export(out, fileName, headListMap, dataListMap, mergeCols, colOrder, wb);
    }

    static void export(OutputStream out, String fileName, List<Map<String, Object>> headListMap, List<Map<String, Object>> dataListMap, String[] mergeCols, String[] colOrder, XSSFWorkbook wb) {
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

