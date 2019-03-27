package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.config.properties.HbisProperties;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.ExcelNewUtil;
import cn.hbis.erp.core.util.ExcelUtil;
import cn.hbis.erp.modular.system.entity.ProtocolAccountDetails;
import cn.hbis.erp.modular.system.model.ProtocolAccountDetailsDto;
import cn.hbis.erp.modular.system.service.ProtocolAccountDetailsService;
import cn.hbis.erp.modular.system.warpper.ProtocolAccountDetailsWrapper;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 协议户明细控制器
 *
 *
 */
@RestController
@RequestMapping("/protocolAccountDetails")
public class ProtocolAccountDetailsController extends BaseController {

    private static String PREFIX = "/modular/system/protocolAccountDetails";
    private static String EXPORT_XLSX_FILE_SUFFIX = ".xlsx";
    @Autowired
    private ProtocolAccountDetailsService protocolAccountDetailsService;
    @Autowired
    private HbisProperties hbisProperties;

    /**
     * 获取所有部门列表
     *
     *
     */
    @ApiOperation(value = "查询协议户明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "protocolYear", value = "协议年份", dataType = "String"),
            @ApiImplicitParam(name = "steelMills", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping(value = "list")
     public Object list(String varieties, String beginTime, String endTime, String protocolYear, String steelMills, String limit, String page) {
        Page<Map<String, Object>> protocolAccounts = protocolAccountDetailsService.searchList(varieties, beginTime, endTime, protocolYear, steelMills);
        Page wrapped = new ProtocolAccountDetailsWrapper(protocolAccounts).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
    /**
     * 获取协议户明细
     *
     *
     */
    @ApiOperation(value = "协议户明细详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "protocolAccountId", value = "协议户明细ID", dataType = "String")
    })
    @PostMapping("detail")
    public Object detail(String protocolAccountId) {
        ProtocolAccountDetails protocolAccountDetails = protocolAccountDetailsService.getById(protocolAccountId);
        ProtocolAccountDetailsDto protocolAccountDetailsDto = new ProtocolAccountDetailsDto();
        BeanUtil.copyProperties(protocolAccountDetails, protocolAccountDetailsDto);
        return protocolAccountDetailsDto;
    }
    /**
     * 修改协议户明细
     *
     *
     */
    @ApiOperation(value = "修改协议户明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "protocolAccountId", value = "协议ID", dataType = "String"),
            @ApiImplicitParam(name = "protocolYear", value = "协议年份", dataType = "String"),
            @ApiImplicitParam(name = "accountName", value = "用户名称", dataType = "String"),
            @ApiImplicitParam(name = "varieties" ,value = "品种",dataType ="String" ),
            @ApiImplicitParam(name = "mainSalesRegional" ,value = "主销售区域",dataType ="String" ),
            @ApiImplicitParam(name = "aidedSalesRegionalOne", value = "辅助销售区域一", dataType = "String"),
            @ApiImplicitParam(name = "aidedSalesRegionalTwo", value = "辅助销售区域二", dataType = "String"),
            @ApiImplicitParam(name = "steelMills" ,value = "钢厂",dataType ="String" ),
            @ApiImplicitParam(name = "annualAgreementVolume" ,value = "年协议量（吨）",dataType ="String" )
    })
    @PostMapping(value = "update")
    public Map protocolAccountDetailsUpdate(String protocolAccountId, String protocolYear, String accountName, String varieties, String mainSalesRegional, String aidedSalesRegionalOne, String aidedSalesRegionalTwo, String steelMills, String annualAgreementVolume) {
        Map map = new HashMap();
        boolean flag = protocolAccountDetailsService.update(protocolAccountId,protocolYear,accountName,varieties,mainSalesRegional,aidedSalesRegionalOne,aidedSalesRegionalTwo,steelMills,annualAgreementVolume);
        if(flag){
            map.put("massage","修改成功");
        }else{
            map.put("massage","修改失败");
        }
        return map;
    }
    /**
     * 协议户明细删除
     *
     *
     */
    @ApiOperation(value = "协议户明细删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "protocolAccountId" ,value = "协议户明细Id",dataType ="String" )
    })
    @PostMapping("delete")
    public Map delete(String protocolAccountId){
        boolean flag = protocolAccountDetailsService.delete(protocolAccountId);
        Map map = new HashMap();
        if(flag){
            map.put("massage","删除成功");
        }else{
            map.put("massage","删除失败");
        }
        return map;
    }
    /**
     * 协议户明细批量删除
     *
     *
     */
    @ApiOperation(value = "协议户明细批量删除")
    @PostMapping("deleteList")
    public Map delete(@RequestParam(value = "idList") List<String> idList){
        boolean flag = protocolAccountDetailsService.deleteList(idList);
        Map map = new HashMap();
        if(flag){
            map.put("massage","删除成功");
        }else{
            map.put("massage","删除失败");
        }
        return map;
    }
    /**
     * 导出协议户明细列表
     *
     *
     */
    @ApiOperation(value = "导出协议户明细列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "varieties", value = "产品类别", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "protocolYear", value = "协议年份", dataType = "String"),
            @ApiImplicitParam(name = "steelMills", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping(value = "exportlist")
    public void exportlist(String varieties, String beginTime, String endTime, String protocolYear, String steelMills, String limit, String page) {
        Page<Map<String, Object>> protocolAccounts = protocolAccountDetailsService.searchList(varieties, beginTime, endTime, protocolYear, steelMills);
        List<Map<String, Object>> list=new ArrayList<>();
        List<Map<String,Object>> resultList = protocolAccounts.getRecords();
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i = 0; i < protocolAccounts.getSize(); i++) {
            Map<String,Object> map = resultList.get(i);
            Map<String, Object> temp = new HashMap<>();
            temp.put("uploadTime", format2.format(map.get("UPLOADTIME")));
            temp.put("protocolYear", map.get("PROTOCOLYEAR"));
            temp.put("accountName", map.get("ACCOUNTNAME"));
            temp.put("supplyMode", map.get("SUPPLYMODE"));
            temp.put("varieties", map.get("VARIETIES"));
            temp.put("mainSalesRegional", map.get("MAINSALESREGIONAL"));
            if (map.get("AIDEDSALESREGIONALONE") == null){
                temp.put("aidedSalesRegionalOne", "-");
            }else{
                temp.put("aidedSalesRegionalOne", map.get("AIDEDSALESREGIONALONE"));
            }
            if (map.get("AIDEDSALESREGIONALTWO") == null){
                temp.put("aidedSalesRegionalTwo", "-");
            }else{
                temp.put("aidedSalesRegionalTwo", map.get("AIDEDSALESREGIONALTWO"));
            }
            temp.put("steelMills", map.get("STEELMILLS"));
            temp.put("annualAgreementVolume", map.get("ANNUALAGREEMENTVOLUME"));
            list.add(temp);
        }
        List<Map<String, Object>> listmap=new ArrayList<Map<String, Object>>();
        Map<String,Object> map=new LinkedHashMap<String,Object>();
        map.put("head_C10", "协议户明细列表");
        listmap.add(map);
        map=new LinkedHashMap<String,Object>();
        map.put("column1", "上传时间");
        map.put("column2", "协议年份");
        map.put("column3", "用户名称（全称）");
        map.put("column4", "供货方式");
        map.put("column5", "品种");
        map.put("column6", "主销售区域");
        map.put("column7", "辅助销售区域一");
        map.put("column8", "辅助销售区域二");
        map.put("column9", "钢厂");
        map.put("column10", "年协议量（吨）");
        listmap.add(map);
        String[] colOrder={"uploadTime","protocolYear","accountName","supplyMode","varieties","mainSalesRegional","aidedSalesRegionalOne","aidedSalesRegionalTwo","steelMills","annualAgreementVolume"};
        String[] mergeCols= {};
        DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer filename = null;
        filename = new StringBuffer();
        filename.append("协议户明细列表");
        filename.append(format1.format(new Date()));
        filename.append(EXPORT_XLSX_FILE_SUFFIX);
        try {
            FileOutputStream out = new FileOutputStream("D:/"+filename.toString());
            exportXlsx(out,filename.toString(),listmap,list,mergeCols,colOrder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "year" ,value = "年份",dataType ="String" ),
    })
    @ApiOperation(value = "协议上传", notes = "协议上传")
    @PostMapping(value = "/importexcel",headers = "content-type=multipart/form-data",consumes = "MultipartFile/*")
    public Map<String, ProtocolAccountDetails> execlimport(@RequestPart("file") MultipartFile file, String year) {
        Map<String, ProtocolAccountDetails> map = new HashMap<String, ProtocolAccountDetails>();
          if (!ToolUtil.isEmpty(file)) {
            try {
                List<ProtocolAccountDetails> excelBeans = ExcelUtil.readExcel(file,ProtocolAccountDetails.class);
                System.out.println(excelBeans.size());
                for(ProtocolAccountDetails ep : excelBeans){
                   ep.setProtocolYear(year);
                }
                for (ProtocolAccountDetails ep : excelBeans){
                    protocolAccountDetailsService.save(ep);
                }
                //map = excelBeans.stream().collect(Collectors.toMap(ProtocolAccountDetails::getProtocolAccountId, a -> a,(k1, k2)->k1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
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
