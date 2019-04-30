package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.hbis.erp.core.shiro.ShiroUser;
import cn.hbis.erp.modular.system.entity.ScmFilter;
import cn.hbis.erp.modular.system.mapper.ScmFilterMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiarsi
 * @since 2019-04-18
 */
@Service
public class ScmFilterService extends ServiceImpl<ScmFilterMapper, ScmFilter>{

    @Resource
    private ScmFilterMapper scmFilterMapper;

    public List getColumnName(String tableName, String columnName){
        return scmFilterMapper.getColumnName(tableName,columnName);
    }

    public List getColumnValue(String tableName,String columnName,String columnValue){
        List list = new ArrayList();
        if (tableName.equals("SCM_SALE_ORDER")){
            list = scmFilterMapper.getColumnValueOrder(columnName,columnValue);
        }else if (tableName.equals("SCM_DELIVERY_DETAIL")){
            list = scmFilterMapper.getColumnValueDetail(columnName,columnValue);
        }else if (tableName.equals("SCM_STEEL_SETTLE")){
            list = scmFilterMapper.getColumnValueSteel(columnName,columnValue);
        }
        return list;
    }

    public String getMaxCode(){
        String code = scmFilterMapper.getMaxCode();
        code = String.format("%0" + code.length() + "d", Integer.parseInt(code) + 1);
        return code;
    }

    public Page<Map<String, Object>> selList(String name,String tableName,String column){
        Page page = PageFactory.defaultPage();
        return scmFilterMapper.selFilterList(page,name,tableName,column);
    }

    public void addFilter(ScmFilter scmFilter){
        if (ToolUtil.isOneEmpty(scmFilter.getCode(),scmFilter.getFColumn(),scmFilter.getTableName())){
            throw new RequestEmptyException();
        }
        ShiroUser user = ShiroKit.getUserNotNull();
        scmFilter.setCreater(user.getAccount());
        Date date = new Date();
        scmFilter.setCreatTime(date);

        this.save(scmFilter);
    }

    public void updateFilter(ScmFilter scmFilter){
        ShiroUser user = ShiroKit.getUserNotNull();
        scmFilter.setUpdater(user.getAccount());
        Date date = new Date();
        scmFilter.setUpdateTime(date);
        this.updateById(scmFilter);
    }

    public void deleteFilter(List<String> ids){
        this.removeByIds(ids);
    }

    public ScmFilter getCode(String fName){
        return scmFilterMapper.getCode(fName);
    }

    public Page<Map<String, Object>> selFilterByOrder (String startTime,String endTime,String code,String column,String companyId){
        Page page = PageFactory.defaultPage();
        return scmFilterMapper.selFilterByOrder(page,startTime,endTime,code,column,companyId);
    }

    public Page<Map<String, Object>> selFilterByDelivery (String startTime,String endTime,String code,String column,String companyId){
        Page page = PageFactory.defaultPage();
        return scmFilterMapper.selFilterByDelivery(page,startTime,endTime,code,column,companyId);
    }

    public Page<Map<String, Object>> selFilterBySteel (String startTime,String endTime,String code,String column,String companyId){
        Page page = PageFactory.defaultPage();
        return scmFilterMapper.selFilterBySteel(page,startTime,endTime,code,column,companyId);
    }

    public List selFilterColumn(String tableName){
        return scmFilterMapper.selFilterColumn(tableName);
    }

    public List selCondition(String col,String sel,String tableName){
        return scmFilterMapper.selCondition(col,sel,tableName);}

    public List selValue(String id){
        return scmFilterMapper.selValue(id);
    }
}
