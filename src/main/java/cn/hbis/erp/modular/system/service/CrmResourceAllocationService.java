package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.Allocation;
import cn.hbis.erp.modular.system.entity.CrmResourceAllocation;
import cn.hbis.erp.modular.system.mapper.CrmResourceAllocationMapper;
import cn.hbis.erp.modular.system.warpper.CrmResourceAllocationWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 资源分配维护表 服务实现类
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-21
 */
@Service
public class CrmResourceAllocationService extends ServiceImpl<CrmResourceAllocationMapper, CrmResourceAllocation> implements CrmResourceAllocationWrapper {
    @Resource
    private CrmResourceAllocationMapper crmResourceAllocationMapper;
    /**
     * 查询合同进度（品种）
     * @param date
     * @return
     */
    @Async
    public List<Allocation> selSchedule(String date,String flName){
        String month = "";
        String year = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(5,7);
        year = date.substring(0,4);
        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selSchedule(date,month,year,flName);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setYield(model.getYield());
            }
            if (model.getPlanNum()!=null && model.getYield()!=null){
                allocation.setSchedule(new BigDecimal((float)model.getYield()/model.getPlanNum()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            allocation.setPzName(model.getPzName());
            lists.add(allocation);
        }
        return lists;
    }
    /**
     * 查询合同进度（产线）
     * @param date
     * @return
     */
    @Async
    public List<Allocation> selScheduleByCx(String date,String sort,String flName){
        String month = "";
        String year = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(5,7);
        year = date.substring(0,4);
        if (sort.equals("0")){
            sort = "ASC";
        }else {
            sort = "DESC";
        }
        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selScheduleByCx(date,month,year,sort,flName);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setYield(model.getYield());
            }
            allocation.setSchedule(model.getSchedule());
            allocation.setCxName(model.getCxName());
            lists.add(allocation);
        }
        return lists;
    }

    /**
     * 查询合同进度（公司）
     * @param date
     * @return
     */
    @Async
    public List<Allocation> selCompany(String date){
        String month = "";
        String year = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(5,7);
        year = date.substring(0,4);

        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selCompany(date,month,year);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setYield(model.getYield());
            }
            if (model.getPlanNum()!=null && model.getYield()!=null){
                allocation.setSchedule(new BigDecimal((float)model.getYield()/model.getPlanNum()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            allocation.setCompanyName(model.getCompanyName());
            lists.add(allocation);
        }
        return lists;
    }
    /**
     * 查询合同进度详细（公司）
     * @param date
     * @return
     */
    @Async
    public List<Allocation> selByCompany(String date,String companyName){
        String month = "";
        String year = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(5,7);
        year = date.substring(0,4);

        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selByCompany(date,month,year,companyName);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0);
                allocation.setSchedule(0.00);
            }else {
                allocation.setYield(model.getYield());
            }
            if (model.getPlanNum()!=null && model.getYield()!=null){
                allocation.setSchedule(new BigDecimal((float)model.getYield()/model.getPlanNum()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            allocation.setFlName(model.getFlName());
            lists.add(allocation);
        }
        return lists;
    }
}
