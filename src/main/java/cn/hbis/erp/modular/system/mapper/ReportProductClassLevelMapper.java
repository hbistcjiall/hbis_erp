package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ReportProductClassLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportProductClassLevelMapper extends BaseMapper<ReportProductClassLevel> {

    List<Map> getcxfb(@Param("startTime")String startTime,@Param("endTime")String endTime);

}
