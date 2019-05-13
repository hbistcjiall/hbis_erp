package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.service.ScmSteelSettleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: 马琳
 * @date: 2019/5/13 10:34
 */
@RestController
@RequestMapping("/exportcx")
public class ExportXSE {
    @Autowired
    private ScmSteelSettleService scmSteelSettleService;

    @ApiOperation(value = "销售额产线导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime", value = "起始日期", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "终止日期", dataType = "String"),
            @ApiImplicitParam(name = "jd", value = "借贷", dataType = "String")

    })
    @PostMapping("/xsjswccxexport")
    @Async
    public List<Map> xsjswccxexport(String startTime, String endTime, String pz, @RequestParam(required = false) List<String> cx, String jd) {
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map> list = new ArrayList<>();
        list = scmSteelSettleService.getxsjswccx(startTime1+" 00:00:00",endTime1+" 23:59:59",pz,cx,jd);
        List<Map>  thelist = new ArrayList<>();
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
        map.put("zl",zl);
        map.put("cxname",cxname);
        map.put("fkimg",fkimg);
        map.put("zsj",zsj);
        map.put("kzwi6",kzwi6);
        map.put("nmfkimg",nmfkimg);
        map.put("nmsj",nmsj);
        map.put("xszgsfkimg",xszgsfkimg);
        map.put("xszgssj",xszgssj);
        map.put("zyfkimg",zyfkimg);
        map.put("zysj",zysj);
        map.put("fgsfkimg",fgsfkimg);
        map.put("fgssj",fgssj);

        map.put("zgsfkimg",zgsfkimg);
        map.put("zgssj",zgssj);
        map.put("sybfkimg",sybfkimg);
        map.put("sybsj",sybsj);
        map.put("xhfkimg",xhfkimg);
        map.put("xhsj",xhsj);
        map.put("zbgsfkimg",zbgsfkimg);
        map.put("zbgssj",zbgssj);
        map.put("ckfkimg",ckfkimg);
        map.put("cksj",cksj);
        thelist.add(map);
        for (int i = 0;i<list.size();i++){
            thelist.add(list.get(i));
        }
        List<Map> newlist = xiaoji(list);
        for (int i = 0;i<newlist.size();i++){
            thelist.add(newlist.get(i));
        }
        return  thelist;
    }
    public  List<Map> xiaoji (List<Map> list){
                List<Map> xiaoj = new ArrayList<>();

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
                            map.put("zl", zl);
                            map.put("cxname", zl + cxname);
                            map.put("fkimg", fkimg);
                            map.put("zsj", zsj);
                            map.put("kzwi6", kzwi6);
                            map.put("nmfkimg", nmfkimg);
                            map.put("nmsj", nmsj);
                            map.put("xszgsfkimg", xszgsfkimg);
                            map.put("xszgssj", xszgssj);
                            map.put("zyfkimg", zyfkimg);
                            map.put("zysj", zysj);
                            map.put("fgsfkimg", fgsfkimg);
                            map.put("fgssj", fgssj);

                            map.put("zgsfkimg", zgsfkimg);
                            map.put("zgssj", zgssj);
                            map.put("sybfkimg", sybfkimg);
                            map.put("sybsj", sybsj);
                            map.put("xhfkimg", xhfkimg);
                            map.put("xhsj", xhsj);
                            map.put("zbgsfkimg", zbgsfkimg);
                            map.put("zbgssj", zbgssj);
                            map.put("ckfkimg", ckfkimg);
                            map.put("cksj", cksj);

                        }
                    xiaoj.add(map);
                    }


        return  xiaoj;
    }
}
