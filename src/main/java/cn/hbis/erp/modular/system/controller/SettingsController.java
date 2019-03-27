package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.service.SettingService;
import cn.hbis.erp.modular.system.warpper.SettingsWrapper;
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
@RequestMapping("/settings")
public class SettingsController extends BaseController {
    @Autowired
    private SettingService settingService;
    @ApiOperation(value = "个人设置列表")
    @PostMapping("/selsettings")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuname" ,value = "菜单名称(汉字)",dataType ="String" ),
            @ApiImplicitParam(name = "limit" ,value = "每页多少条数据",dataType ="String" ),
            @ApiImplicitParam(name = "page" ,value = "第几页",dataType ="String" )
    })
    public Object selSettings(String menuname,String limit,String page){
        Page<Map<String, Object>> objcet = settingService.selSetting(menuname);
        Page wrapped = new SettingsWrapper(objcet).wrap();
        return PageFactory.createPageInfo(wrapped);
    }
    @ApiOperation(value = "添加个人设置")
    @PostMapping("/addorupdate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuid" ,value = "菜单名称",dataType ="String" ),
            @ApiImplicitParam(name = "id" ,value = "设置id",dataType ="String" )

    })
    public Map addOrUpdate(String menuid,String id){
        boolean flag =false;
        Map map = new HashMap();
        flag = settingService.AdddORupdate(id,menuid);
        if(flag){
            map.put("massage","添加或更新成功");
            return map;
        }
       map.put("","添加或更新失败");
        return map;
    }
    @ApiOperation(value = "删除个人设置")
    @PostMapping("/delsettings")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" ,value = "设置id",dataType ="String" )

    })
    public Map del(String id){
        boolean flag =false;
        Map map = new HashMap();
        flag = settingService.delSetting(id);
        if(flag){
            map.put("massage","添加或更新成功");
            return map;
        }
        map.put("massage","添加或更新失败");
        return map;
    }
}
