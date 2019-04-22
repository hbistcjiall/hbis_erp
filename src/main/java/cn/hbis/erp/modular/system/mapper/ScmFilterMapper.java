package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ScmFilter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiarsi
 * @since 2019-04-18
 */
public interface ScmFilterMapper extends BaseMapper<ScmFilter> {
    List getColumnName(@Param("tableName") String tableName, @Param("columnName") String columnName);

    List getColumnValueOrder(@Param("columnName") String columnName,@Param("columnValue") String columnValue);

    List getColumnValueDetail(@Param("columnName") String columnName,@Param("columnValue") String columnValue);

    List getColumnValueSteel(@Param("columnName") String columnName,@Param("columnValue") String columnValue);

    String getMaxCode();

    Page<Map<String, Object>> selFilterList(@Param("page") Page page,@Param("name") String name, @Param("tableName") String tableName, @Param("column") String column);
}
