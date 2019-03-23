/**
 * FileName: test
 * Author:   zhangb
 * Date:     2019/3/23 12:02
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.ExcelUtil;
import cn.hbis.erp.core.util.ExportExcel;
import cn.hbis.erp.modular.system.entity.RefPartExcel;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    @ApiOperation(value="测试",produces="application/octet-stream")
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
        ExportExcel<List<Map<String, Object>>> exportExcel = new ExportExcel<>();
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
            exportExcel.exportXSExcelByColumn(filename.toString(), new String[] {"字段1", "字段2", "字段3"}, new String[] {"id", "number", "description"},
                    list, out ,null);
        } catch (IOException e) {
        }
    }
}
