package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.OperationLog;
import cn.hbis.erp.modular.system.mapper.OperationLogMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class OperationLogService extends ServiceImpl<OperationLogMapper, OperationLog> {

    /**
     * 获取操作日志列表
     *
     *
     */
    public List<Map<String, Object>> getOperationLogs(Page page, String beginTime, String endTime, String logName, String s) {
        return this.baseMapper.getOperationLogs(page, beginTime, endTime, logName, s);
    }

}
