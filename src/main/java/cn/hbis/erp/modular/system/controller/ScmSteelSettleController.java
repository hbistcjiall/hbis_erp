package cn.hbis.erp.modular.system.controller;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import cn.hbis.erp.modular.system.service.ScmSteelSettleService;
import cn.stylefeng.roses.core.util.ToolUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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
    public static final String GET_URL = "http://price.oltest-hbistc.com:8080/priceweb/priceSellPrice/oneSpacesPhone.htm";
    public static final String GET_URL1 = "http://price.oltest-hbistc.com:8080/priceweb/dologin.htm?account=10100050&password=123456";


    @ApiOperation(value = "月度产线报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "dw", value = "单位", dataType = "String"),
            @ApiImplicitParam(name = "zt", value = "品种钢0/高端产品1", dataType = "String"),

    })
    @PostMapping("getcx")
    @Async
    public  List<ScmSteelSettle>  getcx(String dw,@RequestParam(required = false) List<String> cx, String startTime, String endTime,String zt) throws ParseException {
        if(ToolUtil.isNotEmpty(dw)){
            switch (dw){
                case "9580":
                    dw = "唐钢";
                    break;
                case "9727":
                    dw = "邯钢";
                    break;
                case "7778":
                    dw = "邯宝";
                    break;
                case "9193":
                    dw = "宣钢";
                    break;
                case "9196":
                    dw = "承钢";
                    break;
                case "1932":
                    dw = "舞钢";
                    break;
                case "8110":
                    dw = "石钢";
                    break;
                case "8493":
                    dw = "横板";
                    break;
            }
        }
        String  startTime1=(String)DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
//        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date=sDateFormat.parse(startTime);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.MONTH, -1);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        String firstDay = sDateFormat.format(calendar.getTime());
//        Calendar calendar2 = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar2.set(Calendar.DAY_OF_MONTH, 1);
//        calendar2.add(Calendar.DATE, -1);
//        String lastDay = sDateFormat.format(calendar.getTime());
//        System.out.println(firstDay);
//        System.out.println(lastDay);
//        String startagainTime=firstDay;
//        String endagainTime=lastDay;
        List<ScmSteelSettle> getcx=scmSteelSettleService.getcx(dw,cx,startTime1+" 00:00:00",endTime1+" 23:59:59",zt);

        return getcx ;
    }
    @ApiOperation(value = "月度品种报表")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "zt", value = "品种钢0/高端产品1", dataType = "String"),
    })
    @PostMapping("getpz")
    @Async
    public  List<ScmSteelSettle>  getpz(String pz,String startTime,String endTime,String zt) throws ParseException {
        String  startTime1=(String)DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
//        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date date=sDateFormat.parse(startTime);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.MONTH, -1);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        String firstDay = sDateFormat.format(calendar.getTime());
//        Calendar calendar2 = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar2.set(Calendar.DAY_OF_MONTH, 1);
//        calendar2.add(Calendar.DATE, -1);
//        String lastDay = sDateFormat.format(calendar.getTime());
//        System.out.println(firstDay);
//        System.out.println(lastDay);
//        String startagainTime=firstDay;
//        String endagainTime=lastDay;
        List<ScmSteelSettle> getpz=scmSteelSettleService.getpz(pz,startTime1+" 00:00:00",endTime1+" 23:59:59",zt);
        return getpz;
    }
    @ApiOperation(value = "责任部门报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zrbm", value = "责任部门", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "zt", value = "品种钢0/高端产品1", dataType = "String"),
    })
    @PostMapping("getzrbm")
    @Async
    public  List<ScmSteelSettle>  getzrbm(String zrbm,String startTime,String endTime,String zt) {
        String  startTime1=(String)DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getzrbm(zrbm,startTime1+" 00:00:00",endTime1+" 23:59:59",zt);
        return getzrbm;
    }


    @ApiOperation(value = "结算完成情况报表")
    @ApiImplicitParams({

    })
    @PostMapping("getpzjszl")
    @Async
    public  List<ScmSteelSettle>  getpzjszl(String startTime,String endTime) {
        String  startTime1=(String)DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getpzjszl(startTime1+" 00:00:00",endTime1+" 23:59:59");
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
            @ApiImplicitParam(name = "startTime", value = "时间", dataType = "String"),
            @ApiImplicitParam(name = "pz", value = "品种", dataType = "String"),
            @ApiImplicitParam(name = "xszt", value = "销售主题", dataType = "String")
    })
    @PostMapping("getzyjh")
    @Async
    public  List<ScmSteelSettle>  getzyjh(String startTime,String pz,@RequestParam(required = false) List<String> cx,String xszt) {
        String nf = startTime.substring(0,4);
        String yf = startTime.substring(5,7);
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
    public List<ScmSteelSettle> getCxNamePzg(String companyId,String type){
        return  scmSteelSettleService.getCxNamePzg(companyId,type);
    }

}

