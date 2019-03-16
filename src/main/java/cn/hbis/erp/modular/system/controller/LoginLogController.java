
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.annotion.Permission;
import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.service.LoginLogService;
import cn.hbis.erp.modular.system.warpper.LogWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 *
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 查询登录日志列表
     *
     *
     */
    @ApiOperation(value = "查询登录日志列表")
    @PostMapping("list")
    @Permission(Const.ADMIN_NAME)
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) String logName,
                       @RequestParam String limit,
                       @RequestParam String page) {

        //获取分页参数
        Page pageG = PageFactory.defaultPage();

        //根据条件查询日志
        List<Map<String, Object>> result = loginLogService.getLoginLogs(pageG, beginTime, endTime, logName);
        pageG.setRecords(new LogWrapper(result).wrap());

        return PageFactory.createPageInfo(pageG);
    }

    /**
     * 清空日志
     *
     *
     */
    @BussinessLog("清空登录日志")
    @PostMapping("delLoginLog")
    @Permission(Const.ADMIN_NAME)
    public Object delLog() {
        SqlRunner.db().delete("delete from sys_login_log");
        return SUCCESS_TIP;
    }
}
