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
    List<ScmSteelSettle> getcx(@Param("dw") String dw,@Param("cx") String cx);
    List<ScmSteelSettle> getpz(@Param("pz") String pz);
    List<ScmSteelSettle> getndcx(@Param("dw") String dw,@Param("cx") String cx);
    List<ScmSteelSettle> getndpz(@Param("pz") String pz);
    List<ScmSteelSettle> getzrbm(@Param("zrbm") String zrbm);
}
