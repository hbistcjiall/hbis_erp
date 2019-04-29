package cn.hbis.erp.modular.system.controller;


import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ScmFilter;
import cn.hbis.erp.modular.system.service.ScmFilterService;
import cn.hbis.erp.modular.system.warpper.MenuWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiarsi
 * @since 2019-04-18
 */
@RestController
@RequestMapping("/scm-filter")
public class ScmFilterController extends BaseController {

    @Autowired
    private ScmFilterService scmFilterService;

    @ApiOperation(value = "通过表名获取字段")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "String",required = true),
            @ApiImplicitParam(name = "columnName", value = "字段名", dataType = "String")
    })
    @PostMapping("/getColumnName")
    public List getColumnName(String tableName, String columnName){
        return scmFilterService.getColumnName(tableName,columnName);
    }

    @ApiOperation(value = "通过字段获取字段值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "String"),
            @ApiImplicitParam(name = "columnName", value = "字段名", dataType = "String"),
            @ApiImplicitParam(name = "columnValue", value = "字段值", dataType = "String")
    })
    @PostMapping("/getColumnValue")
    public List getColumnValue(String tableName,String columnName,String columnValue){
        return scmFilterService.getColumnValue(tableName,columnName,columnValue);
    }

    @ApiOperation(value = "获取最大code值")
    @PostMapping("/getMaxCode")
    public String getMaxCode(){
        return scmFilterService.getMaxCode();
    }

    @ApiOperation(value = "获取过滤列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "业务类型", dataType = "String"),
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "String"),
            @ApiImplicitParam(name = "column", value = "过滤字段", dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "每页多少条数据", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "第几页", dataType = "String")
    })
    @PostMapping("/getFilterList")
    public Object getFilterList(String name,String tableName,String column,String limit,String page){
        Page<Map<String, Object>> menus = this.scmFilterService.selList(name,tableName,column);
        Page<Map<String, Object>> wrap = new MenuWrapper(menus).wrap();
        return PageFactory.createPageInfo(wrap);
    }

    @ApiOperation(value = "新增过滤")
    @PostMapping("addFilter")
    public ResponseData add(ScmFilter scmFilter) {
        this.scmFilterService.addFilter(scmFilter);
        return SUCCESS_TIP;
    }

    @ApiOperation(value = "修改过滤")
    @PostMapping("editFilter")
    public ResponseData editFilter(ScmFilter scmFilter) {
        if (ToolUtil.isEmpty(scmFilter.getId())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.scmFilterService.updateFilter(scmFilter);
        return SUCCESS_TIP;
    }

    @ApiOperation(value = "删除过滤")
    @PostMapping("deleteFilter")
    public ResponseData deleteFilter(@RequestParam List<String> ids) {
        if (ids.size()<=0) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.scmFilterService.deleteFilter(ids);
        return SUCCESS_TIP;
    }

    /**
     * 部门详情
     *
     *
     */
    @ApiOperation(value = "过滤详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filterId", value = "过滤ID", dataType = "String")
    })
    @PostMapping("filterDetail")
    public Object filterDetail(String filterId) {
        if (ToolUtil.isEmpty(filterId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        return this.scmFilterService.getById(filterId);
    }

    @ApiOperation(value = "过滤结果查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "公司ID", dataType = "String"),
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "String",required = true),
            @ApiImplicitParam(name = "fName", value = "过滤类型", dataType = "String",required = true),
            @ApiImplicitParam(name = "limit", value = "每页多少条数据", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "第几页", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String")
    })
    @PostMapping("selFilterDzl")
    public Object selFilterDzl(String companyId,String tableName,String fName,String limit,String page,String startTime,String endTime) {
        startTime = DateUtil.getFirstDayOfMonth(startTime).replaceAll("[[\\s-:punct:]]","").substring(0,8);
        endTime = DateUtil.getLastDayOfMonth(endTime).replaceAll("[[\\s-:punct:]]","").substring(0,8);
        Page<Map<String, Object>> list = null;
        Page<Map<String, Object>> wrap = null;
        ScmFilter scmFilter = scmFilterService.getCode(fName);
        if (tableName.equals("SCM_SALE_ORDER")){
            list = scmFilterService.selFilterByOrder(startTime,endTime,scmFilter.getCode(),"sso."+scmFilter.getFColumn(),companyId);
            wrap = new MenuWrapper(list).wrap();
        }else if (tableName.equals("SCM_DELIVERY_DETAIL")){
            list = scmFilterService.selFilterByDelivery(startTime,endTime,scmFilter.getCode(),"detail."+scmFilter.getFColumn(),companyId);
            wrap = new MenuWrapper(list).wrap();
        }else if (tableName.equals("SCM_STEEL_SETTLE")){
            list = scmFilterService.selFilterBySteel(startTime,endTime,scmFilter.getCode(),"settle."+scmFilter.getFColumn(),companyId);
            wrap = new MenuWrapper(list).wrap();
        }

        return PageFactory.createPageInfo(wrap);
    }

    @ApiOperation(value = "查询过滤类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "String",required = true)
    })
    @PostMapping("selFilterColumn")
    public Object selFilterColumn(String tableName) {
        return this.scmFilterService.selFilterColumn(tableName);
    }

    @ApiOperation(value = "查询过滤表查询条件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "col", value = "要查询的字段名", dataType = "String",required = true),
            @ApiImplicitParam(name = "sel", value = "查询条件名", dataType = "String"),
            @ApiImplicitParam(name = "tableName", value = "表名", dataType = "String")
    })
    @PostMapping("selCondition")
    public Object selCondition(String col,String sel,String tableName) {
        return this.scmFilterService.selCondition(col,sel,tableName);
    }
}

