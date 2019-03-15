
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.annotion.Permission;
import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.constant.state.BizLogType;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.OperationLog;
import cn.hbis.erp.modular.system.service.OperationLogService;
import cn.hbis.erp.modular.system.warpper.LogWrapper;
import cn.hutool.core.bean.BeanUtil;
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
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 查询操作日志列表
     *
     *
     */
    @ApiOperation(value = "查询操作日志列表")
    @PostMapping("list")
    @Permission(Const.ADMIN_NAME)
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) String logName,
                       @RequestParam(required = false) Integer logType,
                       @RequestParam String limit,
                       @RequestParam String page) {

        //获取分页参数
        Page page1 = PageFactory.defaultPage();

        //根据条件查询操作日志
        List<Map<String, Object>> result = operationLogService.getOperationLogs(page1, beginTime, endTime, logName, BizLogType.valueOf(logType));

        page1.setRecords(new LogWrapper(result).wrap());

        return PageFactory.createPageInfo(page1);
    }

    /**
     * 查询操作日志详情
     *
     *
     */
    @ApiOperation(value = "查询操作日志详情")
    @PostMapping("detail")
    @Permission(Const.ADMIN_NAME)
    public Object detail(@RequestParam String id) {
        OperationLog operationLog = operationLogService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(operationLog);
        return super.warpObject(new LogWrapper(stringObjectMap));
    }

    /**
     * 清空日志
     *
     *
     */
    @ApiOperation(value = "清空日志")
    @BussinessLog(value = "清空业务日志")
    @PostMapping("delLog")
    @Permission(Const.ADMIN_NAME)
    public Object delLog() {
        SqlRunner.db().delete("delete from sys_operation_log");
        return SUCCESS_TIP;
    }
}
