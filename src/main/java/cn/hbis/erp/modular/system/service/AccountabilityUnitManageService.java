package cn.hbis.erp.modular.system.service;


import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.AccountabilityUnitManage;
import cn.hbis.erp.modular.system.mapper.AccountabilityUnitManageMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        /**
         * 责任公司管理的列表
         * @param accountname
         * @return
         */

        public Page<Map<String, Object>> selectAccountManeg(String accountname){
                Page page = PageFactory.defaultPage();
                return this.accounUnitManageMapper.selectaccunitname(page,accountname);
            }

        /**
         * 责任公司添加和更新
          * @param id
         * @param code
         * @param name
         * @return
         */
        @Transactional(rollbackFor = Exception.class)
        public boolean AddORUpdate(String id,String code,String name){
            boolean flag = false;
            if(id != null && !id.equals("")){
                AccountabilityUnitManage accounManage = accounUnitManageMapper.selectById(id);
                if(!code.equals("")&&code==null){
                    accounManage.setAccountabilityunitcode(code);
                }
                if(!name.equals("")&&name==null){
                    accounManage.setAccountabilityunitname(name);
                }

                int sun = accounUnitManageMapper.updateById(accounManage);
                if(sun  ==1){
                     flag= true;
                    return flag;
                }else{
                    return flag;
                }

            }else{
            AccountabilityUnitManage accounManage = new AccountabilityUnitManage();
            if(!code.equals("")&&code!=null){
                accounManage.setAccountabilityunitcode(code);
            }
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
        public AccountabilityUnitManage getOne(String id){

                AccountabilityUnitManage   am = accounUnitManageMapper.selectById(id);
                return am;
        }
}
