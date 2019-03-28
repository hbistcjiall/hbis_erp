package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.OrderTypePriceTypeControl;
import cn.hbis.erp.modular.system.mapper.OrderTypePriceTypeMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 订单类型与价格类型对照表 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class OrderTypePriceTypeService extends ServiceImpl<OrderTypePriceTypeMapper, OrderTypePriceTypeControl> {

    @Resource
    private OrderTypePriceTypeMapper orderTypePriceTypeMapper;

    /**
     * 订单类型与价格类型对照表
     *
     *
     */
    public Page<Map<String, Object>> List(String companyId, String varieties, String orderType, String priceType) {
        Page page = PageFactory.defaultPage();
        return this.baseMapper.List(page, companyId, varieties, orderType, priceType);
    }
    /**
     * 添加销售主体例会主体对照
     *
     *
     */
    public boolean insert(String varieties, String companyId, String orderType, String priceType, String note) {
        OrderTypePriceTypeControl otpt = new OrderTypePriceTypeControl();
        otpt.setVarieties(varieties);
        otpt.setCompanyId(companyId);
        if ("9580".equals(companyId)){
            otpt.setCompanyName("唐钢");
        }else if ("9727".equals(companyId)){
            otpt.setCompanyName("邯钢");
        }else if ("9193".equals(companyId)){
            otpt.setCompanyName("宣钢");
        }else if ("9196".equals(companyId)){
            otpt.setCompanyName("承钢");
        }else if ("1932".equals(companyId)){
            otpt.setCompanyName("舞钢");
        }
        otpt.setOrderType(orderType);
        otpt.setPriceType(priceType);
        otpt.setNote(note);
        otpt.setDeleteState("0");
        int a = orderTypePriceTypeMapper.insert(otpt);
        boolean state = false;
        if (a == 1){
            state = true;
        } else {
            state = false;
        }
        return state;
    }
    /**
     * 修改销售主体例会主体对照
     *
     *
     */
    public boolean update(String orderPriceId, String varieties, String companyId, String orderType, String priceType, String note) {
        OrderTypePriceTypeControl otpt = orderTypePriceTypeMapper.selectById(orderPriceId);
        otpt.setVarieties(varieties);
        otpt.setCompanyId(companyId);
        if ("9580".equals(companyId)){
            otpt.setCompanyName("唐钢");
        }else if ("9727".equals(companyId)){
            otpt.setCompanyName("邯钢");
        }else if ("9193".equals(companyId)){
            otpt.setCompanyName("宣钢");
        }else if ("9196".equals(companyId)){
            otpt.setCompanyName("承钢");
        }else if ("1932".equals(companyId)){
            otpt.setCompanyName("舞钢");
        }
        otpt.setOrderType(orderType);
        otpt.setPriceType(priceType);
        otpt.setNote(note);
        int a = orderTypePriceTypeMapper.updateById(otpt);
        boolean state = false;
        if (a == 1){
            state = true;
        } else {
            state = false;
        }
        return state;
    }
    /**
     * 删除销售主体例会主体对照
     *
     *
     */
    public boolean delete(String orderPriceId) {
        OrderTypePriceTypeControl otpt = orderTypePriceTypeMapper.selectById(orderPriceId);
        otpt.setDeleteState("1");
        int a = orderTypePriceTypeMapper.updateById(otpt);
        boolean state = false;
        if (a == 1){
            state = true;
        } else {
            state = false;
        }
        return state;
    }
}
