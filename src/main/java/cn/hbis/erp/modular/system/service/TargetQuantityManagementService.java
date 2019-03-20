package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.TargetQuantityManagement;
import cn.hbis.erp.modular.system.mapper.TargetQuantityManagementMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

        public boolean addORUpadte(String id,String targetname,String year,String jan,String feb,String mar,String apr,String may,String jun,String jul,String aug,String sep,String oct,String nov,String dec){
            boolean flag =false;

            if(id != null && !id.equals("")){
                TargetQuantityManagement targetManagement = targetManagementMapper.selectById(id);
                    if(!year.equals("")&&year!=null){
                        targetManagement.setYaer(year);
                    }

                    if(jan==null||jan.equals("")){
                        targetManagement.setJanuary("0");
                    }else{
                        targetManagement.setJanuary(jan);
                    }
                    if(feb==null||feb.equals("")){
                        targetManagement.setFebruary("0");
                    }else{
                        targetManagement.setFebruary(feb);
                    }

                    if(mar==null||mar.equals("")){
                        targetManagement.setMarch("0");
                    }else{
                    targetManagement.setMarch(mar);}
                    if(apr==null||apr.equals("")){
                        targetManagement.setApril("0");
                    }else{
                    targetManagement.setApril(apr);}
                    if(may==null||may.equals("")){
                        targetManagement.setMay("0");
                    }else{
                    targetManagement.setMay(may);}
                    if(jun==null||jun.equals("")){
                        targetManagement.setJune("0");
                    }else {
                        targetManagement.setJune(jun);
                    }
                    if(jul==null||jul.equals("")){
                        targetManagement.setJuly("0");
                    }else{
                    targetManagement.setJuly(jul);}
                if(aug==null||aug.equals("")){
                    targetManagement.setAugust("0");
                }else{
                    targetManagement.setAugust(aug);}
                if(sep==null||sep.equals("")){
                    targetManagement.setSeptember("0");
                }else{
                    targetManagement.setSeptember(sep);}
                if(oct==null||oct.equals("")){
                    targetManagement.setOctober("0");
                }else{
                    targetManagement.setOctober(oct);}
                if(nov==null||nov.equals("")){
                    targetManagement.setNovember("0");
                }else{
                    targetManagement.setNovember(nov);}
                if(dec==null||dec.equals("")){
                    targetManagement.setDecember("0");
                }else{
                    targetManagement.setDecember(dec);}
                int num = targetManagementMapper.updateById(targetManagement);
                if(num == 1){
                    flag = true;
                    return flag;
                }else{
                    return flag;
                }
            }else{
                List<Map> list = targetManagementMapper.theList();
                for(int i=0;i<list.size();i++){
                    String names = list.get(i).get("CODE").toString();
                    if(targetname.equals(names)){
                        return false;
                    }
                }
                TargetQuantityManagement targetManagement = new TargetQuantityManagement();
                if(!year.equals("")&&year!=null){
                    targetManagement.setYaer(year);
                }
                if(!targetname.equals("")&&targetname!=null){

                    targetManagement.setResponsibilityunit(targetname);
                }
                if(jan==null||jan.equals("")){
                    targetManagement.setJanuary("0");
                }else{
                    targetManagement.setJanuary(jan);
                }
                if(feb==null||feb.equals("")){
                    targetManagement.setFebruary("0");
                }else{
                    targetManagement.setFebruary(feb);
                }
                if(mar==null||mar.equals("")){
                    targetManagement.setMarch("0");
                }else{
                    targetManagement.setMarch(mar);}
                if(apr==null||apr.equals("")){
                    targetManagement.setApril("0");
                }else{
                    targetManagement.setApril(apr);}
                if(may==null||may.equals("")){
                    targetManagement.setMay("0");
                }else{
                    targetManagement.setMay(may);}
                if(jun==null||jun.equals("")){
                    targetManagement.setJune("0");
                }else {
                    targetManagement.setJune(jun);
                }
                if(jul==null||jul.equals("")){
                    targetManagement.setJuly("0");
                }else{
                    targetManagement.setJuly(jul);}
                if(aug==null||aug.equals("")){
                    targetManagement.setAugust("0");
                }else{
                    targetManagement.setAugust(aug);}
                if(sep==null||sep.equals("")){
                    targetManagement.setSeptember("0");
                }else{
                    targetManagement.setSeptember(sep);}
                if(oct==null||oct.equals("")){
                    targetManagement.setOctober("0");
                }else{
                    targetManagement.setOctober(oct);}
                if(nov==null||nov.equals("")){
                    targetManagement.setNovember("0");
                }else{
                targetManagement.setNovember(nov);}
                if(dec==null||dec.equals("")){
                    targetManagement.setDecember("0");
                }else{
                    targetManagement.setDecember(dec);}
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
                    targetManagement.setResponsibilityunit("");
                    int num = targetManagementMapper.updateById(targetManagement);
                    if(num ==1){
                        flag=true;
                        return  flag;
                    }
                    return flag;
        }

        public  TargetQuantityManagement getOne(String id){
                TargetQuantityManagement tm = targetManagementMapper.selectById(id);

            return tm;
        }

        public List<Map> getlist(){
            List<Map> map = new ArrayList<>();
            map = targetManagementMapper.selectList();
            return map ;
        }
}
