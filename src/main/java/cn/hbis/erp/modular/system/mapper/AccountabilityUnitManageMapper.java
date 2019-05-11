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
   List<Map<String,Object>> xsegcexcel(@Param("companyname") String companyname,@Param("jd") String jd,@Param("startTime")String startTime,@Param("endTime")String endTime);
   List<Map<String,Object>> xsecxexcel(@Param("pz") String pz,@Param("cx") List cx,@Param("jd") String jd,@Param("startTime")String startTime,@Param("endTime")String endTime);
   List<Map<String,Object>> xsepzexcel(@Param("jd") String jd,@Param("pz") String pz,@Param("startTime")String startTime,@Param("endTime")String endTime);
   List<Map<String,Object>> xsecxfbexcel(@Param("cx")List cx,@Param("zl") String zl,@Param("startTime")String startTime,@Param("endTime")String endTime);

}
