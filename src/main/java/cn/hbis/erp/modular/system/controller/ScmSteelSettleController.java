package cn.hbis.erp.modular.system.controller;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import cn.hbis.erp.modular.system.service.ScmSteelSettleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 结算清单信息接口 前端控制器
 * </p>
 *
 * @author yaojiaqi
 * @since 2019-03-16
 */
@RestController
@RequestMapping("/scm-steel-settle")
public class ScmSteelSettleController {
    @Autowired
    private ScmSteelSettleService scmSteelSettleService;

    @ApiOperation(value = "月度产线报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "dw", value = "单位", dataType = "String"),

    })
    @PostMapping("getcx")
    @Async
    public  List<ScmSteelSettle>  getcx(String dw,@RequestParam(required = false) List<String> cx, String startTime, String endTime) throws ParseException {
        String  startTime1=(String)DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sDateFormat.parse(startTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = sDateFormat.format(calendar.getTime());
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.set(Calendar.DAY_OF_MONTH, 1);
        calendar2.add(Calendar.DATE, -1);
        String lastDay = sDateFormat.format(calendar.getTime());
        System.out.println(firstDay);
        System.out.println(lastDay);
        String startagainTime=firstDay;
        String endagainTime=lastDay;
        List<ScmSteelSettle> getcx=scmSteelSettleService.getcx(dw,cx,startTime1+"00:00:00",endTime1+"00:00:00",startagainTime,endagainTime);

        return getcx ;
    }
    @ApiOperation(value = "月度品种报表")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
    })
    @PostMapping("getpz")
    @Async
    public  List<ScmSteelSettle>  getpz(String pz,String startTime,String endTime) throws ParseException {
        String  startTime1=(String)DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sDateFormat.parse(startTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = sDateFormat.format(calendar.getTime());
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.set(Calendar.DAY_OF_MONTH, 1);
        calendar2.add(Calendar.DATE, -1);
        String lastDay = sDateFormat.format(calendar.getTime());
        System.out.println(firstDay);
        System.out.println(lastDay);
        String startagainTime=firstDay;
        String endagainTime=lastDay;

        List<ScmSteelSettle> getpz=scmSteelSettleService.getpz(pz,startTime1+"00:00:00",endTime1+"00:00:00",startagainTime,endagainTime);
        return getpz;
    }
    @ApiOperation(value = "责任部门报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zrbm", value = "责任部门", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
    })
    @PostMapping("getzrbm")
    @Async
    public  List<ScmSteelSettle>  getzrbm(String zrbm,String startTime,String endTime) {
        String  startTime1=(String)DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getzrbm(zrbm,startTime1+"00:00:00",endTime1+"00:00:00");
        return getzrbm;
    }


    @ApiOperation(value = "结算完成情况报表")
    @ApiImplicitParams({

    })
    @PostMapping("getpzjszl")
    @Async
    public  List<ScmSteelSettle>  getpzjszl(String startTime,String endTime) {
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getpzjszl(startTime,endTime);
        return getzrbm;
    }


    @ApiOperation(value = "品种钢完成情况报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "zt", value = "状态", dataType = "String"),
    })
    @PostMapping("getpzgjswc")
    @Async
    public  List<ScmSteelSettle>  getpzgjswc(String zt,String startTime,String endTime) {
        List<ScmSteelSettle> getzrbm;
        if(zt.equals("1")){
            getzrbm=scmSteelSettleService.getpzgjswc(startTime,endTime);
        }else{
            getzrbm=scmSteelSettleService.getzgsjswc(startTime,endTime);
        }

        return getzrbm;
    }


    @ApiOperation(value = "资源计划报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nf", value = "年份", dataType = "String"),
            @ApiImplicitParam(name = "yf", value = "月份", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),

            @ApiImplicitParam(name = "xszt", value = "销售主题", dataType = "String"),
    })
    @PostMapping("getzyjh")
    @Async
    public  List<ScmSteelSettle>  getzyjh(String nf,String yf,String pz,@RequestParam(required = false) List<String> cx,String xszt) {

        List<ScmSteelSettle> getzyjh=scmSteelSettleService.getzyjh(nf,yf,pz,cx,xszt);
        return getzyjh;
    }

    @ApiOperation(value = "资源计划查询条件品种")
    @ApiImplicitParams({
    })
    @PostMapping("getzyjhcxtjpz")
    @Async
    public  List<ScmSteelSettle>  getzyjhcxtjpz(String pz) {
        List<ScmSteelSettle> getzyjhcxtjpz = scmSteelSettleService.getzyjhcxtjpz();
        return getzyjhcxtjpz;
    }

    @ApiOperation(value = "资源计划查询条件产线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String")
    })
    @PostMapping("getzyjhcxtjcx")
    @Async
    public  List<ScmSteelSettle>  getzyjhcxtjcx(String pz) {
        List<ScmSteelSettle> getzyjhcxtjcx = scmSteelSettleService.getzyjhcxtjcx(pz);
        return getzyjhcxtjcx;
    }

    @ApiOperation(value = "资源计划查询条件销售主体")
    @ApiImplicitParams({
    })
    @PostMapping("getzyjhcxtjxszt")
    @Async
    public  List<ScmSteelSettle>  getzyjhcxtjxszt() {
        List<ScmSteelSettle> getzyjhcxtjxszt = scmSteelSettleService.getzyjhcxtjxszt();
        return getzyjhcxtjxszt;
    }

    @ApiOperation(value = "产线合同进度报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String"),
            @ApiImplicitParam(name = "companyId", value = "公司ID", dataType = "String")
    })
    @PostMapping("getcxhtjd")
    @Async
    public  List<ScmSteelSettle>  getcxhtjd(String startTime,String endTime,@RequestParam(required = false) List<String> cxName ,String companyId) {
        List<ScmSteelSettle> getcxhtjd = scmSteelSettleService.getcxhtjd(startTime,endTime,cxName,companyId);
        return getcxhtjd;
    }

    @ApiOperation(value = "品种合同进度报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "开始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束日期", dataType = "String"),
            @ApiImplicitParam(name = "pzName", value = "品种", dataType = "String"),
    })
    @PostMapping("getpzhtjd")
    @Async
    public  List<ScmSteelSettle>  getpzhtjd(String pzName,String startTime,String endTime) {
        List<ScmSteelSettle> getpzhtjd = scmSteelSettleService.getpzhtjd(startTime,endTime,pzName);
        return getpzhtjd;
    }

    @PostMapping("getCxName")
    @Async
    public List<ScmSteelSettle> getCxName(String companyId){
        return  scmSteelSettleService.getCxName(companyId);
    }

    @PostMapping("getCxNamePzg")
    @Async
    public List<ScmSteelSettle> getCxNamePzg(String companyId){
        return  scmSteelSettleService.getCxName(companyId);
    }
}

