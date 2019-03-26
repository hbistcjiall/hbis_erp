package cn.hbis.erp.modular.system.mapper;


import cn.hbis.erp.modular.system.entity.Settings;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SettingsMapper extends BaseMapper<Settings> {

         Page<Map<String, Object>> selthesets(@Param("page")Page page,@Param("menuname") String menuname);
            int del(String id ,String menuid);

}
