package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.config.properties.HbisProperties;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.ProtocolAccountDetails;
import cn.hbis.erp.modular.system.service.ProtocolAccountDetailsService;
import cn.hbis.erp.modular.system.warpper.ProtocolAccountDetailsWrapper;
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
import java.util.UUID;

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

    @ApiOperation(value = "协议上传")
    @RequestMapping(value = "/importexcel" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year" ,value = "年份",dataType ="String" ),
            @ApiImplicitParam(name = "filepath" ,value = "文件路径",dataType ="String" )
    })
    public Map execlimport(@RequestPart("file") MultipartFile picture, String year){
        Map map= new HashMap();
        String pictureName =  "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        String fileSavePath = hbisProperties.getFileUploadPath();
        String filepath = fileSavePath+pictureName;
        try{
            List<ProtocolAccountDetails> list = protocolAccountDetailsService.excleIn(filepath,year);
            for (int i=0;i<list.size();i++){
                protocolAccountDetailsService.save(list.get(i));
            }
            map.put("massage","导入成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("massage","导入失败");
        }
        return map;
    }
}
