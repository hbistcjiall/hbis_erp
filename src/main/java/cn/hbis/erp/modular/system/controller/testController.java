/**
 * FileName: testController
 * Author:   zhangb
 * Date:     2019/3/27 17:34
 */
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.modular.system.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {
    @Autowired
    private TestService testService;

    @ApiOperation(value = "异步测试")
    @GetMapping("/async")
    public String async(){
        System.out.println("####IndexController####   1");
        testService.sendSms();
        System.out.println("####IndexController####   4");
        return "success";
    }
}
