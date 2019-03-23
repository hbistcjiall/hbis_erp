package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.Allocation;
import cn.hbis.erp.modular.system.entity.CrmResourceAllocation;
import cn.hbis.erp.modular.system.mapper.CrmResourceAllocationMapper;
import cn.hbis.erp.modular.system.warpper.CrmResourceAllocationWrapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    public List<Allocation> selSchedule(String date,String flName){
        String month = "";
        String year = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        if (ToolUtil.isEmpty(date)){
            date = df.format(new Date()).toString();
        }
        month = date.substring(5,7);
        year = date.substring(0,4);
        return crmResourceAllocationMapper.selSchedule(date,month,year,flName);
    }

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
            sort = "DESC";
        }else {
            sort = "ASC";
        }
        return crmResourceAllocationMapper.selScheduleByCx(date,month,year,sort,flName);
    }
}
