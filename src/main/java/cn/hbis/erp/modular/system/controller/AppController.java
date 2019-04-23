/**
 * FileName: AppController
 * Author:   zhangb
 * Date:     2019/4/11 8:56
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.modular.system.entity.Allocation;
import cn.hbis.erp.modular.system.service.CrmResourceAllocationService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appAllocation")
public class AppController {

    @Autowired
    private CrmResourceAllocationService crmResourceAllocationService;
    public static final String GET_URL = "http://price.oltest-hbistc.com:8080/priceweb/priceSellPrice/oneSpacesPhone.htm";
    public static final String GET_URL1 = "http://price.oltest-hbistc.com:8080/priceweb/dologin.htm?account=10100050&password=123456";
//    public static final String POST_URL = "http://price.oltest-hbistc.com:8080/priceweb/priceSellPrice/oneSpacesPhone.htm";

    @ApiOperation(value = "通过品种获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "flName", value = "分类名称", dataType = "String")
    })
    @RequestMapping(value = "/selAppAllocation",method = RequestMethod.POST)
    public List<Allocation> selAppAllocation(String date, String flName){
        return this.crmResourceAllocationService.selSchedule(date,flName);
    }

    @ApiOperation(value = "通过公司获取合同进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
    })
    @RequestMapping(value = "/selScheduleByCompany",method = RequestMethod.POST)
    public List<Allocation> selScheduleByCompany(String date){
        return this.crmResourceAllocationService.selCompany(date);
    }

    @ApiOperation(value = "获取合同进度详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "公司名", dataType = "String")
    })
    @RequestMapping(value = "/selDetail",method = RequestMethod.POST)
    public List<Allocation> selDetail(String date,String companyName){
        return this.crmResourceAllocationService.selByCompany(date,companyName);
    }


    @ApiOperation(value = "测试")
    @PostMapping("httpURLConnectionPOST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "POST_URL", value = "端口地址", dataType = "String"),
            @ApiImplicitParam(name = "fkdat", value = "结算日期", dataType = "String"),
            @ApiImplicitParam(name = "companyId", value = "公司ID", dataType = "String"),
            @ApiImplicitParam(name = "attribute1", value = "材质", dataType = "String"),
            @ApiImplicitParam(name = "attribute2", value = "规格", dataType = "String"),
    })
    @Async
    public Map<String, Object> httpURLConnectionPOST(String POST_URL,String fkdat,String companyId,String attribute1,String attribute2) throws IOException {

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

        String parm = "";
        //根据地址不同，拼接查询条件字符串  //URLEncoder.encode()方法  为字符串进行编码
        if(POST_URL.equals("http://18.0.128.1:8080/priceweb/sellPrice/screwThreadDiagram.htm")){
            if(fkdat != null && fkdat != ""){
                parm = "fkdat=" + fkdat;
            }
            if(companyId != null && companyId != ""){
                parm = parm + "&" + "companyId=" + companyId;
            }
        }else if(POST_URL.equals("http://18.0.128.1:8080/priceweb/priceSellPrice/oneSpacesPhone.htm")){
            if(fkdat != null && fkdat != ""){
                parm = "fkdat=" + fkdat;
            }
            if(companyId != null && companyId != ""){
                parm = parm + "&" + "companyId=" + companyId;
            }
            if(attribute1 != null && attribute1 != ""){
                parm = parm + "&" + "attribute1=" + attribute1;
            }
            if(attribute2 != null && attribute2 != ""){
                parm = parm + "&" + "attribute2=" + attribute2;
            }
        }else if(POST_URL.equals("http://18.0.128.1:8080/priceweb/priceSellPrice/getVarietySubjectBody.htm")){
            if(fkdat != null && fkdat != ""){
                parm = "fkdat=" + fkdat;
            }
            if(companyId != null && companyId != ""){
                parm = parm + "&" + "companyId=" + companyId;
            }
            if(attribute1 != null && attribute1 != "") {
                parm = parm + "&" + "attribute1=" + attribute1;
            }
        }else if(POST_URL.equals("http://18.0.128.1:8080/priceweb/priceSellPrice/phoneSecondQueryList1.htm")){
            if(fkdat != null && fkdat != ""){
                parm = "fkdat=" + fkdat;
            }
            if(companyId != null && companyId != ""){
                parm = parm + "&" + "companyId=" + companyId;
            }
        }
        // 将参数输出到连接
        dataout.writeBytes(parm);

        // 输出完成后刷新并关闭流
        dataout.flush();
        dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

        System.out.println(connection.getResponseCode());

        // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;

        StringBuilder sb = new StringBuilder();
        // 用来存储响应数据
        Map map = new HashMap();
        // 循环读取流,若不到结尾处
        while ((line = bf.readLine()) != null) {
            sb.append(line);
            System.out.println(sb);

        }
        String bb= sb+"";
        Map<String, Object>  str_json = this.json2map(bb);
        bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
        connection.disconnect(); // 销毁连接

        map.put("sb",sb);
        return str_json;
    }

    public static Map<String, Object> json2map(String str_json) {
        Map<String, Object> res = null;

            Gson gson = new Gson();
            res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
            }.getType());

        return res;
    }

}
