package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.hbis.erp.modular.system.entity.Settings;
import cn.hbis.erp.modular.system.mapper.SettingsMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SettingService extends ServiceImpl<SettingsMapper, Settings> {
    @Resource
    private  SettingsMapper settingsMapper;

    public boolean AdddORupdate(String id,String menuid){
        boolean flag=false;
        if(id!=null&&!id.equals("")){
            Settings set = settingsMapper.selectById(id);
            set.setUploadTime(new Date());
            set.setMenuid(menuid);
            set.setCreateuserid(ShiroKit.getUser().getId());
           int num = settingsMapper.updateById(set);
            if(num>0){
                return  true;
            }

        }else{
            Settings set = new Settings();
            set.setMenuid(menuid);
            set.setUploadTime(new Date());
            set.setCreateuserid(ShiroKit.getUser().getId());
            int   num = settingsMapper.insert(set);
            if (num>0){
                return true;
            }

        }
        return  flag;
    }

    public boolean delSetting(String id){
        boolean flag =false;
        int num =settingsMapper.deleteById(id);
        if (num>0){
            return true;
        }
        return  flag;
    }
    public Page<Map<String, Object>> selSetting(String menuname){
       Page page = PageFactory.defaultPage();
       return this.settingsMapper.selthesets(page,menuname);
   }

}
