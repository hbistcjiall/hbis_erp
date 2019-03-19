package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.LoginLog;
import cn.hbis.erp.modular.system.mapper.LoginLogMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class LoginLogService extends ServiceImpl<LoginLogMapper, LoginLog> {

    /**
     * 获取登录日志列表
     *
     *
     */
    public List<Map<String, Object>> getLoginLogs(Page page, String beginTime, String endTime, String logName) {
        if(ToolUtil.isNotEmpty(beginTime) || ToolUtil.isNotEmpty(endTime)){
            beginTime = beginTime + " 00:00:00";
            endTime = endTime + " 23:59:59";
        }
        return this.baseMapper.getLoginLogs(page, beginTime, endTime, logName);
    }
}
