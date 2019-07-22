package cn.hbis.erp.modular.system.service;


import cn.hbis.erp.modular.system.mapper.deliveryDetailMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 *
 *
 */
@Service
public class BasicDataExportService  {

        @Resource
        private deliveryDetailMapper ddMapper;

    /**
     * 发货数据导出
     *
     * @return
     */
    @Async
    public   List<Map<String, Object>> fhsj(String companyName, String variety, String actualStartTime, String actualEndTime, String delivNumb, String delivItem, String ModifyStartTime, String ModifyEndTime){
//        String  startTime1=(String) DateUtil.getFirstDayOfMonth(actualStartTime);
////        String  endTime1=(String)DateUtil.getLastDayOfMonth(actualEndTime);
////        String  startTime2=(String) DateUtil.getFirstDayOfMonth(ModifyStartTime);
////        String  endTime2=(String)DateUtil.getLastDayOfMonth(ModifyEndTime);
        Map<String,String> map=new HashMap();
        map.put("唐钢","9580");
        map.put("邯钢","9727");
        map.put("宣钢","9193");
        map.put("承钢","9196");
        map.put("邯宝","7778");
        map.put("舞钢","1932");
        map.put("石钢","8110");
        map.put("衡板","8493");
        String companyId=map.get(companyName);
        List<Map<String, Object>> list = ddMapper.fhsjexcel(companyId,variety,actualStartTime,actualEndTime,delivNumb,delivItem,ModifyStartTime,ModifyEndTime);
        return  list;
    }

}
