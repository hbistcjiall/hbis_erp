package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.SalesSubjectMeetingSubjectControl;
import cn.hbis.erp.modular.system.service.SalesSubjectMeetingSubjectService;
import cn.hbis.erp.modular.system.warpper.SalesSubjectMeetingSubjectWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 销售主体例会主体对照控制器
 *
 *
 */
@RestController
@RequestMapping("/SalesSubjectMeetingSubject")
public class SalesSubjectMeetingSubjectController {

    @Autowired
    private SalesSubjectMeetingSubjectService salesSubjectMeetingSubjectService;

    /**
     * 查询销售主体例会主体对照列表
     *
     *
     */
    @ApiOperation(value = "查询销售主体例会主体对照列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "limit" ,value = "每页条数",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    @PostMapping(value = "list")
    public Object list(String companyId, String limit, String page) {
        Page<Map<String, Object>> salesSubjectMeetingSubject = salesSubjectMeetingSubjectService.List(companyId);
        Page wrapped = new SalesSubjectMeetingSubjectWrapper(salesSubjectMeetingSubject).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
    /**
     * 添加销售主体例会主体对照
     *
     *
     */
    @ApiOperation(value = "添加销售主体例会主体对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "saleBodyDes" ,value = "销售主体",dataType ="String" ),
            @ApiImplicitParam(name = "saleBody" ,value = "例会主体",dataType ="String" )
    })
    @PostMapping(value = "insert")
    public Map insert(String companyId, String saleBodyDes, String saleBody) {
        Map map = new HashMap();
        SalesSubjectMeetingSubjectControl ssmsc = new SalesSubjectMeetingSubjectControl();
        ssmsc.setCompanyId(companyId);
        if ("9580".equals(companyId)){
            ssmsc.setCompanyName("唐钢");
        }else if ("9727".equals(companyId)){
            ssmsc.setCompanyName("邯钢");
        }else if ("9193".equals(companyId)){
            ssmsc.setCompanyName("宣钢");
        }else if ("9196".equals(companyId)){
            ssmsc.setCompanyName("承钢");
        }else if ("1932".equals(companyId)){
            ssmsc.setCompanyName("舞钢");
        }
        ssmsc.setSaleBodyDes(saleBodyDes);
        ssmsc.setSaleBody(saleBody);
        ssmsc.setIsDelete("0");
        ssmsc.setGmtCrate(new Date());
        ssmsc.setGmtModify(new Date());
        boolean state = salesSubjectMeetingSubjectService.insert(ssmsc);
        if (state){
            map.put("message","成功");
        }else {
            map.put("message","失败");
        }
        return map;
    }
    /*
     * 修改销售主体例会主体对照
     *
     */
    @ApiOperation(value = "修改销售主体例会主体对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "ID", dataType = "String"),
            @ApiImplicitParam(name = "companyId", value = "钢厂", dataType = "String"),
            @ApiImplicitParam(name = "saleBodyDes" ,value = "销售主体",dataType ="String" ),
            @ApiImplicitParam(name = "saleBody" ,value = "例会主体",dataType ="String" )
    })
    @PostMapping(value = "update")
    public Map update(String Id, String companyId, String saleBodyDes, String saleBody) {
        Map map = new HashMap();
        boolean state = salesSubjectMeetingSubjectService.update(Id, companyId, saleBodyDes, saleBody);
        if (state){
            map.put("message","成功");
        }else {
            map.put("message","失败");
        }
        return map;
    }
    /*
     * 删除销售主体例会主体对照
     *
     */
    @ApiOperation(value = "删除销售主体例会主体对照")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Id", value = "ID", dataType = "String")
    })
    @PostMapping(value = "delete")
    public Map delete(String Id) {
        Map map = new HashMap();
        boolean state = salesSubjectMeetingSubjectService.delete(Id);
        if (state){
            map.put("message","删除成功");
        }else {
            map.put("message","删除失败");
        }
        return map;
    }
}
