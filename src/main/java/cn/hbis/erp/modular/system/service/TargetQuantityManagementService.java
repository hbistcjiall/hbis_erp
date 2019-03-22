package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.TargetQuantityManagement;
import cn.hbis.erp.modular.system.mapper.TargetQuantityManagementMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TargetQuantityManagementService extends ServiceImpl<TargetQuantityManagementMapper, TargetQuantityManagement> {

        @Resource
        private  TargetQuantityManagementMapper targetManagementMapper;


        public Page<Map<String, Object>> selectTargetManeg( String targetname,String year){
            Page page = PageFactory.defaultPage();
            return this.targetManagementMapper.selTargetManagement(page,targetname,year);
        }
        public List<Map> getlist(){
            List<Map> list = targetManagementMapper.getlist();
            return list;
        }
        public boolean addORUpadte(String id,String targetname,String year,String jan,String feb,String mar,String apr,String may,String jun,String jul,String aug,String sep,String oct,String nov,String dec){
            boolean flag =false;
            if(id != null && !id.equals("")){
                TargetQuantityManagement targetManagement = targetManagementMapper.selectById(id);
                    targetManagement.setYaer(year);
                    targetManagement.setResponsibilityunit(targetname);
                    targetManagement.setJanuary(jan);
                    targetManagement.setFebruary(feb);
                    targetManagement.setMarch(mar);
                    targetManagement.setApril(apr);
                    targetManagement.setMay(may);
                    targetManagement.setJune(jun);
                    targetManagement.setJuly(jul);
                    targetManagement.setAugust(aug);
                    targetManagement.setSeptember(sep);
                    targetManagement.setOctober(oct);
                    targetManagement.setNovember(nov);
                    targetManagement.setDecember(dec);
                int num = targetManagementMapper.updateById(targetManagement);
                if(num == 1){
                    flag = true;
                    return flag;
                }else{
                    return flag;
                }
            }else{
                TargetQuantityManagement targetManagement = new TargetQuantityManagement();
                targetManagement.setYaer(year);
                targetManagement.setResponsibilityunit(targetname);
                targetManagement.setJanuary(jan);
                targetManagement.setFebruary(feb);
                targetManagement.setMarch(mar);
                targetManagement.setApril(apr);
                targetManagement.setMay(may);
                targetManagement.setJune(jun);
                targetManagement.setJuly(jul);
                targetManagement.setAugust(aug);
                targetManagement.setSeptember(sep);
                targetManagement.setOctober(oct);
                targetManagement.setNovember(nov);
                targetManagement.setDecember(dec);
                targetManagement.setDeletestatus("0");
                int num = targetManagementMapper.insert(targetManagement);
                if(num == 1){
                    flag = true;
                    return flag;
                }else{
                    return flag;
                }
            }
        }

        public  boolean delete(String id){
            boolean flag = false;
            TargetQuantityManagement targetManagement = targetManagementMapper.selectById(id);
                    targetManagement.setDeletestatus("1");
                    int num = targetManagementMapper.updateById(targetManagement);
                    if(num ==1){
                        flag=true;
                        return  flag;
                    }
                    return flag;
        }

     
}
