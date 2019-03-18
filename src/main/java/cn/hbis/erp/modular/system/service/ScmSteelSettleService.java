package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import cn.hbis.erp.modular.system.mapper.ScmSteelSettleMapper;
import cn.hbis.erp.modular.system.warpper.ScmSteelSettleWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * <p>
 * 结算清单信息接口 服务实现类
 * </p>
 *
 * @author yaojiaqi
 * @since 2019-03-16
 */

@Service
public class ScmSteelSettleService extends ServiceImpl<ScmSteelSettleMapper, ScmSteelSettle> implements ScmSteelSettleWrapper {
    @Resource
    private ScmSteelSettleMapper scmSteelSettleMapper;

    /*
     月度产线报表
     */
    public List<ScmSteelSettle> getcx(String dw,String cx) {
        return scmSteelSettleMapper.getcx(dw,cx);
    }
    /*
    月度产线报表
     */
    public List<ScmSteelSettle> getpz(String pz) {
        return scmSteelSettleMapper.getpz(pz);
    }
    /*
    年度度产线报表
     */
    public List<ScmSteelSettle> getndcx(String dw,String cx) {
        return scmSteelSettleMapper.getcx(dw,cx);
    }
    /*
    年度度品种报表
    */
    public List<ScmSteelSettle> getndpz(String pz) {
        return scmSteelSettleMapper.getpz(pz);
    }

    /*
    报表责任部门
 */
    public List<ScmSteelSettle> getzebm(String zrbm) {
        return scmSteelSettleMapper.getzebm(zrbm);
    }


}
