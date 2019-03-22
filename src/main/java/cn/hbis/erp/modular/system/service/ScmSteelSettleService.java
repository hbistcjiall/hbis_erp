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
    public List<ScmSteelSettle> getcx(String dw,String cx,String startTime,String endTime) {
        return scmSteelSettleMapper.getcx(dw,cx,startTime,endTime);
    }
    /*
    月度品种报表
     */
    public List<ScmSteelSettle> getpz(String pz,String startTime,String endTime) {
        return scmSteelSettleMapper.getpz(pz,startTime,endTime);
    }


    /*
 报表责任部门
*/
    public List<ScmSteelSettle> getzrbm(String zrbm,String startTime,String endTime) {
        return scmSteelSettleMapper.getzrbm(zrbm,startTime,endTime);
    }

    /*
结算量报表
*/
    public List<ScmSteelSettle> getpzjszl(String startTime,String endTime) {
        return scmSteelSettleMapper.getpzjszl(startTime,endTime);
    }

    /*
品种钢结算量报表
*/
    public List<ScmSteelSettle> getpzgjswc(String startTime,String endTime) {
        return scmSteelSettleMapper.getpzgjswc(startTime,endTime);
    }


    /*
子公司结算量报表
*/
    public List<ScmSteelSettle> getzgsjswc(String startTime,String endTime) {
        return scmSteelSettleMapper.getzgsjswc(startTime,endTime);
    }


}
