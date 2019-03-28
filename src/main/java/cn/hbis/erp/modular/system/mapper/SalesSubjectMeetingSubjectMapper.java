package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.SalesSubjectMeetingSubjectControl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 销售主体例会主体对照 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface SalesSubjectMeetingSubjectMapper extends BaseMapper<SalesSubjectMeetingSubjectControl> {

    Page<Map<String, Object>> List(@Param("page") Page page, @Param("companyId") String companyId);

    SalesSubjectMeetingSubjectControl select(@Param("Id") Long Id);
}
