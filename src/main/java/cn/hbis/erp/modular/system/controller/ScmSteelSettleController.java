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
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public static final String POST_URL = "http://price.oltest-hbistc.com:8080/priceweb/priceSellPrice/oneSpacesPhone.htm";


    @ApiOperation(value = "月度产线报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "dw", value = "单位", dataType = "String"),

    })
    @PostMapping("getcx")
    @Async
    public  List<ScmSteelSettle>  getcx(String dw,@RequestParam(required = false) List<String> cx, String startTime, String endTime) throws ParseException {
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
        List<ScmSteelSettle> getcx=scmSteelSettleService.getcx(dw,cx,startTime1+" 00:00:00",endTime1+" 23:59:59",startagainTime,endagainTime);

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

        List<ScmSteelSettle> getpz=scmSteelSettleService.getpz(pz,startTime1+" 00:00:00",endTime1+" 23:59:59",startagainTime,endagainTime);
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
        List<ScmSteelSettle> getzrbm=scmSteelSettleService.getzrbm(zrbm,startTime1+" 00:00:00",endTime1+" 23:59:59");
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
        return  scmSteelSettleService.getCxNamePzg(companyId);
    }



    @ApiOperation(value = "测试")
    @PostMapping("httpURLConnectionPOST")
    @Async
    public Map httpURLConnectionPOST() throws IOException {


                URL url = new URL(POST_URL);

                // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

                // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
                connection.setDoOutput(true);

                // 设置连接输入流为true
                connection.setDoInput(true);

                // 设置请求方式为post
                connection.setRequestMethod("POST");

                // post请求缓存设为false
                connection.setUseCaches(false);

                // 设置该HttpURLConnection实例是否自动执行重定向
                connection.setInstanceFollowRedirects(true);

                // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
                // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
                connection.connect();

                // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
                DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
                String parm = "fkdat=" + URLEncoder.encode("2019-02", "utf-8")+"&"+"companyId="+URLEncoder.encode("9196", "utf-8"); //URLEncoder.encode()方法  为字符串进行编码
                // 将参数输出到连接
                dataout.writeBytes(parm);

                // 输出完成后刷新并关闭流
                dataout.flush();
                dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

                System.out.println(connection.getResponseCode());

                // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder sb = new StringBuilder(); // 用来存储响应数据
                Map map = new HashMap();
                // 循环读取流,若不到结尾处
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                    System.out.println(sb);

                }
                bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
                connection.disconnect(); // 销毁连接
                map.put(sb,"sb");
                return map;
    }


}

