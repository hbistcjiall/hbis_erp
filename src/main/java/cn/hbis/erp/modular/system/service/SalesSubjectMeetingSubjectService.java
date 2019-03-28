package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.modular.system.entity.SalesSubjectMeetingSubjectControl;
import cn.hbis.erp.modular.system.mapper.SalesSubjectMeetingSubjectMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.codec.language.bm.Lang;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 销售主体例会主体对照 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class SalesSubjectMeetingSubjectService extends ServiceImpl<SalesSubjectMeetingSubjectMapper, SalesSubjectMeetingSubjectControl> {

    @Resource
    private SalesSubjectMeetingSubjectMapper salesSubjectMeetingSubjectMapper;

    /**
     * 查询销售主体例会主体对照
     *
     *
     */
    public Page<Map<String, Object>> List(String companyId) {
        Page page = PageFactory.defaultPage();
        return this.baseMapper.List(page, companyId);
    }
    /**
     * 添加销售主体例会主体对照
     *
     *
     */
    public boolean insert(SalesSubjectMeetingSubjectControl salesSubjectMeetingSubjectControl) {
        int a = salesSubjectMeetingSubjectMapper.insert(salesSubjectMeetingSubjectControl);
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
    public boolean update(Long Id, String companyId, String saleBodyDes, String saleBody) {
        SalesSubjectMeetingSubjectControl ssmsc = salesSubjectMeetingSubjectMapper.select(Id);
        ssmsc.setCompanyId(companyId);
        if ("9580".equals(companyId)){
            ssmsc.setCompanyName("唐钢");
        }else if ("9727".equals(companyId)){
            ssmsc.setCompanyName("邯钢");
        }else if ("9193".equals(companyId)){
            ssmsc.setCompanyName("宣钢");
        }else if ("9196".equals(companyId)){
            ssmsc.setCompanyName("承钢");
        }else if ("1932".equals(companyId)){
            ssmsc.setCompanyName("舞钢");
        }
        ssmsc.setSaleBodyDes(saleBodyDes);
        ssmsc.setSaleBody(saleBody);
        ssmsc.setGmtModify(new Date());
        int a = this.baseMapper.updateById(ssmsc);
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
    public boolean delete(Long Id) {
        SalesSubjectMeetingSubjectControl ssmsc = salesSubjectMeetingSubjectMapper.select(Id);
        ssmsc.setIsDelete("1");
        int a = this.baseMapper.updateById(ssmsc);
        boolean state = false;
        if (a == 1){
            state = true;
        } else {
            state = false;
        }
        return state;
    }
}
