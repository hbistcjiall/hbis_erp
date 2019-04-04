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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(4,6);
        year = date.substring(0,4);

        if(ToolUtil.isNotEmpty(flName)){
            if (flName.equals("3")){
                flName =  "'专业公司','分公司'";
            }else if (flName.equals("4")){
                flName =  "'事业部','技术中心','子公司','自办公司'";
            }else if (flName.equals("2")){
                flName =  "'出口'";
            }

        }
        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selSchedule(date,month,year,flName);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0.00);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0.00);
                allocation.setSchedule(0.00);
            }else {
                allocation.setYield(model.getYield());
            }
            allocation.setSchedule(model.getSchedule());
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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(4,6);
        year = date.substring(0,4);
        if (sort.equals("0")){
            sort = "DESC";
        }else {
            sort = "ASC";
        }
        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selScheduleByCx(date,month,year,sort,flName);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0.00);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0.00);
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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(4,6);
        year = date.substring(0,4);

        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selCompany(date,month,year);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0.00);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0.00);
                allocation.setSchedule(0.00);
            }else {
                allocation.setYield(model.getYield());
            }
            allocation.setSchedule(model.getSchedule());
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
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(4,6);
        year = date.substring(0,4);

        if(ToolUtil.isNotEmpty(companyName)){
            if (companyName.equals("唐钢")){
                companyName = "9580";
            }else if (companyName.equals("邯钢")){
                companyName = "9727";
            }else if (companyName.equals("宣钢")){
                companyName = "9193";
            }else if (companyName.equals("承钢")){
                companyName = "9196";
            }else if (companyName.equals("舞钢")){
                companyName = "1932";
            }
        }
        List<Allocation> lists = new ArrayList<>();
        List<Allocation> list = crmResourceAllocationMapper.selByCompany(date,month,year,companyName);
        Allocation allocation = null;
        for (Allocation model : list){
            allocation = new Allocation();
            if (model.getPlanNum()==null){
                allocation.setPlanNum(0.00);
                allocation.setSchedule(0.00);
            }else {
                allocation.setPlanNum(model.getPlanNum());
            }
            if (model.getYield()==null){
                allocation.setYield(0.00);
                allocation.setSchedule(0.00);
            }else {
                allocation.setYield(model.getYield());
            }
            allocation.setSchedule(model.getSchedule());
            allocation.setFlName(model.getFlName());
            lists.add(allocation);
        }
        return lists;
    }
}
