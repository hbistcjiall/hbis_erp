/**
 * FileName: AppController
 * Author:   zhangb
 * Date:     2019/4/11 8:56
 */
package cn.hbis.erp.modular.system.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/appweixinocation")
public class AppWeiXinController {



    @Async
    public String httpWeiXinURLConnectionPOST(String appid,String REDIRECT_URI) throws IOException {

        String url1 = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid="+appid+"" +
                "&redirect_uri="+REDIRECT_URI+"" +
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&state=STATE" +
                "#wechat_redirect";

        URL url = new URL(url1);

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


        //根据地址不同，拼接查询条件字符串  //URLEncoder.encode()方法  为字符串进行编码



        // 输出完成后刷新并关闭流
        dataout.flush();
        dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

        System.out.println(connection.getResponseCode());

        // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String b = (String) bf.readLine();
//        String line;
//
//        StringBuilder sb = new StringBuilder();
        // 用来存储响应数据
//        Map map = new HashMap();
//        // 循环读取流,若不到结尾处
//        while ((line = bf.readLine()) != null) {
//            sb.append(line);
//            System.out.println(sb);
//
//        }
//        String bb= sb+"";
//        Map<String, Object>  str_json = this.json2map(bb);
        bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
        connection.disconnect(); // 销毁连接

//        map.put("sb",sb);
        return b;
    }

//    public static Map<String, Object> json2map(String str_json) {
//        Map<String, Object> res = null;
//
//        Gson gson = new Gson();
//        res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
//        }.getType());
//
//        return res;
//    }

    @ApiOperation(value = "测试02")
    @PostMapping("httpWeiXinURLConnectionPOST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "REDIRECT_URI", value = "端口地址", dataType = "String"),
            @ApiImplicitParam(name = "appid", value = "微信端ID", dataType = "String"),

    })

    @Async
    public String httpWeiXinURLConnectionPOSTTwo(String appid,String REDIRECT_URI) throws IOException {

        String  CODE=this.httpWeiXinURLConnectionPOST(appid,REDIRECT_URI);

        String  AppId="";
        String  AppSecret="";
        String url2 = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid="+AppId+"" +
                "&secret="+AppSecret+"" +
                "&code="+CODE+"" +
                "&grant_type=authorization_code";

        URL url = new URL(url2);

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


        //根据地址不同，拼接查询条件字符串  //URLEncoder.encode()方法  为字符串进行编码



//        // 将参数输出到连接
//        dataout.writeBytes(url2);

        // 输出完成后刷新并关闭流
        dataout.flush();
        dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

        System.out.println(connection.getResponseCode());

        // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String b = (String) bf.readLine();
//        String line;

//        StringBuilder sb = new StringBuilder();
//        // 用来存储响应数据
//        Map map = new HashMap();
//        // 循环读取流,若不到结尾处
//        while ((line = bf.readLine()) != null) {
//            sb.append(line);
//            System.out.println(sb);
//
//        }
//        String bb= sb+"";
//        Map<String, Object>  str_json = this.json2map(bb);
        bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
        connection.disconnect(); // 销毁连接

//        map.put("sb",sb);
        return b;
    }




}
