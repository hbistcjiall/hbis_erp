package cn.hbis.erp.modular.system.mapper;


import cn.hbis.erp.modular.system.entity.TargetQuantityManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TargetQuantityManagementMapper extends BaseMapper<TargetQuantityManagement> {



    Page<Map<String, Object>> selTargetManagement(@Param("page") Page page, @Param("targetname") String targetname, @Param("year") String year);
     List<Map> getlist();
    List<Map> selectscaaccnuitList();
    List<Map>theList();

    List<Map> salesmainsum(@Param("name") String name,@Param("month") String month ,@Param("year") String year );
    List<Map> salesmain(@Param("name") String name,@Param("month") String month,@Param("year") String year);

    List<Map>Steelmillsplan(@Param("name") String name,@Param("month") String month,@Param("year") String year );
    List<Map>Steelmillssum(@Param("name") String name,@Param("month") String month,@Param("year") String year );

    List<Map> typeselect(@Param("name") String name,@Param("month") String month,@Param("year") String year);
    List<Map> typessum(@Param("name") String name,@Param("month") String month,@Param("year") String year);

    List<Map> Steellist (@Param("name") String name,@Param("month") String month,@Param("year") String year);
    List<Map> Steelsum(@Param("name") String name,@Param("month") String month,@Param("year") String year);




}
