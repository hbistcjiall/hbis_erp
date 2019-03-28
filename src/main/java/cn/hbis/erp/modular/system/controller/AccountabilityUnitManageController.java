package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.service.AccountabilityUnitManageService;
import cn.hbis.erp.modular.system.warpper.AccountabilityUnitManageWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/acctabilityunit")
public class AccountabilityUnitManageController extends BaseController {

            @Autowired
            private AccountabilityUnitManageService accManageService;

            @ApiOperation(value = "获取责任公司列表")
            @PostMapping("/selaccountmanager")
            @ApiImplicitParams({
                    @ApiImplicitParam(name = "companyname" ,value = "责任公司名称",dataType ="String" ),
                    @ApiImplicitParam(name = "limit" ,value = "每页多少条数据",dataType ="String" ),
                    @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
            })
            public Object selaccountmanager(String companyname,String limit, String page){
                Page<Map<String, Object>> objcet = accManageService.selectAccountManeg(companyname);
                Page wrapped = new AccountabilityUnitManageWrapper(objcet).wrap();
                return PageFactory.createPageInfo(wrapped);
            }
            @ApiOperation(value = "责任公司添加和更新")
            @PostMapping("/addorupdatemanager")
            @ApiImplicitParams({
                    @ApiImplicitParam(name = "companyname" ,value = "责任公司名称",dataType ="String" ),
                    @ApiImplicitParam(name = "id" ,value = "责任公司Id",dataType ="String" )
            })
            public ResponseData addORUpdate(String companyname, String id){
                boolean flag = accManageService.AddORUpdate(id,companyname);
                return SUCCESS_TIP;
            }
            @ApiOperation(value = "责任公司逻辑删除")
            @PostMapping("/delmanager")
            @ApiImplicitParams({
                    @ApiImplicitParam(name = "id" ,value = "责任公司Id",dataType ="String" )
            })
            public ResponseData delete(String id){
                boolean flag = accManageService.delete(id);
                return SUCCESS_TIP;
            }
}
