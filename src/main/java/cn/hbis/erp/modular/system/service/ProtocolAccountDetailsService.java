package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.ProtocolAccountDetails;
import cn.hbis.erp.modular.system.mapper.ProtocolAccountDetailsMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 协议户明细表 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ProtocolAccountDetailsService extends ServiceImpl<ProtocolAccountDetailsMapper, ProtocolAccountDetails> {

    @Resource
    private ProtocolAccountDetailsMapper protocolAccountDetailsMapper;

    /**
     * 查询协议户明细列表
     *
     *
     */
    public Page<Map<String, Object>> searchList(String mainSalesRegional, String beginTime, String endTime, String protocolYear, String steelMills) {
        Page page = PageFactory.defaultPage();
        return this.baseMapper.searchProtocolAccountDetailList(page, mainSalesRegional, beginTime, endTime, protocolYear, steelMills);
    }
}

