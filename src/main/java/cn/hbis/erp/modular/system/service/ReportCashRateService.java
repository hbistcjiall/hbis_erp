package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.constant.state.EnumCompany;
import cn.hbis.erp.core.common.constant.state.EnumSummaryType;
import cn.hbis.erp.core.util.BigDecimalUtil;
import cn.hbis.erp.modular.system.entity.ReportCashRate;
import cn.hbis.erp.modular.system.entity.ReportCashRateSummary;
import cn.hbis.erp.modular.system.mapper.ReportCashRateMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户兑现率 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ReportCashRateService extends ServiceImpl<ReportCashRateMapper, ReportCashRateSummary> {

    @Resource
    private ReportCashRateMapper reportCashRateMapper;

    /**
     * @Title ReportCashRateManager.getCashRateSummary
     * @Description 客户兑现率汇总
     * @author zhangry12988
     * @time 2017-10-16 15:10
     * @param
     * @return List<ReportCashRateSummary>
     * @throws
     */
    public List<ReportCashRateSummary> getCashRateSummary(String companyId, String orderStopDateS, String orderStopDateE, String recordDate, String summaryType) {
        List<ReportCashRateSummary> list = null;
        list = reportCashRateMapper.getCashRateSummary(companyId, orderStopDateS, orderStopDateE, recordDate, summaryType);
        for (int i = 0 ; i < list.size() ;i++){
            ReportCashRateSummary rcrs = list.get(i);

            if(rcrs.getCompanyId() == null){
                rcrs.setCompanyName("集团");
            }else if (rcrs.getCompanyId() == 9580){
                rcrs.setCompanyName("唐钢");
            }else if (rcrs.getCompanyId() == 9727){
                rcrs.setCompanyName("邯钢（老区）");
            }else if (rcrs.getCompanyId() == 9193){
                rcrs.setCompanyName("宣钢");
            }else if (rcrs.getCompanyId() == 9196){
                rcrs.setCompanyName("承钢");
            }else if (rcrs.getCompanyId() == 1932){
                rcrs.setCompanyName("舞钢");
            }else if (rcrs.getCompanyId() == 9110){
                rcrs.setCompanyName("石钢");
            }else if (rcrs.getCompanyId() == 8493){
                rcrs.setCompanyName("衡板（新区）");
            }
        }
        return list;
    }
    /**
     * @Description 兑现率曲线
     */
    public List<ReportCashRateSummary> getCashRateCurve(String companyId,String recordDate, String summaryType) {
        List<ReportCashRateSummary> list = reportCashRateMapper.getCashRateCurve(companyId, recordDate, summaryType);
        List<ReportCashRateSummary> result = new ArrayList<ReportCashRateSummary>();
        if(!list.isEmpty()) {
            for(ReportCashRateSummary summary :list) {
                summary.setCompanyName(EnumCompany.getByCode(String.valueOf(summary.getCompanyId())));
                summary.setCashRateJan(BigDecimalUtil.keppPoint(summary.getCashRateJan().multiply(new BigDecimal(100)), 2));
                summary.setCashRateFeb(BigDecimalUtil.keppPoint(summary.getCashRateFeb().multiply(new BigDecimal(100)), 2));
                summary.setCashRateMar(BigDecimalUtil.keppPoint(summary.getCashRateMar().multiply(new BigDecimal(100)), 2));
                summary.setCashRateApr(BigDecimalUtil.keppPoint(summary.getCashRateApr().multiply(new BigDecimal(100)), 2));
                summary.setCashRateMay(BigDecimalUtil.keppPoint(summary.getCashRateMay().multiply(new BigDecimal(100)), 2));
                summary.setCashRateJun(BigDecimalUtil.keppPoint(summary.getCashRateJun().multiply(new BigDecimal(100)), 2));
                summary.setCashRateJul(BigDecimalUtil.keppPoint(summary.getCashRateJul().multiply(new BigDecimal(100)), 2));
                summary.setCashRateAug(BigDecimalUtil.keppPoint(summary.getCashRateAug().multiply(new BigDecimal(100)), 2));
                summary.setCashRateSept(BigDecimalUtil.keppPoint(summary.getCashRateSept().multiply(new BigDecimal(100)), 2));
                summary.setCashRateOct(BigDecimalUtil.keppPoint(summary.getCashRateOct().multiply(new BigDecimal(100)), 2));
                summary.setCashRateNov(BigDecimalUtil.keppPoint(summary.getCashRateNov().multiply(new BigDecimal(100)), 2));
                summary.setCashRateDec(BigDecimalUtil.keppPoint(summary.getCashRateDec().multiply(new BigDecimal(100)), 2));
                result.add(summary);
            }
        }
        //设置需要显示的子公司
        return result;
    }
    /**
     * @Title ReportCashRateManager.getCashRateSummaryGrade
     * @Description 客户兑现率汇总-产品等级
     * @author wangsf
     * @time 2019-01-23 09:50
     * @param
     * @return List<ReportCashRateSummary>
     * @throws
     */
    public List<ReportCashRateSummary> getCashRateSummaryGrade(String companyId, String orderStopDateS, String orderStopDateE, String recordDate, String summaryType) {
        return reportCashRateMapper.getCashRateSummaryGrade(companyId, orderStopDateS, orderStopDateE, recordDate, summaryType);
    }
    /**
     * @Title ReportCashRateManager.getCashRateDetai
     * @Description 客户兑现率明细
     * @author zhangry12988
     * @time 2017-09-19 14:56
     * @param
     * @return List<ReportCustomerCashRate>
     * @throws
     */
    public List<Map> getCashRateDetail(String companyId, String orderStopDateS, String orderStopDateE, String recordDate, String summaryType) {
        return reportCashRateMapper.getCashRateDetail(companyId, orderStopDateS, orderStopDateE, recordDate, summaryType);
    }
    /**
     * @Title ReportCashRateManager.timeTask
     * @Description 每个月1-10日凌晨12点执行定时任务，将客户兑现率明细信息保存到数据库中
     * @author zhangry12988
     * @time 2017-09-19 11:18
     * @return String
     * @throws
     */
    public String timeTaskDetail() {
        String msg="兑现率明细处理成功！";
        try {
            //log.info("<---------------定时任务启动,开始处理兑现率明细--------------->");
            //log.info("<---------------兑现率明细不去0统计--------------->");
            reportCashRateMapper.insert(EnumSummaryType.WITH_ZERO.getCode());
            Thread.sleep(15000);
            //log.info("<---------------兑现率明细去0统计--------------->");
            reportCashRateMapper.insert(EnumSummaryType.WITHOUT_ZERO.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            msg = "兑现率明细处理失败！";
        }
        //log.info("<---结果："+msg+"--->");
        return msg;
    }
    public String timeTaskSummary() {
        String msg="兑现率汇总处理成功！";
        try {
            //log.info("<---------------定时任务启动,开始处理兑现率汇总--------------->");
            //log.info("<---------------兑现率汇总不去0统计--------------->");
            reportCashRateMapper.insertSummary(EnumSummaryType.WITH_ZERO.getCode());
            Thread.sleep(10000);
            //log.info("<---------------兑现率汇总去0统计--------------->");
            reportCashRateMapper.insertSummary(EnumSummaryType.WITHOUT_ZERO.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            msg = "兑现率汇总处理失败！";
        }
        //log.info("<---结果："+msg+"--->");
        return msg;
    }
}
