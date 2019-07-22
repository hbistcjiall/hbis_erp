package cn.hbis.erp.modular.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发货数据 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface deliveryDetailMapper extends BaseMapper {


     List<Map<String, Object>> fhsjexcel(@Param("companyId") String companyId, @Param("variety") String variety, @Param("startTime1") String startTime1, @Param("endTime1") String endTime1,@Param("delivNumb") String delivNumb,@Param("delivItem") String delivItem,@Param("startTime2") String startTime2,@Param("endTime2") String endTime2);

}
