/**
 * FileName: test
 * Author:   zhangb
 * Date:     2019/3/23 12:02
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.core.util.ExcelUtil;
import cn.hbis.erp.modular.system.entity.RefPartExcel;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
public class TestController extends BaseController {
    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";

    //	private static String EXPORT_XLS_FILE_SUFFIX = ".xls";

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/fileUpload",headers = "content-type=multipart/form-data",consumes = "MultipartFile/*")
    public Map<String, RefPartExcel> fileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, RefPartExcel> map = new HashMap<String, RefPartExcel>();
        // 判断文件是否为空
        if (!ToolUtil.isEmpty(file)) {
            try {
                List<RefPartExcel> excelBeans = ExcelUtil.readExcel(file,RefPartExcel.class);
                System.out.println(excelBeans.size());
                for(RefPartExcel ep : excelBeans){
                    System.out.println(ep.toString());
                }
                //........逻辑
                map = excelBeans.stream().collect(Collectors.toMap(RefPartExcel::getPartNo, a -> a,(k1, k2)->k1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    /*@ApiOperation(value="测试",produces="application/octet-stream")
    @GetMapping("export")
    public void export(Map<String, Object> map ,HttpServletResponse response) {
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < 100; i++) { //数据库为空,遍历了100000个
            Map<String, Object> temp_ = new HashMap<>();
            temp_.put("id", i + 1);
            temp_.put("number", i + 1);
            temp_.put("description", (i + 1) + "描述");
            list.add(temp_);
        }
        //ExportExcel<List<Map<String, Object>>> exportExcel = new ExportExcel<>();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        if(ToolUtil.isEmpty(map.get("fileName"))) {
            filename = new StringBuffer();
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
            *//*exportExcel.exportXSExcelByColumn(filename.toString(), new String[] {"字段1", "字段2", "字段3"}, new String[] {"id", "number", "description"},
                    list, out ,null);*//*
        } catch (IOException e) {
        }
    }*/
    @ApiOperation(value="测试",produces="application/octet-stream")
    @GetMapping("export")
    public void export1(Map<String, Object> map1,HttpServletResponse response){
        DateFormat format = new SimpleDateFormat("yyyy");
        List<Map<String, Object>> list=new ArrayList<>();//表内容集合，从数据库查，需要合并的列要进行分组，否则需要做合并的时候可能达不到理想结果
        String da = "河钢";
        int j =1;
        for(int i = 0; i < 100; i++) { //数据库为空,遍历了100个
            Map<String, Object> temp = new HashMap<>();
            if (i>=0&&i<10){
                temp.put("nature", da);
                temp.put("remark", 1);
                temp.put("mileage", (i + 1) + "描述");
            }else
            if (i>=10&&i<20){
                temp.put("nature", da+"ad");
                temp.put("remark", 2);
                temp.put("mileage", (i + 2) + "描述");
            }else
            if (i>=20){
                temp.put("nature", da+"add");
                temp.put("remark", 3);
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
        StringBuffer filename = null;
        if(ToolUtil.isEmpty(map1.get("fileName"))) {
            filename = new StringBuffer();
            filename.append(format1.format(new Date()));
        } else {
            filename = ((StringBuffer)map1.get("fileName")).insert(0,format1.format(new Date()).substring(0,7));
        }
        if(ToolUtil.isEmpty(map1.get("excel_type"))) {
            filename.append(EXPORT_XLSX_FILE_SUFFIX);
        } else {
            filename.append(map1.get("excel_type"));
        }
            try {
                FileOutputStream out = new FileOutputStream("D:/"+filename.toString());
                exportXlsx(out,filename.toString(),listmap,list,mergeCols,colOrder);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    private void exportXlsx(FileOutputStream out,String fileName,List<Map<String, Object>> headListMap,List<Map<String, Object>> dataListMap,String[] mergeCols,String[] colOrder) {
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
