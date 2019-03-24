package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.Allocation;
import cn.hbis.erp.modular.system.entity.CrmResourceAllocation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源分配维护表 Mapper 接口
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-21
 */
public interface CrmResourceAllocationMapper extends BaseMapper<CrmResourceAllocation> {
     List<Allocation> selSchedule(@Param("date") String date, @Param("month") String month, @Param("year") String year,@Param("flName")String flName);

     List<Allocation> selScheduleByCx(@Param("date") String date, @Param("month") String month, @Param("year") String year,@Param("sort")String sort,@Param("flName")String flName);
}
