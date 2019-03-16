package cn.hbis.erp.modular.system.mapper;


import cn.hbis.erp.modular.system.entity.TargetQuantityManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TargetQuantityManagementMapper extends BaseMapper<TargetQuantityManagement> {



    Page<Map<String, Object>> selTargetManagement(@Param("page") Page page, @Param("targetname") String targetname, @Param("year") String year);

}
