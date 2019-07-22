package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 结算清单信息接口 Mapper 接口
 * </p>
 *
 * @author yaojiaqi
 * @since 2019-03-16
 */
public interface ScmSteelSettleMapper extends BaseMapper<ScmSteelSettle> {
    List<ScmSteelSettle> getcx(@Param("dw") String dw,@Param("cx") List cx,@Param("startTime") String startTime,@Param("endTime")String endTime,@Param("zt")String zt);
    List<ScmSteelSettle> getpz(@Param("pz") String pz,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("zt")String zt);
    List<ScmSteelSettle> getzrbm(@Param("zrbm") String zrbm,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("zt")String zt);
    List<ScmSteelSettle> getpzjszl(@Param("startTime") String startTime,@Param("endTime") String endTime);
    List<ScmSteelSettle> getpzgjswc(@Param("startTime") String startTime,@Param("endTime") String endTime);
    List<ScmSteelSettle> getzgsjswc(@Param("startTime") String startTime,@Param("endTime") String endTime);
    List<ScmSteelSettle> getzyjh(@Param("nf") String nf,@Param("yf") String yf,@Param("pz") String pz,@Param("cx") List cx,@Param("xszt") String xszt);
    List<ScmSteelSettle> getzyjhcxtjpz();
    List<ScmSteelSettle> getzyjhcxtjcx(@Param("pz") String  pz);
    List<ScmSteelSettle> getzyjhcxtjxszt();
    List<ScmSteelSettle> getcxhtjd(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("year") String year,@Param("cxName") List cxName,@Param("companyId") String companyId,@Param("month") String month);
    List<ScmSteelSettle> getpzhtjd(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("year") String year,@Param("pzName") String pzName,@Param("month") String month);
    List<ScmSteelSettle> selCx(@Param("companyId") String companyId);
    List<ScmSteelSettle> getCxNamePzg(@Param("companyId") String  companyId,@Param("type") String  type);
    List<ScmSteelSettle> getyxybpz(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("pz") String pz,@Param("jd") String jd);
    List<ScmSteelSettle> getyxybgc(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("gc") String gc,@Param("jd") String jd);
    List<Map> getxsjswc(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("pz") String pz,@Param("cx") List cx,@Param("jd") String jd);
    List<Map<String,Object>>getxsjswcs(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("pz") String pz,@Param("cx") List cx,@Param("jd") String jd);
	
	
    List<ScmSteelSettle> getCxNameN(@Param("companyId") String  companyId,@Param("type") String  type);
    List<Map> getjtxsjscx(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("pz") String pz,@Param("cx") List cx,@Param("jd") String jd,@Param("pzg") String pzg);
    List<Map<String,Object>>getjtxsjswcs(@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("pz") String pz,@Param("cx") List cx,@Param("jd") String jd,@Param("pzg") String pzg);

}
