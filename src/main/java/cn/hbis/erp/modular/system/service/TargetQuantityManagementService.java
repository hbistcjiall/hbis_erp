package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.TargetQuantityManagement;
import cn.hbis.erp.modular.system.mapper.TargetQuantityManagementMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
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

        @Async
        public Page<Map<String, Object>> selectTargetManeg( String targetname,String year){
            Page page = PageFactory.defaultPage();
            return this.targetManagementMapper.selTargetManagement(page,targetname,year);
        }
        @Async
        public List<Map> getlist(){
            List<Map> list = targetManagementMapper.getlist();
            return list;
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
                    int num = targetManagementMapper.updateById(targetManagement);
                    if(num ==1){
                        flag=true;
                        return  flag;
                    }
                    return flag;
        }
        //第一个
        @Async
        public List<Map> salesmain( String name){
            List<Map> list = targetManagementMapper.salesmain(name);
            List<Map> su = targetManagementMapper.salesmainsum(name);
            String  sum = "0";
            if(su.size()>0){
            Map map = su.get(0);
            if(map!=null){
                sum = String.valueOf( map.get("ZYFKIMG"));
            }
            }
            double  sums = Double.parseDouble(sum);
            Map type = new HashMap();
            type.put("COMPANYNAME","集团产销资源总量");
            type.put("ZYFKIMG",sums);
            List<Map> newlist = new ArrayList<>();
            newlist.add(type);
            if(list.size()>0) {
                for (int i = 0; i < list.size(); i++) {
                    Map ma = list.get(i);
                    String mi = String.valueOf(ma.get("ZYFKIMG"));
                    double mis = Double.parseDouble(mi);
                    double bi = mis / sums;
                    if(bi>0){
                        String result = String.valueOf(bi * 100).substring(0, 4);
                        ma.put("BILI", result);
                        newlist.add(ma);
                    }else{
                        ma.put("BILI", "0");
                        newlist.add(ma);
                    }
                }
            }else{
                Map m = new HashMap();
                m.put("COMPANYNAME","技术中心");
                m.put("ZYFKIMG",0);
                m.put("BILI","0");
                Map m1 = new HashMap();
                m1.put("COMPANYNAME","出口");
                m1.put("ZYFKIMG",0);
                m1.put("BILI","0");
                Map m2 = new HashMap();
                m2.put("COMPANYNAME","子公司");
                m2.put("ZYFKIMG",0);
                m2.put("BILI","0");
                Map m3 = new HashMap();
                m3.put("COMPANYNAME","销售公司资");
                m3.put("ZYFKIMG",0);
                m3.put("BILI","0");
                newlist.add(m1);
                newlist.add(m2);
                newlist.add(m3);
            }
            return  newlist;
        }
        //第二个
        @Async
        public List<Map> mills(String name) {
            List<Map> sum = targetManagementMapper.Steelmillssum(name);
            List<Map> newlist = new ArrayList<Map>();
            double jhs = 0;
            double xhs = 0;

                Map map = sum.get(0);
            if(map!=null) {
                String jh = String.valueOf(map.get("JH"));
                if (jh != null && !jh.equals("") && !jh.equals("null")) {
                    jhs = Double.parseDouble(map.get("JH").toString());
                }
                String xh = String.valueOf(map.get("XH"));
                if (xh != null && !xh.equals("") && !xh.equals("null")) {
                    xhs = Double.parseDouble(xh);
                } else {
                    xhs = 0;
                }
                Map mills = new HashMap();
                mills.put("COMPANYNAME", "集团");
                mills.put("JH", jhs);
                mills.put("XH", xhs);
                newlist.add(mills);
            }else {
            Map mills = new HashMap();
            mills.put("COMPANYNAME", "集团");
            mills.put("JH", jhs);
            mills.put("XH", xhs);
            newlist.add(mills);}
            Map m = new HashMap();
            m.put("XH", 0);
            m.put("COMPANYNAME", "唐钢");
            m.put("JH", 0);
            Map m1 = new HashMap();
            m1.put("XH", 0);
            m1.put("COMPANYNAME", "邯钢");
            m1.put("JH", 0);
            Map m2 = new HashMap();
            m2.put("XH", 0);
            m2.put("COMPANYNAME", "宣钢");
            m2.put("JH", 0);
            Map m3 = new HashMap();
            m3.put("XH", 0);
            m3.put("COMPANYNAME", "承钢");
            m3.put("JH", 0);
            Map m4 = new HashMap();
            m4.put("XH", 0);
            m4.put("COMPANYNAME", "舞钢");
            m4.put("JH", 0);
            Map m5 = new HashMap();
            m5.put("XH", 0);
            m5.put("COMPANYNAME", "石钢");
            m5.put("JH", 0);
            Map m6 = new HashMap();
            m6.put("XH", 0);
            m6.put("COMPANYNAME", "衡板");
            m6.put("JH", 0);
            newlist.add(m);
            newlist.add(m1);
            newlist.add(m2);
            newlist.add(m3);
            newlist.add(m4);
            newlist.add(m5);
            newlist.add(m6);
            List<Map> list = targetManagementMapper.Steelmillsplan(name);
            if (list.size() > 0) {
                for (int j=1;j<newlist.size();j++){
                    for (int i = 0; i < list.size(); i++) {
                        if(newlist.get(j).get("COMPANYNAME").equals(list.get(i).get("COMPANYNAME"))){
                            newlist.add(j,list.get(i));
                            newlist.remove(j+1);
                        }else{
                            continue;
                        }
                    }
                }
                return newlist;
                }
            return newlist;



        }
        //第三个
        @Async
        public List<Map> typesa(String name){
            List<Map> sum = targetManagementMapper.typessum(name);
            Map map = sum.get(0);
            String  jsl = "0";
            String pzgl = "0";
            String  nmb = "0";
            if(map!=null) {
                if (map.get("JSL") != null) {
                    jsl = map.get("JSL").toString();
                }
                if (map.get("PZGL") != null) {
                    pzgl = map.get("PZGL").toString();
                }
                if (map.get("NMB") != null) {
                    nmb = map.get("NMB").toString();
                }
            }
            double a = Double.parseDouble(nmb);
            double b=Double.parseDouble(jsl);
            double c=Double.parseDouble(pzgl);
            String result = "0";
            double bi = a/b*100;
            if(bi>0){
            result = String.valueOf(bi).substring(0,4);}

            Map type = new HashMap();
            type.put("NAME","总量");
            type.put("JSL",b);
            type.put("PZGL",c);
            type.put("BILI",result+"%");
            List<Map> newlist = new ArrayList<>();
            newlist.add(type);
            List<Map> list = targetManagementMapper.typeselect(name);
            if(list.size()>0) {
                for (int i = 0; i < list.size(); i++) {
                    Map m1 = list.get(i);
                    String bili = m1.get("BILI").toString();
                    m1.put("BILI", bili + "%");
                    newlist.add(m1);
                }
            }else{
                Map ma = new HashMap();
                ma.put("NAME","热板");
                ma.put("JSL",0);
                ma.put("PZGL",0);
                ma.put("BILI","0%");
                Map ma1 = new HashMap();
                ma1.put("NAME","冷板");
                ma1.put("JSL",0);
                ma1.put("PZGL",0);
                ma1.put("BILI","0%");
                Map ma2 = new HashMap();
                ma2.put("NAME","宽厚板");
                ma2.put("JSL",0);
                ma2.put("PZGL",0);
                ma2.put("BILI","0%");
                Map ma3 = new HashMap();
                ma3.put("NAME","棒线");
                ma3.put("JSL",0);
                ma3.put("PZGL",0);
                ma3.put("BILI","0%");
                Map ma4 = new HashMap();
                ma4.put("NAME","型带");
                ma4.put("JSL",0);
                ma4.put("PZGL",0);
                ma4.put("BILI","0%");
                newlist.add(ma);
                newlist.add(ma1);
                newlist.add(ma2);
                newlist.add(ma3);
                newlist.add(ma4);
            }

            return newlist;

            }
        //第四个
        @Async
        public List<Map> settl(String name){
            List<Map> sum = targetManagementMapper.Steelsum(name);
            Map map = sum.get(0);
            double jhs = 0.0;
            double jsls = 0.0;
            double pzgls=0.0;
            double bi = 0;
            String  jh ="";
            String jsl ="";
            String   pzgl ="";
            if(!map.get("JH").equals("null")){
                jh = map.get("JH").toString();
            }
            if(!map.get("JSL").equals("null")){
                jsl=map.get("JSL").toString();
            }
            if(!map.get("PZGL").equals("null")&&map.get("PZGL")!=null){
                pzgl =  map.get("PZGL").toString();
            }else {
                pzgl="0";
            }
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
            List<Map> newlist =new ArrayList<>();
            newlist.add(type);
            Map m =  new HashMap();
            m.put("COMPANYNAME","唐钢");
            m.put("PZGL",0);
            m.put("JSL",0);
            m.put("BILI","0%");
            Map m11 =  new HashMap();
            m11.put("COMPANYNAME","邯钢");
            m11.put("PZGL",0);
            m11.put("JSL",0);
            m11.put("BILI","0%");
            Map m1 =  new HashMap();
            m1.put("COMPANYNAME","宣钢");
            m1.put("PZGL",0);
            m1.put("JSL",0);
            m1.put("BILI","0%");
            Map m2 =  new HashMap();
            m2.put("COMPANYNAME","承钢");
            m2.put("PZGL",0);
            m2.put("JSL",0);
            m2.put("BILI","0%");
            Map m3 =  new HashMap();
            m3.put("COMPANYNAME","舞钢");
            m3.put("PZGL",0);
            m3.put("JSL",0);
            m3.put("BILI","0%");
            Map m4 =  new HashMap();
            m4.put("COMPANYNAME","石钢");
            m4.put("PZGL",0);
            m4.put("JSL",0);
            m4.put("BILI","0%");
            Map m5 =  new HashMap();
            m5.put("COMPANYNAME","衡板");
            m5.put("PZGL",0);
            m5.put("JSL",0);
            m5.put("BILI","0%");
            newlist.add(m);
            newlist.add(m11);
            newlist.add(m1);
            newlist.add(m2);
            newlist.add(m3);
            newlist.add(m4);
            newlist.add(m5);
            if(list.size()>0){
                for (int j=1;j<newlist.size();j++){
                    for (int i = 0; i < list.size(); i++) {
                        if(newlist.get(j).get("COMPANYNAME").equals(list.get(i).get("COMPANYNAME"))){
                            newlist.add(j,list.get(i));
                            newlist.remove(j+1);
                        }else{
                            continue;
                        }
                    }
                }
                return newlist;
            }
            return newlist;
        }


}
