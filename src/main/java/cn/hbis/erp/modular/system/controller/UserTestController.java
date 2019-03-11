package cn.hbis.erp.modular.system.controller;


import cn.hbis.erp.modular.system.entity.UserTest;
import cn.hbis.erp.modular.system.service.UserTestService;
import cn.hbis.erp.modular.system.warpper.UserTestWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-11
 */

@RestController
@RequestMapping("/")
@Api(value = "UserTest",description = "测试user")
public class UserTestController {
    @Autowired
   private UserTestWrapper utw;
    @ApiOperation(value = "用户测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username" ,value = "登录名",dataType ="String" ,required = true),
    })
    @GetMapping("ttt")
    public List<UserTest> test(String username){
        List<UserTest> ut=utw.list(new QueryWrapper<UserTest>().eq("USERNAME",username));
        return ut;
    }
}

