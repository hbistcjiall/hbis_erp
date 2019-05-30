package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ReportProductClassLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportProductClassLevelMapper extends BaseMapper<ReportProductClassLevel> {

    List<Map> getcxfb(@Param("cx")List cx,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("zl")String zl);
    List<Map<String,Object>> getcxfb01(@Param("cx")List cx,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("zl")String zl);
    List<Map> getcxzl(@Param("cx")List cx,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("zl")String zl);
    List<Map> getcxzl01(@Param("cx")List cx,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("zl")String zl);
    List<Map> getcxzl02(@Param("cx")List cx,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("zl")String zl);

    List cxexcel(@Param("dw") String dw,@Param("cx") List<String> cx,@Param("startTime")String startTime,@Param("endTime")String endTime);
    List pzexcel (@Param("pz") String pz,@Param("startTime")String startTime,@Param("endTime")String endTime);

    List jtgczybgqk(@Param("pz") String pz,@Param("name") String name,@Param("htdw") String htdw,@Param("startTime")String startTime,@Param("endTime")String endTime);
    List jtgczybgqkpz(@Param("name") String name,@Param("startTime")String startTime,@Param("endTime")String endTime);
}
