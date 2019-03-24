package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.config.properties.HbisProperties;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.ExcelUtil;
import cn.hbis.erp.modular.system.entity.ProtocolAccountDetails;
import cn.hbis.erp.modular.system.entity.RefPartExcel;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 协议户明细控制器
 *
 *
 */
@RestController
@RequestMapping("/protocolAccountDetails")
public class ProtocolAccountDetailsController extends BaseController {

    private static String PREFIX = "/modular/system/protocolAccountDetails";

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

    @ApiOperation(value = "协议上传")
    @PostMapping(value = "/importexcel",headers = "content-type=multipart/form-data",consumes = "MultipartFile/*")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year" ,value = "年份",dataType ="String" ),
            @ApiImplicitParam(name = "file" ,value = "文件",dataType ="form_data" )
    })
    public Map execlimport(@RequestPart("file") MultipartFile file, String year) {
        Map map = new HashMap();
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
                map = excelBeans.stream().collect(Collectors.toMap(ProtocolAccountDetails::getProtocolAccountId, a -> a,(k1, k2)->k1));
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        return map;
    }
}
