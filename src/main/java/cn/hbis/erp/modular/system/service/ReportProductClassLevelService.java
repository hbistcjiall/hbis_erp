package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.ReportProductClassLevel;
import cn.hbis.erp.modular.system.mapper.ReportProductClassLevelMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportProductClassLevelService extends ServiceImpl<ReportProductClassLevelMapper,ReportProductClassLevel> {
    @Resource
    private ReportProductClassLevelMapper reportProductClassLevelMapper;

    @Async
    public List<Map> getcxfb(List<String> cx, String startTime,String endTime,String zl){
        return reportProductClassLevelMapper.getcxfb(cx,startTime,endTime,zl);
    }
    @Async
    public List<Map> getcxzl(List<String> cx, String startTime,String endTime,String zl){
        return reportProductClassLevelMapper.getcxzl(cx,startTime,endTime,zl);
    }


}
