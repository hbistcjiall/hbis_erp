package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.TargetQuantityManagement;
import cn.hbis.erp.modular.system.mapper.ScmSteelSettleMapper;
import cn.hbis.erp.modular.system.mapper.TargetQuantityManagementMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TargetQuantityManagementService extends ServiceImpl<TargetQuantityManagementMapper, TargetQuantityManagement> {

        @Resource
        private  TargetQuantityManagementMapper targetManagementMapper;
        @Resource
        private ScmSteelSettleMapper scmSteelSettleMapper;
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
                    targetManagement.setYear(year);
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
                    targetManagement.setYear(year);
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
            Calendar cale = null;
            cale = Calendar.getInstance();
            int year =cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            List<Map> list = targetManagementMapper.salesmain(name,String.valueOf(month),String.valueOf(year));
            List<Map> su = targetManagementMapper.salesmainsum(name,String.valueOf(month),String.valueOf(year));
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
                        String result = String.valueOf(bi * 100)+"00";
                           String  results = result .substring(0, 4);

                        ma.put("BILI", results);

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
            Calendar cale = null;
            cale = Calendar.getInstance();
            int year =cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            List<Map> sum = targetManagementMapper.Steelmillssum(name,String.valueOf(month),String.valueOf(year));
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

            List<Map> list = targetManagementMapper.Steelmillsplan(name,String.valueOf(month),String.valueOf(year));
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
            Calendar cale = null;
            cale = Calendar.getInstance();
            int year =cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            List<Map> sum = targetManagementMapper.typessum(name,String.valueOf(month),String.valueOf(year));
            Map map = sum.get(0);
            String  jsl = "0";
            String pzgl = "0";
            if(map!=null) {
                if (map.get("JSL") != null) {
                    jsl = map.get("JSL").toString();
                }
                if (map.get("PZGL") != null) {
                    pzgl = map.get("PZGL").toString();
                }

            }
            double b=Double.parseDouble(jsl);
            double c=Double.parseDouble(pzgl);
            String result = "0";
            double bi = c/b*100;
            if(bi>0){
            result = String.valueOf(bi).substring(0,4);}

            Map type = new HashMap();
            type.put("NAME","总量");
            type.put("JSL",b);
            type.put("PZGL",c);
            type.put("BILI",result);
            List<Map> newlist = new ArrayList<>();
            newlist.add(type);
            List<Map> list = targetManagementMapper.typeselect(name,String.valueOf(month),String.valueOf(year));
            if(list.size()>0) {
                for (int i = 0; i < list.size(); i++) {
                    Map m1 = list.get(i);
                    String bili = m1.get("BILI").toString();
                    m1.put("BILI", bili );
                    newlist.add(m1);
                }
            }else{
                Map ma = new HashMap();
                ma.put("NAME","热板");
                ma.put("JSL",0);
                ma.put("PZGL",0);
                ma.put("BILI",0);
                Map ma1 = new HashMap();
                ma1.put("NAME","冷板");
                ma1.put("JSL",0);
                ma1.put("PZGL",0);
                ma1.put("BILI",0);
                Map ma2 = new HashMap();
                ma2.put("NAME","宽厚板");
                ma2.put("JSL",0);
                ma2.put("PZGL",0);
                ma2.put("BILI",0);
                Map ma3 = new HashMap();
                ma3.put("NAME","棒线");
                ma3.put("JSL",0);
                ma3.put("PZGL",0);
                ma3.put("BILI",0);
                Map ma4 = new HashMap();
                ma4.put("NAME","型带");
                ma4.put("JSL",0);
                ma4.put("PZGL",0);
                ma4.put("BILI",0);
                newlist.add(ma);
                newlist.add(ma1);
                newlist.add(ma2);
                newlist.add(ma3);
                newlist.add(ma4);
            }

            return newlist;

            }
        //第四个
        public List<Map> settl(String name){
            Calendar cale = Calendar.getInstance();
            int year =cale.get(Calendar.YEAR);
            int month = cale.get(Calendar.MONTH) + 1;
            List<Map> sum = targetManagementMapper.Steelsum(name,String.valueOf(month),String.valueOf(year));
            Map map = sum.get(0);
            double jsls = 0.0;
            double pzgls=0.0;
            double bi = 0;
            String result="0";
            String jsl ="";
            String pzgl ="";
            if(map !=null) {
                if (!map.get("JSL").equals("null")) {
                    jsl = map.get("JSL").toString();
                }
                if (!map.get("PZGL").equals("null") && map.get("PZGL") != null) {
                    pzgl = map.get("PZGL").toString();
                } else {
                    pzgl = "0";
                }

                if (jsl != null && !jsl.equals("null") && !jsl.equals("")) {
                    jsl = map.get("JSL").toString();
                }
                if (pzgl != null && !pzgl.equals("null") && !pzgl.equals("")) {
                    pzgl = map.get("PZGL").toString();
                }
                if (jsl != null && !jsl.equals("") && !jsl.equals("null")) {
                    jsls = Double.parseDouble(jsl);
                }
                if (pzgl != null && !pzgl.equals("") && !pzgl.equals("null")) {
                    pzgls = Double.parseDouble(pzgl);
                }
                if (pzgls / jsls > 0) {
                    bi = pzgls / jsls;
                }


                if (bi > 0) {
                    result = String.valueOf(bi * 100).substring(0, 4);
                } else {
                    result = String.valueOf(bi);
                }
            }
            Map type =  new HashMap();
            type.put("COMPANYNAME","集团");
            type.put("PZGL",pzgls);
            type.put("JSL",jsls);
            type.put("BILI",result);
            List<Map> list = targetManagementMapper.Steellist(name,String.valueOf(month),String.valueOf(year));
            List<Map> newlist =new ArrayList<>();
            newlist.add(type);
            Map m =  new HashMap();
            m.put("COMPANYNAME","唐钢");
            m.put("PZGL",0);
            m.put("JSL",0);
            m.put("BILI","0");
            Map m11 =  new HashMap();
            m11.put("COMPANYNAME","邯钢");
            m11.put("PZGL",0);
            m11.put("JSL",0);
            m11.put("BILI","0");
            Map m1 =  new HashMap();
            m1.put("COMPANYNAME","宣钢");
            m1.put("PZGL",0);
            m1.put("JSL",0);
            m1.put("BILI","0");
            Map m2 =  new HashMap();
            m2.put("COMPANYNAME","承钢");
            m2.put("PZGL",0);
            m2.put("JSL",0);
            m2.put("BILI","0");
            Map m3 =  new HashMap();
            m3.put("COMPANYNAME","舞钢");
            m3.put("PZGL",0);
            m3.put("JSL",0);
            m3.put("BILI","0");
            Map m4 =  new HashMap();
            m4.put("COMPANYNAME","石钢");
            m4.put("PZGL",0);
            m4.put("JSL",0);
            m4.put("BILI","0");
            Map m5 =  new HashMap();
            m5.put("COMPANYNAME","衡板");
            m5.put("PZGL",0);
            m5.put("JSL",0);
            m5.put("BILI","0");
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

    public List<Map<String,Object>> xsjswccxexport(String startTime, String endTime, String pz, @RequestParam(required = false) List<String> cx, String jd) {
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String,Object>> list = new ArrayList<>();
        list = scmSteelSettleMapper.getxsjswcs(startTime1+" 00:00:00",endTime1+" 23:59:59",pz,cx,jd);
        List<Map<String,Object>>  thelist = new ArrayList<Map<String,Object>>();
        Map map = new HashMap();
        String zl ="河钢集团";
        String cxname = "合计";
        double fkimg=0.00;
        double kzwi6 = 0.00;
        double zsj = 0;
        double nmfkimg = 0.00;
        double nmkzwi6 = 0.00;
        double nmsj = 0;
        double xszgsfkimg = 0.00;
        double xszgskzwi6 = 0.00;
        double xszgssj = 0;
        double zyfkimg = 0.00;
        double zykzwi6 = 0.00;
        double  zysj = 0;
        double fgsfkimg = 0.00;
        double fgskzwi6 = 0.00;
        double  fgssj = 0;
        double zgsfkimg = 0.00;
        double zgskzwi6 = 0.00;
        double zgssj =0;
        double xhfkimg = 0.00;
        double xhkzwi6 = 0.00;
        double xhsj=0;
        double sybfkimg = 0.00;
        double sybkzwi6 = 0.00;
        double sybsj = 0;
        double zbgsfkimg = 0.00;
        double zbgskzwi6 = 0.00;
        double zbgssj = 0;
        double ckfkimg = 0.00;
        double ckkzwi6 = 0.00;
        double  cksj = 0;
        for (int i =0; i<list.size() ;i++ ){
            if(!list.get(i).get("FKIMG").toString().equals("0")){
                fkimg = fkimg + Double.parseDouble(list.get(i).get("FKIMG").toString());
            }else {
                fkimg = fkimg +0;
            }
            if(!list.get(i).get("KZWI6").toString().equals("0")){
                kzwi6 = kzwi6 + Double.parseDouble(list.get(i).get("KZWI6").toString());
            }else {
                kzwi6 = kzwi6 +0;
            }

            if(!list.get(i).get("NMFKIMG").toString().equals("0")){
                nmfkimg = nmfkimg + Double.parseDouble(list.get(i).get("NMFKIMG").toString());
            }else {
                nmfkimg = nmfkimg +0;
            }
            if(!list.get(i).get("NMKZWI6").toString().equals("0")){
                nmkzwi6 = nmkzwi6 + Double.parseDouble(list.get(i).get("NMKZWI6").toString());
            }else {
                nmkzwi6 = nmkzwi6 +0;
            }
            if(!list.get(i).get("XSZGSFKIMG").toString().equals("0")){
                xszgsfkimg = xszgsfkimg + Double.parseDouble(list.get(i).get("XSZGSFKIMG").toString());
            }else {
                xszgsfkimg = xszgsfkimg +0;
            }
            if(!list.get(i).get("XSZGSKZWI6").toString().equals("0")){
                xszgskzwi6 = xszgskzwi6 + Double.parseDouble(list.get(i).get("XSZGSKZWI6").toString());
            }else {
                xszgskzwi6 = xszgskzwi6 +0;
            }
            if(!list.get(i).get("ZYFKIMG").toString().equals("0")){
                zyfkimg = zyfkimg + Double.parseDouble(list.get(i).get("ZYFKIMG").toString());
            }else {
                zyfkimg = zyfkimg  +0;
            }
            if(!list.get(i).get("FGSFKIMG").toString().equals("0")){
                fgsfkimg = fgsfkimg + Double.parseDouble(list.get(i).get("FGSFKIMG").toString());
            }else {
                fgsfkimg = fgsfkimg  +0;
            }

            if(!list.get(i).get("FGSKZWI6").toString().equals("0")){
                fgskzwi6 = fgskzwi6 + Double.parseDouble(list.get(i).get("FGSKZWI6").toString());
            }else {
                fgskzwi6 = fgskzwi6 +0;
            }
            if(!list.get(i).get("ZGSFKIMG").toString().equals("0")){
                zgsfkimg = zgsfkimg + Double.parseDouble(list.get(i).get("ZGSFKIMG").toString());
            }else {
                zgsfkimg = zgsfkimg +0;
            }

            if(!list.get(i).get("ZGSKZWI6").toString().equals("0")){
                zgskzwi6 = zgskzwi6 + Double.parseDouble(list.get(i).get("ZGSKZWI6").toString());
            }else {
                zgskzwi6 = zgskzwi6 +0;
            }

            if(!list.get(i).get("XHFKIMG").toString().equals("0")){
                xhfkimg = xhfkimg + Double.parseDouble(list.get(i).get("XHFKIMG").toString());
            }else {
                xhfkimg = xhfkimg +0;
            }
            if(!list.get(i).get("XHKZWI6").toString().equals("0")){
                xhkzwi6 = xhkzwi6 + Double.parseDouble(list.get(i).get("XHKZWI6").toString());
            }else {
                xhkzwi6 = xhkzwi6 +0;
            }
            if(!list.get(i).get("ZBGSFKIMG").toString().equals("0")){
                zbgsfkimg = zbgsfkimg + Double.parseDouble(list.get(i).get("ZBGSFKIMG").toString());
            }else {
                zbgsfkimg = zbgsfkimg  +0;
            }

            if(!list.get(i).get("ZBGSKZWI6").toString().equals("0")){
                zbgskzwi6 = zbgskzwi6 + Double.parseDouble(list.get(i).get("ZBGSKZWI6").toString());
            }else {
                zbgskzwi6 = zbgskzwi6 +0;
            }
            if(!list.get(i).get("CKFKIMG").toString().equals("0")){
                ckfkimg = ckfkimg + Double.parseDouble(list.get(i).get("CKFKIMG").toString());
            }else {
                ckfkimg = ckfkimg +0;
            }
            if(!list.get(i).get("CKKZWI6").toString().equals("0")){
                ckkzwi6 = ckkzwi6 + Double.parseDouble(list.get(i).get("CKKZWI6").toString());
            }else {
                ckkzwi6 = ckkzwi6 +0;
            }

            if(!list.get(i).get("SYBFKIMG").toString().equals("0")){
                sybfkimg = sybfkimg + Double.parseDouble(list.get(i).get("SYBFKIMG").toString());
            }else {
                sybfkimg = sybfkimg  +0;
            }

            if(!list.get(i).get("SYBKZWI6").toString().equals("0")){
                sybkzwi6 = sybkzwi6 + Double.parseDouble(list.get(i).get("SYBKZWI6").toString());
            }else {
                sybkzwi6 = sybkzwi6 +0;
            }
        }
        if(kzwi6/fkimg>0){
            zsj = kzwi6*10000/fkimg;

        }else {
            zsj = 0;
        }
        if(nmkzwi6/nmfkimg>0){
            nmsj = nmkzwi6/nmfkimg;
        }else {
            nmsj = 0;
        }
        if(xszgskzwi6/xszgsfkimg>0){
            xszgssj = xszgskzwi6/xszgsfkimg;
        }else {
            xszgssj = 0;
        }
        if(zykzwi6/zyfkimg>0){
            zysj = zykzwi6/zyfkimg;
        }else {
            zysj = 0;
        }

        if(fgskzwi6/fgsfkimg>0){
            fgssj = fgskzwi6/fgsfkimg;
        }else {
            fgssj = 0;
        }
        if(zgskzwi6/zgsfkimg>0){
            zgssj = zgskzwi6/zgsfkimg;
        }else {
            zgssj = 0;
        }
        if(xhkzwi6/xhfkimg>0){
            xhsj = xhkzwi6/xhfkimg;
        }else {
            xhsj = 0;
        }

        if(zbgskzwi6/zbgsfkimg>0){
            zbgssj = zbgskzwi6/zbgsfkimg;
        }else {
            zbgssj = 0;
        }
        if(ckkzwi6/ckfkimg>0){
            cksj = ckkzwi6/ckfkimg;
        }else {
            cksj = 0;
        }
        if(sybfkimg/sybfkimg>0){
            sybsj = sybkzwi6/sybfkimg;
        }else {
            sybsj = 0;
        }
        map.put("ZL",zl);
        map.put("CXNAME",cxname);
        map.put("FKIMG",Double.valueOf(String.format("%.2f", fkimg) ));
        map.put("ZSJ",Double.valueOf(String.format("%.2f", zsj) ));
        map.put("KZWI6",Double.valueOf(String.format("%.2f", kzwi6) ));
        map.put("NMFKIMG",Double.valueOf(String.format("%.2f", nmfkimg) ) );
        map.put("NMSJ",Double.valueOf(String.format("%.2f", nmsj) ) );
        map.put("XSZGSFKIMG",Double.valueOf(String.format("%.2f", xszgsfkimg) ));
        map.put("XSZGSSJ",Double.valueOf(String.format("%.2f", xszgssj) ));
        map.put("ZYFKIMG",Double.valueOf(String.format("%.2f", zyfkimg) ) );
        map.put("ZYSJ",Double.valueOf(String.format("%.2f", zysj) ) );
        map.put("FGSFKIMG", Double.valueOf(String.format("%.2f", fgsfkimg) ));
        map.put("FGSSJ",Double.valueOf(String.format("%.2f", fgssj) ) );

        map.put("ZGSFKIMG",Double.valueOf(String.format("%.2f", zgsfkimg) ) );
        map.put("ZGSSJ",Double.valueOf(String.format("%.2f", zgssj) ));
        map.put("SYBFKIMG",Double.valueOf(String.format("%.2f", sybfkimg) ));
        map.put("SYBSJ",Double.valueOf(String.format("%.2f", sybsj) ));
        map.put("XHFKIMG",Double.valueOf(String.format("%.2f", xhfkimg) ) );
        map.put("XHSJ",Double.valueOf(String.format("%.2f", xhsj) ) );
        map.put("ZBGSFKIMG",Double.valueOf(String.format("%.2f", zbgsfkimg) ) );
        map.put("ZBGSSJ",Double.valueOf(String.format("%.2f", zbgssj) ));
        map.put("CKFKIMG",Double.valueOf(String.format("%.2f", ckfkimg) ) );
        map.put("CKSJ",Double.valueOf(String.format("%.2f", cksj) ) );
        thelist.add(map);
        for (int i = 0;i<list.size();i++){
            thelist.add(list.get(i));
        }
        List<Map<String,Object>> newlist = xiaoji(list);
        for (int i = 0;i<newlist.size();i++){
            thelist.add(newlist.get(i));
        }
        return  thelist;
    }

	public List<Map<String,Object>> xsjsjtwccxexport(String startTime, String endTime, String pz, @RequestParam(required = false) List<String> cx, String jd,String pzg) {
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String,Object>> list = new ArrayList<>();
        list = scmSteelSettleMapper.getjtxsjswcs(startTime1+" 00:00:00",endTime1+" 23:59:59",pz,cx,jd,pzg);
        List<Map<String,Object>>  thelist = new ArrayList<Map<String,Object>>();
        Map map = new HashMap();
        String zl ="河钢集团";
        String cxname = "合计";
        double fkimg=0.00;
        double kzwi6 = 0.00;
        double zsj = 0;
        double nmfkimg = 0.00;
        double nmkzwi6 = 0.00;
        double nmsj = 0;
        double xszgsfkimg = 0.00;
        double xszgskzwi6 = 0.00;
        double xszgssj = 0;
        double zgsfkimg = 0.00;
        double zgskzwi6 = 0.00;
        double zgssj =0;
        double ckfkimg = 0.00;
        double ckkzwi6 = 0.00;
        double  cksj = 0;
        for (int i =0; i<list.size() ;i++ ){
            if(!list.get(i).get("FKIMG").toString().equals("0")){
                fkimg = fkimg + Double.parseDouble(list.get(i).get("FKIMG").toString());
            }else {
                fkimg = fkimg +0;
            }
            if(!list.get(i).get("KZWI6").toString().equals("0")){
                kzwi6 = kzwi6 + Double.parseDouble(list.get(i).get("KZWI6").toString());
            }else {
                kzwi6 = kzwi6 +0;
            }
            if(!list.get(i).get("NMFKIMG").toString().equals("0")){
                nmfkimg = nmfkimg + Double.parseDouble(list.get(i).get("NMFKIMG").toString());
            }else {
                nmfkimg = nmfkimg +0;
            }
            if(!list.get(i).get("NMKZWI6").toString().equals("0")){
                nmkzwi6 = nmkzwi6 + Double.parseDouble(list.get(i).get("NMKZWI6").toString());
            }else {
                nmkzwi6 = nmkzwi6 +0;
            }
            if(!list.get(i).get("XSZGSFKIMG").toString().equals("0")){
                xszgsfkimg = xszgsfkimg + Double.parseDouble(list.get(i).get("XSZGSFKIMG").toString());
            }else {
                xszgsfkimg = xszgsfkimg +0;
            }
            if(!list.get(i).get("XSZGSKZWI6").toString().equals("0")){
                xszgskzwi6 = xszgskzwi6 + Double.parseDouble(list.get(i).get("XSZGSKZWI6").toString());
            }else {
                xszgskzwi6 = xszgskzwi6 +0;
            }
            if(!list.get(i).get("ZGSFKIMG").toString().equals("0")){
                zgsfkimg = zgsfkimg + Double.parseDouble(list.get(i).get("ZGSFKIMG").toString());
            }else {
                zgsfkimg = zgsfkimg +0;
            }
            if(!list.get(i).get("ZGSKZWI6").toString().equals("0")){
                zgskzwi6 = zgskzwi6 + Double.parseDouble(list.get(i).get("ZGSKZWI6").toString());
            }else {
                zgskzwi6 = zgskzwi6 +0;
            }
            if(!list.get(i).get("CKFKIMG").toString().equals("0")){
                ckfkimg = ckfkimg + Double.parseDouble(list.get(i).get("CKFKIMG").toString());
            }else {
                ckfkimg = ckfkimg +0;
            }
            if(!list.get(i).get("CKKZWI6").toString().equals("0")){
                ckkzwi6 = ckkzwi6 + Double.parseDouble(list.get(i).get("CKKZWI6").toString());
            }else {
                ckkzwi6 = ckkzwi6 +0;
            }
        }
        if(kzwi6/fkimg>0){
            zsj = kzwi6*10000/fkimg;

        }else {
            zsj = 0;
        }
        if(nmkzwi6/nmfkimg>0){
            nmsj = nmkzwi6/nmfkimg;
        }else {
            nmsj = 0;
        }
        if(xszgskzwi6/xszgsfkimg>0){
            xszgssj = xszgskzwi6/xszgsfkimg;
        }else {
            xszgssj = 0;
        }

        if(zgskzwi6/zgsfkimg>0){
            zgssj = zgskzwi6/zgsfkimg;
        }else {
            zgssj = 0;
        }
        if(ckkzwi6/ckfkimg>0){
            cksj = ckkzwi6/ckfkimg;
        }else {
            cksj = 0;
        }

        map.put("ZL",zl);
        map.put("CXNAME",cxname);
        map.put("FKIMG",Double.valueOf(String.format("%.2f", fkimg) ));
        map.put("ZSJ",Double.valueOf(String.format("%.2f", zsj) ));
        map.put("KZWI6",Double.valueOf(String.format("%.2f", kzwi6) ));
        map.put("NMFKIMG",Double.valueOf(String.format("%.2f", nmfkimg) ) );
        map.put("NMSJ",Double.valueOf(String.format("%.2f", nmsj) ) );
        map.put("XSZGSFKIMG",Double.valueOf(String.format("%.2f", xszgsfkimg) ));
        map.put("XSZGSSJ",Double.valueOf(String.format("%.2f", xszgssj) ));
        map.put("ZGSFKIMG",Double.valueOf(String.format("%.2f", zgsfkimg) ) );
        map.put("ZGSSJ",Double.valueOf(String.format("%.2f", zgssj) ));
        map.put("CKFKIMG",Double.valueOf(String.format("%.2f", ckfkimg) ) );
        map.put("CKSJ",Double.valueOf(String.format("%.2f", cksj) ) );
        thelist.add(map);

        List<Map<String,Object>> newlist = xiaojiAndAll(list);
        for (int i = 0;i<newlist.size();i++){
            thelist.add(newlist.get(i));
        }
        return  thelist;
    }

    public  List<Map<String,Object>> xiaojiAndAll (List<Map<String,Object>> list){
        List<Map<String,Object>> xiaoj = new ArrayList<>();
        List pz = new ArrayList<>();
        pz.add("热板");
        pz.add("酸洗");
        pz.add("冷板");
        pz.add("镀锌");
        pz.add("中厚板");
        pz.add("螺纹钢");
        pz.add("圆钢");
        pz.add("线材");
        pz.add("型材");
        pz.add("薄板");
        for(int j=0;j<pz.size();j++){
            Map map = new HashMap();
            List<Map<String,Object>> thelist=new ArrayList<>();
            String zl ="";
            String cxname = "小计";
            double fkimg = 0.00;
            double kzwi6 = 0.00;
            double zsj = 0;
            double nmfkimg = 0.00;
            double nmkzwi6 = 0.00;
            double nmsj = 0;
            double xszgsfkimg = 0.00;
            double xszgskzwi6 = 0.00;
            double xszgssj = 0;
            double zgsfkimg = 0.00;
            double zgskzwi6 = 0.00;
            double zgssj = 0;
            double ckfkimg = 0.00;
            double ckkzwi6 = 0.00;
            double cksj = 0;
            for (int i = 0; i < list.size(); i++) {
                if (pz.get(j).equals(list.get(i).get("ZL").toString())) {
                    thelist.add(list.get(i));
                    zl = list.get(i).get("ZL").toString();
                    if (!list.get(i).get("FKIMG").toString().equals("0")) {
                        fkimg = fkimg + Double.parseDouble(list.get(i).get("FKIMG").toString());
                    } else {
                        fkimg = fkimg + 0;
                    }
                    if (!list.get(i).get("KZWI6").toString().equals("0")) {
                        kzwi6 = kzwi6 + Double.parseDouble(list.get(i).get("KZWI6").toString());
                    } else {
                        kzwi6 = kzwi6 + 0;
                    }

                    if (!list.get(i).get("NMFKIMG").toString().equals("0")) {
                        nmfkimg = nmfkimg + Double.parseDouble(list.get(i).get("NMFKIMG").toString());
                    } else {
                        nmfkimg = nmfkimg + 0;
                    }
                    if (!list.get(i).get("NMKZWI6").toString().equals("0")) {
                        nmkzwi6 = nmkzwi6 + Double.parseDouble(list.get(i).get("NMKZWI6").toString());
                    } else {
                        nmkzwi6 = nmkzwi6 + 0;
                    }
                    if (!list.get(i).get("XSZGSFKIMG").toString().equals("0")) {
                        xszgsfkimg = xszgsfkimg + Double.parseDouble(list.get(i).get("XSZGSFKIMG").toString());
                    } else {
                        xszgsfkimg = xszgsfkimg + 0;
                    }
                    if (!list.get(i).get("XSZGSKZWI6").toString().equals("0")) {
                        xszgskzwi6 = xszgskzwi6 + Double.parseDouble(list.get(i).get("XSZGSKZWI6").toString());
                    } else {
                        xszgskzwi6 = xszgskzwi6 + 0;
                    }
                    if (!list.get(i).get("ZGSFKIMG").toString().equals("0")) {
                        zgsfkimg = zgsfkimg + Double.parseDouble(list.get(i).get("ZGSFKIMG").toString());
                    } else {
                        zgsfkimg = zgsfkimg + 0;
                    }
                    if (!list.get(i).get("ZGSKZWI6").toString().equals("0")) {
                        zgskzwi6 = zgskzwi6 + Double.parseDouble(list.get(i).get("ZGSKZWI6").toString());
                    } else {
                        zgskzwi6 = zgskzwi6 + 0;
                    }
                    if (!list.get(i).get("CKFKIMG").toString().equals("0")) {
                        ckfkimg = ckfkimg + Double.parseDouble(list.get(i).get("CKFKIMG").toString());
                    } else {
                        ckfkimg = ckfkimg + 0;
                    }
                    if (!list.get(i).get("CKKZWI6").toString().equals("0")) {
                        ckkzwi6 = ckkzwi6 + Double.parseDouble(list.get(i).get("CKKZWI6").toString());
                    } else {
                        ckkzwi6 = ckkzwi6 + 0;
                    }
                }

                if (kzwi6 / fkimg > 0) {
                    zsj = kzwi6 * 10000 / fkimg;
                } else {
                    zsj = 0;
                }
                if (nmkzwi6 / nmfkimg > 0) {
                    nmsj = nmkzwi6 / nmfkimg;
                } else {
                    nmsj = 0;
                }
                if (xszgskzwi6 / xszgsfkimg > 0) {
                    xszgssj = xszgskzwi6 / xszgsfkimg;
                } else {
                    xszgssj = 0;
                }
                if (zgskzwi6 / zgsfkimg > 0) {
                    zgssj = zgskzwi6 / zgsfkimg;
                } else {
                    zgssj = 0;
                }
                if (ckkzwi6 / ckfkimg > 0) {
                    cksj = ckkzwi6 / ckfkimg;
                } else {
                    cksj = 0;
                }
                map.put("ZL", zl);
                map.put("CXNAME", zl + cxname);
                map.put("FKIMG", Double.valueOf(String.format("%.2f", fkimg)));
                map.put("ZSJ", Double.valueOf(String.format("%.2f", zsj)));
                map.put("KZWI6", Double.valueOf(String.format("%.2f", kzwi6)));
                map.put("NMFKIMG", Double.valueOf(String.format("%.2f", nmfkimg)));
                map.put("NMSJ", Double.valueOf(String.format("%.2f", nmsj)));
                map.put("XSZGSFKIMG", Double.valueOf(String.format("%.2f", xszgsfkimg)));
                map.put("XSZGSSJ", Double.valueOf(String.format("%.2f", xszgssj)));
                map.put("ZGSFKIMG", Double.valueOf(String.format("%.2f", zgsfkimg)));
                map.put("ZGSSJ", Double.valueOf(String.format("%.2f", zgssj)));
                map.put("CKFKIMG", Double.valueOf(String.format("%.2f", ckfkimg)));
                map.put("CKSJ", Double.valueOf(String.format("%.2f", cksj)));
            }
            xiaoj.add(map);
            if(thelist.size()>1) {
                for (int i = 0; i < thelist.size(); i++) {
                    xiaoj.add(thelist.get(i));
                }
            }
        }


        return  xiaoj;
    }


    public  List<Map<String,Object>> xiaoji (List<Map<String,Object>> list){
        List<Map<String,Object>> xiaoj = new ArrayList<>();

        List pz = new ArrayList<>();
        pz.add("热板");
        pz.add("酸洗");
        pz.add("冷板");
        pz.add("镀锌");
        pz.add("中厚板");
        pz.add("螺纹钢");
        pz.add("圆钢");
        pz.add("线材");
        pz.add("型材");
        for(int j=0;j<pz.size();j++){
            Map map = new HashMap();
            String zl ="";

            String cxname = "小计";

            double fkimg = 0.00;
            double kzwi6 = 0.00;
            double zsj = 0;
            double nmfkimg = 0.00;
            double nmkzwi6 = 0.00;
            double nmsj = 0;
            double xszgsfkimg = 0.00;
            double xszgskzwi6 = 0.00;
            double xszgssj = 0;
            double zyfkimg = 0.00;
            double zykzwi6 = 0.00;
            double zysj = 0;
            double fgsfkimg = 0.00;
            double fgskzwi6 = 0.00;
            double fgssj = 0;
            double zgsfkimg = 0.00;
            double zgskzwi6 = 0.00;
            double zgssj = 0;
            double xhfkimg = 0.00;
            double xhkzwi6 = 0.00;
            double xhsj = 0;
            double sybfkimg = 0.00;
            double sybkzwi6 = 0.00;
            double sybsj = 0;
            double zbgsfkimg = 0.00;
            double zbgskzwi6 = 0.00;
            double zbgssj = 0;
            double ckfkimg = 0.00;
            double ckkzwi6 = 0.00;
            double cksj = 0;
            for (int i = 0; i < list.size(); i++) {
                if (pz.get(j).equals(list.get(i).get("ZL").toString())) {

                    zl = list.get(i).get("ZL").toString();
                    if (!list.get(i).get("FKIMG").toString().equals("0")) {
                        fkimg = fkimg + Double.parseDouble(list.get(i).get("FKIMG").toString());
                    } else {
                        fkimg = fkimg + 0;
                    }
                    if (!list.get(i).get("KZWI6").toString().equals("0")) {
                        kzwi6 = kzwi6 + Double.parseDouble(list.get(i).get("KZWI6").toString());
                    } else {
                        kzwi6 = kzwi6 + 0;
                    }

                    if (!list.get(i).get("NMFKIMG").toString().equals("0")) {
                        nmfkimg = nmfkimg + Double.parseDouble(list.get(i).get("NMFKIMG").toString());
                    } else {
                        nmfkimg = nmfkimg + 0;
                    }
                    if (!list.get(i).get("NMKZWI6").toString().equals("0")) {
                        nmkzwi6 = nmkzwi6 + Double.parseDouble(list.get(i).get("NMKZWI6").toString());
                    } else {
                        nmkzwi6 = nmkzwi6 + 0;
                    }
                    if (!list.get(i).get("XSZGSFKIMG").toString().equals("0")) {
                        xszgsfkimg = xszgsfkimg + Double.parseDouble(list.get(i).get("XSZGSFKIMG").toString());
                    } else {
                        xszgsfkimg = xszgsfkimg + 0;
                    }
                    if (!list.get(i).get("XSZGSKZWI6").toString().equals("0")) {
                        xszgskzwi6 = xszgskzwi6 + Double.parseDouble(list.get(i).get("XSZGSKZWI6").toString());
                    } else {
                        xszgskzwi6 = xszgskzwi6 + 0;
                    }
                    if (!list.get(i).get("ZYFKIMG").toString().equals("0")) {
                        zyfkimg = zyfkimg + Double.parseDouble(list.get(i).get("ZYFKIMG").toString());
                    } else {
                        zyfkimg = zyfkimg + 0;
                    }
                    if (!list.get(i).get("FGSFKIMG").toString().equals("0")) {
                        fgsfkimg = fgsfkimg + Double.parseDouble(list.get(i).get("FGSFKIMG").toString());
                    } else {
                        fgsfkimg = fgsfkimg + 0;
                    }

                    if (!list.get(i).get("FGSKZWI6").toString().equals("0")) {
                        fgskzwi6 = fgskzwi6 + Double.parseDouble(list.get(i).get("FGSKZWI6").toString());
                    } else {
                        fgskzwi6 = fgskzwi6 + 0;
                    }
                    if (!list.get(i).get("ZGSFKIMG").toString().equals("0")) {
                        zgsfkimg = zgsfkimg + Double.parseDouble(list.get(i).get("ZGSFKIMG").toString());
                    } else {
                        zgsfkimg = zgsfkimg + 0;
                    }

                    if (!list.get(i).get("ZGSKZWI6").toString().equals("0")) {
                        zgskzwi6 = zgskzwi6 + Double.parseDouble(list.get(i).get("ZGSKZWI6").toString());
                    } else {
                        zgskzwi6 = zgskzwi6 + 0;
                    }

                    if (!list.get(i).get("XHFKIMG").toString().equals("0")) {
                        xhfkimg = xhfkimg + Double.parseDouble(list.get(i).get("XHFKIMG").toString());
                    } else {
                        xhfkimg = xhfkimg + 0;
                    }
                    if (!list.get(i).get("XHKZWI6").toString().equals("0")) {
                        xhkzwi6 = xhkzwi6 + Double.parseDouble(list.get(i).get("XHKZWI6").toString());
                    } else {
                        xhkzwi6 = xhkzwi6 + 0;
                    }
                    if (!list.get(i).get("ZBGSFKIMG").toString().equals("0")) {
                        zbgsfkimg = zbgsfkimg + Double.parseDouble(list.get(i).get("ZBGSFKIMG").toString());
                    } else {
                        zbgsfkimg = zbgsfkimg + 0;
                    }

                    if (!list.get(i).get("ZBGSKZWI6").toString().equals("0")) {
                        zbgskzwi6 = zbgskzwi6 + Double.parseDouble(list.get(i).get("ZBGSKZWI6").toString());
                    } else {
                        zbgskzwi6 = zbgskzwi6 + 0;
                    }
                    if (!list.get(i).get("CKFKIMG").toString().equals("0")) {
                        ckfkimg = ckfkimg + Double.parseDouble(list.get(i).get("CKFKIMG").toString());
                    } else {
                        ckfkimg = ckfkimg + 0;
                    }
                    if (!list.get(i).get("CKKZWI6").toString().equals("0")) {
                        ckkzwi6 = ckkzwi6 + Double.parseDouble(list.get(i).get("CKKZWI6").toString());
                    } else {
                        ckkzwi6 = ckkzwi6 + 0;
                    }

                    if (!list.get(i).get("SYBFKIMG").toString().equals("0")) {
                        sybfkimg = sybfkimg + Double.parseDouble(list.get(i).get("SYBFKIMG").toString());
                    } else {
                        sybfkimg = sybfkimg + 0;
                    }

                    if (!list.get(i).get("SYBKZWI6").toString().equals("0")) {
                        sybkzwi6 = sybkzwi6 + Double.parseDouble(list.get(i).get("SYBKZWI6").toString());
                    } else {
                        sybkzwi6 = sybkzwi6 + 0;
                    }
                }
                if (kzwi6 / fkimg > 0) {
                    zsj = kzwi6 * 10000 / fkimg;
                } else {
                    zsj = 0;
                }
                if (nmkzwi6 / nmfkimg > 0) {
                    nmsj = nmkzwi6 / nmfkimg;
                } else {
                    nmsj = 0;
                }
                if (xszgskzwi6 / xszgsfkimg > 0) {
                    xszgssj = xszgskzwi6 / xszgsfkimg;
                } else {
                    xszgssj = 0;
                }
                if (zykzwi6 / zyfkimg > 0) {
                    zysj = zykzwi6 / zyfkimg;
                } else {
                    zysj = 0;
                }

                if (fgskzwi6 / fgsfkimg > 0) {
                    fgssj = fgskzwi6 / fgsfkimg;
                } else {
                    fgssj = 0;
                }
                if (zgskzwi6 / zgsfkimg > 0) {
                    zgssj = zgskzwi6 / zgsfkimg;
                } else {
                    zgssj = 0;
                }
                if (xhkzwi6 / xhfkimg > 0) {
                    xhsj = xhkzwi6 / xhfkimg;
                } else {
                    xhsj = 0;
                }

                if (zbgskzwi6 / zbgsfkimg > 0) {
                    zbgssj = zbgskzwi6 / zbgsfkimg;
                } else {
                    zbgssj = 0;
                }
                if (ckkzwi6 / ckfkimg > 0) {
                    cksj = ckkzwi6 / ckfkimg;
                } else {
                    cksj = 0;
                }
                if (sybfkimg / sybfkimg > 0) {
                    sybsj = sybkzwi6 / sybfkimg;
                } else {
                    sybsj = 0;
                }
                map.put("ZL", zl);
                map.put("CXNAME", zl + cxname);
                map.put("FKIMG",Double.valueOf(String.format("%.2f", fkimg) ));
                map.put("ZSJ", Double.valueOf(String.format("%.2f", zsj) ));
                map.put("KZWI6",Double.valueOf(String.format("%.2f", kzwi6) ) );
                map.put("NMFKIMG",Double.valueOf(String.format("%.2f", nmfkimg) ) );
                map.put("NMSJ",Double.valueOf(String.format("%.2f", nmsj) ) );
                map.put("XSZGSFKIMG",Double.valueOf(String.format("%.2f", xszgsfkimg) ) );
                map.put("XSZGSSJ",Double.valueOf(String.format("%.2f", xszgssj) ) );
                map.put("ZYFKIMG",Double.valueOf(String.format("%.2f", zyfkimg) ) );
                map.put("ZYSJ",Double.valueOf(String.format("%.2f", zysj) ));
                map.put("FGSFKIMG",Double.valueOf(String.format("%.2f", fgsfkimg) ) );
                map.put("FGSSJ",Double.valueOf(String.format("%.2f", fgssj) ) );

                map.put("ZGSFKIMG",Double.valueOf(String.format("%.2f", zgsfkimg) ) );
                map.put("ZGSSJ", Double.valueOf(String.format("%.2f", zgssj) )  );
                map.put("SYBFKIMG",Double.valueOf(String.format("%.2f", sybfkimg) ));
                map.put("SYBSJ",Double.valueOf(String.format("%.2f", sybsj) ) );
                map.put("XHFKIMG",Double.valueOf(String.format("%.2f", xhfkimg) ) );
                map.put("XHSJ",Double.valueOf(String.format("%.2f", xhsj) ) );
                map.put("ZBGSFKIMG",Double.valueOf(String.format("%.2f", zbgsfkimg) ));
                map.put("ZBGSSJ",Double.valueOf(String.format("%.2f", zbgssj) ));
                map.put("CKFKIMG",Double.valueOf(String.format("%.2f", ckfkimg) ));
                map.put("CKSJ",Double.valueOf(String.format("%.2f", cksj) ));

            }
            xiaoj.add(map);
        }


        return  xiaoj;
    }

}
