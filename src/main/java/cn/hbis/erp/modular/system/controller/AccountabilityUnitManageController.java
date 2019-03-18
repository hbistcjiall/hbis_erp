package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.AccountabilityUnitManage;
import cn.hbis.erp.modular.system.service.AccountabilityUnitManageService;
import cn.hbis.erp.modular.system.warpper.AccountabilityUnitManageWrapper;
import cn.hbis.erp.modular.system.warpper.ProtocolAccountDetailsWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
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
                    @ApiImplicitParam(name = "code" ,value = "责任公司编码",dataType ="String" ),
                    @ApiImplicitParam(name = "id" ,value = "责任公司Id",dataType ="String" )
            })
            public Map addORUpdate(String companyname,String code,String id){
                Map map = new HashMap();
                boolean flag = accManageService.AddORUpdate(id,code,companyname);
                if(flag){
                    map.put("massage","添加或修改成功");
                }else{
                    map.put("massage","添加或修改失败");
                }
                return map;
            }
            @ApiOperation(value = "责任公司逻辑删除")
            @PostMapping("/delmanager")
            @ApiImplicitParams({
                    @ApiImplicitParam(name = "id" ,value = "责任公司Id",dataType ="String" )
            })
            public Map delete(String id){
                Map map = new HashMap();
                boolean flag = accManageService.delete(id);
                if(flag){
                    map.put("massage","删除成功");
                }else{
                    map.put("massage","删除失败");
                }
                return map;
            }
                @ApiOperation(value = "责任公司更新显示")
                @PostMapping("/selectOne")
                @ApiImplicitParams({
                        @ApiImplicitParam(name = "id" ,value = "责任公司Id",dataType ="String" )
                })
                public Map GetOne(String id){
                Map map = new HashMap();
                AccountabilityUnitManage am= accManageService.getOne(id);

                map.put("massage",am);

                        return map;
                }
}
