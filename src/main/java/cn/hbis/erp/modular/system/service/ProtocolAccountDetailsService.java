package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.ProtocolAccountDetails;
import cn.hbis.erp.modular.system.mapper.ProtocolAccountDetailsMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
    @Async
    public Page<Map<String, Object>> searchList(String varieties, String beginTime, String endTime, String protocolYear, String steelMills) {
        Page page = PageFactory.defaultPage();
        return this.baseMapper.searchProtocolAccountDetailList(page, varieties, beginTime, endTime, protocolYear, steelMills);
    }

    /**
     * 查询协议户明细列表（不分页）
     *
     *
     */
    @Async
    public List<Map<String, Object>> searchDetailList(String varieties, String beginTime, String endTime, String protocolYear, String steelMills) {
        return this.baseMapper.searchProtocolAccountDetailList(varieties, beginTime, endTime, protocolYear, steelMills);
    }
    /**
     * 修改协议户明细
     *
     *
     */
    public boolean update(String protocolAccountId, String protocolYear, String accountName, String varieties, String mainSalesRegional, String aidedSalesRegionalOne, String aidedSalesRegionalTwo, String steelMills, String annualAgreementVolume){
        boolean flag =false;
        ProtocolAccountDetails protocolAccountDetails = protocolAccountDetailsMapper.selectById(protocolAccountId);
        protocolAccountDetails.setProtocolYear(protocolYear);
        protocolAccountDetails.setAccountName(accountName);
        protocolAccountDetails.setVarieties(varieties);
        protocolAccountDetails.setMainSalesRegional(mainSalesRegional);
        protocolAccountDetails.setAidedSalesRegionalOne(aidedSalesRegionalOne);
        protocolAccountDetails.setAidedSalesRegionalTwo(aidedSalesRegionalTwo);
        protocolAccountDetails.setSteelMills(steelMills);
        protocolAccountDetails.setAnnualAgreementVolume(annualAgreementVolume);
        int num = protocolAccountDetailsMapper.updateById(protocolAccountDetails);
        if(num == 1){
            flag = true;
            return flag;
        }else{
            return flag;
        }
    }
    /**
     * 协议户明细删除
     *
     *
     */
    public  boolean delete(String protocolAccountId){
        boolean flag = false;
        ProtocolAccountDetails protocolAccountDetails = protocolAccountDetailsMapper.selectById(protocolAccountId);
        protocolAccountDetails.setDeleteStatus("1");
        int num = protocolAccountDetailsMapper.updateById(protocolAccountDetails);
        if(num ==1){
            flag=true;
            return  flag;
        }
        return flag;
    }
    /**
     * 协议户明细批量删除
     *
     *
     */
    public  boolean deleteList(List list){
        boolean flag = false;
        for(int i = 0 ; i < list.size(); i++) {
            String id = list.get(i).toString();
            ProtocolAccountDetails protocolAccountDetails = protocolAccountDetailsMapper.selectById(id);
            protocolAccountDetails.setDeleteStatus("1");
            int num = protocolAccountDetailsMapper.updateById(protocolAccountDetails);
            if(num ==1){
                flag = true;
            }
        }
        return flag;
    }
}

