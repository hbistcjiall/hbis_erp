package cn.hbis.erp.modular.system.mapper;

import cn.hbis.erp.modular.system.entity.ScmSteelSettle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 * 结算清单信息接口 Mapper 接口
 * </p>
 *
 * @author yaojiaqi
 * @since 2019-03-16
 */
public interface ScmSteelSettleMapper extends BaseMapper<ScmSteelSettle> {
    List<ScmSteelSettle> getcx(@Param("dw") String dw,@Param("cx") String cx,@Param("startTime") String startTime,@Param("endTime")String endTime,@Param("startagainTime") String startagainTime,@Param("endagainTime")String endagainTime);
    List<ScmSteelSettle> getpz(@Param("pz") String pz,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("startagainTime") String startagainTime,@Param("endagainTime")String endagainTime);
    List<ScmSteelSettle> getzrbm(@Param("zrbm") String zrbm,@Param("startTime") String startTime,@Param("endTime") String endTime);
    List<ScmSteelSettle> getpzjszl(@Param("startTime") String startTime,@Param("endTime") String endTime);
    List<ScmSteelSettle> getpzgjswc(@Param("startTime") String startTime,@Param("endTime") String endTime);
    List<ScmSteelSettle> getzgsjswc(@Param("startTime") String startTime,@Param("endTime") String endTime);
    List<ScmSteelSettle> getzyjh(@Param("nf") String nf,@Param("yf") String yf,@Param("pz") String pz,@Param("cx") String cx,@Param("xszt") String xszt);
    List<ScmSteelSettle> getzyjhcxtjpz();
    List<ScmSteelSettle> getzyjhcxtjcx(@Param("pz") String  pz);
    List<ScmSteelSettle> getzyjhcxtjxszt();
    List<ScmSteelSettle> getcxhtjd(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("year") String year,@Param("cxName") String cxName);
    List<ScmSteelSettle> getpzhtjd(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("year") String year,@Param("pzName") String cxName);

}
