package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import cn.hbis.erp.modular.system.mapper.ScmSteelSettleMapper;
import cn.hbis.erp.modular.system.warpper.ScmSteelSettleWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    @Async
    public List<ScmSteelSettle> getcx(String dw,List<String> cx,String startTime,String endTime,String startagainTime,String endagainTime) {
        return scmSteelSettleMapper.getcx(dw,cx,startTime,endTime,startagainTime,endagainTime);
    }
    /*
    月度品种报表
     */
    @Async
    public List<ScmSteelSettle> getpz(String pz,String startTime,String endTime,String startagainTime,String endagainTime) {
        return scmSteelSettleMapper.getpz(pz,startTime,endTime,startagainTime,endagainTime);
    }


    /*
 报表责任部门
*/
    @Async
    public List<ScmSteelSettle> getzrbm(String zrbm,String startTime,String endTime) {
        return scmSteelSettleMapper.getzrbm(zrbm,startTime,endTime);
    }
    /*
结算量报表
*/
    @Async
    public List<ScmSteelSettle> getpzjszl(String startTime,String endTime) {
        return scmSteelSettleMapper.getpzjszl(startTime,endTime);
    }

    /*
品种钢结算量报表
*/
    @Async
    public List<ScmSteelSettle> getpzgjswc(String startTime,String endTime) {
        return scmSteelSettleMapper.getpzgjswc(startTime,endTime);
    }


    /*
子公司结算量报表
*/
    @Async
    public List<ScmSteelSettle> getzgsjswc(String startTime,String endTime) {
        return scmSteelSettleMapper.getzgsjswc(startTime,endTime);
    }


    /*
资源计划报表
*/
    @Async
    public List<ScmSteelSettle> getzyjh(String nf,String yf,String pz,List<String> cx,String xszt) {
        return scmSteelSettleMapper.getzyjh(nf,yf,pz,cx,xszt);
    }

    /*
品种查询条件
*/
    @Async
    public List<ScmSteelSettle> getzyjhcxtjpz() {
        return scmSteelSettleMapper.getzyjhcxtjpz();
    }

    /*
产线查询条件
*/
    @Async
    public List<ScmSteelSettle> getzyjhcxtjcx(String pz) {
        return scmSteelSettleMapper.getzyjhcxtjcx(pz);
    }

    /*
销售计划 查询条件
*/
    @Async
    public List<ScmSteelSettle> getzyjhcxtjxszt() {
        return scmSteelSettleMapper.getzyjhcxtjxszt();
    }


    /*
产险合同进度报表1
*/
    @Async
    public List<ScmSteelSettle> getcxhtjd(String startTime,String endTime,List<String> cxName,String companyId) {
        String month = "";
        String year = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        startTime = startTime.replaceAll("[[\\s-:punct:]]","").substring(0,6);
        month = startTime.substring(4,6);
        year = startTime.substring(0,4);
        return scmSteelSettleMapper.getcxhtjd(startTime,endTime,year,cxName,companyId,month);
    }

    /*
品种合同进度报表1
*/
    @Async
    public List<ScmSteelSettle> getpzhtjd(String startTime,String endTime,String pzName) {
        String month = "";
        String year = "";
        startTime = startTime.replaceAll("[[\\s-:punct:]]","").substring(0,6);
        month = startTime.substring(4,6);
        year = startTime.substring(0,4);
        return scmSteelSettleMapper.getpzhtjd(startTime,endTime,year,pzName,month);
    }




}
