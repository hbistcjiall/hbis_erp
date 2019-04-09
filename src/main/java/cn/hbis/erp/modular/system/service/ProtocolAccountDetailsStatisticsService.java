package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.ProtocolAccountDetailsStatistics;
import cn.hbis.erp.modular.system.mapper.ProtocolAccountDetailsStatisticsMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 协议户明细统计表 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ProtocolAccountDetailsStatisticsService extends ServiceImpl<ProtocolAccountDetailsStatisticsMapper, ProtocolAccountDetailsStatistics> {

    @Resource
    private ProtocolAccountDetailsStatisticsMapper protocolAccountDetailsStatisticsMapper;

    /**
     * 查询协议户明细统计列表
     *
     *
     */
    @Async
    public Page<Map<String, Object>> List(String varieties, String beginTime, String endTime, String supplyMode, List<String> companyIdList) {
        Page page = PageFactory.defaultPage();
        if (beginTime != null){
            beginTime = beginTime.replace("-","");
        }
        if (endTime != null){
            endTime = endTime.replace("-","");
        }
        System.out.println(beginTime+":"+endTime);
        return this.baseMapper.List(page, varieties, beginTime, endTime, supplyMode, companyIdList);
    }
    public List sumList(String varieties, String beginTime, String endTime, String supplyMode, List<String> companyIdList) {
        if (beginTime != null){
            beginTime = beginTime.replace("-","");
        }
        if (endTime != null){
            endTime = endTime.replace("-","");
        }
        return this.baseMapper.sumList(varieties, beginTime, endTime, supplyMode, companyIdList);
    }

    /**
     * 导出 销售总公司协议户销量明细统计列表
     *
     *
     */
    @Async
    public List<Map<String, Object>> searchList(String varieties, String beginTime, String endTime, String supplyMode, List<String> companyIdList) {
        if (beginTime != null){
            beginTime = beginTime.replace("-","");
        }
        if (endTime != null){
            endTime = endTime.replace("-","");
        }
        return this.baseMapper.List(varieties, beginTime, endTime, supplyMode, companyIdList);
    }
}
