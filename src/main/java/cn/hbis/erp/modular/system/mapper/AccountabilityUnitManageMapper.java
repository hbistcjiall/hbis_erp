package cn.hbis.erp.modular.system.mapper;


import cn.hbis.erp.modular.system.entity.AccountabilityUnitManage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 责任管理 Mapper 接口
 * </p>
 *
 *
 *
 */
public interface AccountabilityUnitManageMapper extends BaseMapper<AccountabilityUnitManage> {
   /**
    *  根据责任公司名查询
    * @param accuntname
    * @return
    */

   Page<Map<String, Object>> selectaccunitname(@Param("page") Page page,@Param("accountname") String accountname);
}
