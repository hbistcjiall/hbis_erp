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
        //第一个
        public List<Map> salesmain( String name){
            List<Map> list = targetManagementMapper.salesmain(name);
            List<Map> su = targetManagementMapper.salesmainsum(name);
            Map map = su.get(0);
            String  sum =String.valueOf( map.get("ZYFKIMG"));
            double  sums = Double.parseDouble(sum);
            Map type = new HashMap();
            type.put("COMPANYNAME","集团产销资源总量");
            type.put("ZYFKIMG",sums);
            List<Map> newlist = new ArrayList<>();
            newlist.add(type);
            for(int i=0;i<list.size();i++){
                Map ma = list.get(i);
                String mi =String.valueOf( ma.get("ZYFKIMG"));
                double  mis = Double.parseDouble(mi);
                double bi = mis/sums;
                String result = String.valueOf(bi*100).substring(0,4);
                ma.put("BILI",result+"%");
                newlist.add(ma);

            }
            return  newlist;
        }
        //第二个
        public List<Map> mills(String name) {
            List<Map> sum = targetManagementMapper.Steelmillssum(name);
            Map map = sum.get(0);
            double jhs = 0;
            double xhs = 0;
            String jh = String.valueOf(map.get("JH"));
            if (jh != null &&!jh.equals("") && !jh.equals("null")) {
                jhs = Double.parseDouble(map.get("JH").toString());
            }
            String xh = String.valueOf(map.get("XH"));
            if (xh != null && !xh.equals("") && !xh.equals("null")) {
                xhs = Double.parseDouble(xh);
            }else{
                xhs = 0;
            }
            Map mills = new HashMap();
            mills.put("companyname", "集团");
            mills.put("JH", jhs);
            mills.put("XH", xhs);
            List<Map> list = targetManagementMapper.Steelmillsplan(name);
            list.add(mills);
            return list;
        }
        //第三个
        public List<Map> typesa(String name){
            List<Map> sum = targetManagementMapper.typessum(name);
            Map map = sum.get(0);
            String  jsl = "";
            String pzgl = "";
            String  nmb = "";
            if(map.get("JSL")!=null){
                jsl = map.get("JSL").toString();
            }
            if(map.get("PZGL")!=null){
                pzgl = map.get("PZGL").toString();}
            if(map.get("NMB")!=null){
                nmb = map.get("NMB").toString();}
            double a = Double.parseDouble(nmb);
            double b=Double.parseDouble(jsl);
            double c=Double.parseDouble(pzgl);
            double bi = a/b*100;
            String result = String.valueOf(bi).substring(0,4);
            Map type = new HashMap();
            type.put("NAME","总量");
            type.put("JSL",b);
            type.put("PZGL",c);
            type.put("BILI",result+"%");
            List<Map> list = targetManagementMapper.typeselect(name);
            for (int i=0;i<list.size();i++){
                Map m1=list.get(i);
                String bili = m1.get("BILI").toString();
                m1.put("BILI",bili+"%");
            }
            list.add(type);
            return list;


        }
        //第四个
        public List<Map> settl(String name){
            List<Map> sum = targetManagementMapper.Steelsum(name);
            Map map = sum.get(0);
            double jhs = 0;
            double jsls = 0;
            double pzgls=0;
            double bi = 0;
            String  jh ="";
            String jsl ="";
            String   pzgl ="";
            jh = map.get("JH").toString();
            jsl=map.get("JSL").toString();
            pzgl =  map.get("PZGL").toString();
            if(jh!=null&&!jh.equals("null")&&!jh.equals("")){
                  jh =  map.get("JH").toString();
            }
            if(jsl!=null&&!jsl.equals("null")&&!jsl.equals("")){
                 jsl =  map.get("JSL").toString();
            }
            if(pzgl!=null&&!pzgl.equals("null")&&!pzgl.equals("")){
                   pzgl =  map.get("PZGL").toString();
            }
            if (jh != null && !jh.equals("") &&!jh.equals("null")) {
                jhs = Double.parseDouble(jh);
            }
            if (jsl != null && !jsl.equals("") && !jsl.equals("null")) {
                jsls = Double.parseDouble(jsl);
            }
            if (pzgl != null && !pzgl.equals("") && !pzgl.equals("null")) {
                pzgls = Double.parseDouble(pzgl);
            }
            if(jhs/jsls>0){
                bi = jhs/jsls;
            }

            String result;
            if(bi>0) {
                result = String.valueOf(bi * 100).substring(0, 4);
            }else{
                result = String.valueOf(bi);
            }
            Map type =  new HashMap();
            type.put("COMPANYNAME","集团");
            type.put("PZGL",pzgls);
            type.put("JSL",jsls);
            type.put("BILI",result+"%");
            List<Map> list = targetManagementMapper.Steellist(name);
            for (int i=0;i<list.size();i++){
                String m1= list.get(i).get("BILI").toString();
                list.get(i).put("BILI",m1+"%");
            }
            list.add(type);
            return list;

        }


}
