package cn.hbis.erp.modular.system.service;


import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.util.DateUtil;
import cn.hbis.erp.modular.system.entity.AccountabilityUnitManage;
import cn.hbis.erp.modular.system.mapper.AccountabilityUnitManageMapper;
import cn.hbis.erp.modular.system.mapper.TargetQuantityManagementMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 责任公司管理表 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class AccountabilityUnitManageService extends ServiceImpl<AccountabilityUnitManageMapper, AccountabilityUnitManage> {

        @Resource
        private  AccountabilityUnitManageMapper accounUnitManageMapper;

        @Resource
        private TargetQuantityManagementMapper taManagementMapper;

        /**
         * 责任公司管理的列表
         * @param accountname
         * @return
         */
        @Async
        public Page<Map<String, Object>> selectAccountManeg(String accountname){
                Page page = PageFactory.defaultPage();
                return this.accounUnitManageMapper.selectaccunitname(page,accountname);
            }

        /**
         * 责任公司添加和更新
          * @param id
         * @param name
         * @return
         */
        @Transactional(rollbackFor = Exception.class)
        public boolean AddORUpdate(String id,String name){
            boolean flag = false;
            if (ToolUtil.isNotEmpty(name)){
                List<Map> list = taManagementMapper.selectscaaccnuitList();
                for(int i=0;i<list.size();i++){
                    String names = list.get(i).get("NAME").toString();
                    if(name.equals(names)) {
                        throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
                    }
                }
            }
            if(id != null && !id.equals("")){

                AccountabilityUnitManage accounManage = accounUnitManageMapper.selectById(id);

                accounManage.setAccountabilityunitname(name);
                int sun = accounUnitManageMapper.updateById(accounManage);
                if(sun  ==1){
                    flag= true;
                    return flag;
                }else{
                    return flag;
                }

            }else{
                AccountabilityUnitManage accounManage = new AccountabilityUnitManage();
                //生成uuid的hashCode值
                int hashCode = UUID.randomUUID().toString().hashCode();
                if(hashCode<0){
                    hashCode=-hashCode;
                }
                accounManage.setAccountabilityunitcode(String.valueOf(hashCode).substring(0,6));
               /* String code = String.valueOf((int)((Math.random()*9+1)*100000));
                accounManage.setAccountabilityunitcode(code);*/
                if(!name.equals("")&&name!=null){
                    accounManage.setAccountabilityunitname(name);
                }
                accounManage.setDeletestatus("0");
                int sun  = accounUnitManageMapper.insert(accounManage);
                if(sun  ==1){
                    flag= true;
                    return flag;
                }}
            return flag;
        }

        /**
         * 责任公司管理的逻辑删除
         * @param id
         * @return
         */
        @Transactional(rollbackFor = Exception.class)
        public boolean delete(String id ){
                boolean flag = false;
                if(id != null && !id.equals("")){
                    AccountabilityUnitManage accounManage = accounUnitManageMapper.selectById(id);
                    accounManage.setDeletestatus("1");
                    int sun = accounUnitManageMapper.updateById(accounManage);
                    if(sun  ==1){
                        flag= true;
                        return flag;
                    }

                }
            return flag;
        }

    /**
     * 销售额钢厂导出
     * @param companyName
     * @param jd
     * @param startTime
     * @param endTime
     * @return
     */
        @Async
        public  List<Map<String ,Object>> xsegc ( String companyName, String jd, String startTime,String endTime){
            String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
            String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
            List<Map<String ,Object>> list = accounUnitManageMapper.xsegcexcel( companyName,jd, startTime1, endTime1);
            return  list;
        }
    /**
     * 销售额钢厂导出
     *
     * @param jd
     * @param startTime
     * @param endTime
     * @return
     */
    @Async
    public  List<Map<String ,Object>> xsecx ( String pz,List cx,String jd, String startTime,String endTime){
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String ,Object>> list = accounUnitManageMapper.xsecxexcel( pz,cx,jd, startTime1, endTime1);
        return  list;
    }

    /**
     * 集团销售结算明细导出
     *
     * @param jd
     * @param startTime
     * @param endTime
     * @return
     */
    @Async
    public  List<Map<String ,Object>> jtxsecx ( String pzg,String pz,List cx,String jd, String startTime,String endTime){
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String ,Object>> list = accounUnitManageMapper.jtxsjsmxcxexcel( pz,cx,jd, startTime1, endTime1,pzg);
        return  list;
    }


    @Async
    public  List<Map<String ,Object>> xsepz ( String jd,String pz, String startTime,String endTime){
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String ,Object>> list = accounUnitManageMapper.xsepzexcel(jd,pz,startTime1+" 00:00:00",endTime1+" 23:59:59");
        return  list;
    }

    @Async
    public  List<Map<String ,Object>> xsecxfb ( List<String> cx,String zl, String startTime,String endTime){
        String  startTime1=(String) DateUtil.getFirstDayOfMonth(startTime);
        String  endTime1=(String)DateUtil.getLastDayOfMonth(endTime);
        List<Map<String ,Object>> list = accounUnitManageMapper.xsecxfbexcel(cx,zl,startTime1+" 00:00:00",endTime1+" 23:59:59");
        return  list;
    }
}
