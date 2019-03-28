package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.ProtocolNameContractUnit;
import cn.hbis.erp.modular.system.mapper.ProtocolNameContractUnitMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 协议户名与合同单位对照表列表 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ProtocolNameContractUnitService extends ServiceImpl<ProtocolNameContractUnitMapper, ProtocolNameContractUnit> {

    @Resource
    private ProtocolNameContractUnitMapper protocolNameContractUnitMapper;

    /**
     * 协议户名与合同单位对照表列表
     *
     *
     */
    public Page<Map<String, Object>> List(String accontName, String contractUnit) {
        Page page = PageFactory.defaultPage();
        return this.baseMapper.List(page, accontName, contractUnit);
    }
    /**
     * 添加协议户名与合同单位对照
     *
     *
     */
    public boolean insert(String accontName, String contractUnit, String note) {
        ProtocolNameContractUnit pncu = new ProtocolNameContractUnit();
        pncu.setAccontName(accontName);
        pncu.setContractUnit(contractUnit);
        pncu.setNote(note);
        pncu.setDeleteState("0");
        int a = protocolNameContractUnitMapper.insert(pncu);
        boolean state = false;
        if (a == 1){
            state = true;
        } else {
            state = false;
        }
        return state;
    }
    /**
     * 修改协议户名与合同单位对照
     *
     *
     */
    public boolean update(String nameContractUnitId, String accontName, String contractUnit, String note) {
        ProtocolNameContractUnit pncu = protocolNameContractUnitMapper.selectById(nameContractUnitId);
        pncu.setAccontName(accontName);
        pncu.setContractUnit(contractUnit);
        pncu.setNote(note);
        int a = protocolNameContractUnitMapper.updateById(pncu);
        boolean state = false;
        if (a == 1){
            state = true;
        } else {
            state = false;
        }
        return state;
    }
    /**
     * 删除协议户名与合同单位对照
     *
     *
     */
    public boolean delete(String nameContractUnitId) {
        ProtocolNameContractUnit pncu = protocolNameContractUnitMapper.selectById(nameContractUnitId);
        pncu.setDeleteState("1");
        int a = protocolNameContractUnitMapper.updateById(pncu);
        boolean state = false;
        if (a == 1){
            state = true;
        } else {
            state = false;
        }
        return state;
    }
}
