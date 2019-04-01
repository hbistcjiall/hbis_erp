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
    public boolean update(String Id, String companyId, String saleBodyDes, String saleBody) {
        Long l = Long.parseLong(Id);
        SalesSubjectMeetingSubjectControl ssmsc = salesSubjectMeetingSubjectMapper.select(l);
        ssmsc.setId(l);
        ssmsc.setCompanyName(companyId);
        if ("唐钢".equals(companyId)){
            ssmsc.setCompanyId("9580");
        }else if ("邯钢".equals(companyId)){
            ssmsc.setCompanyId("9727");
        }else if ("宣钢".equals(companyId)){
            ssmsc.setCompanyId("9193");
        }else if ("承钢".equals(companyId)){
            ssmsc.setCompanyId("9196");
        }else if ("舞钢".equals(companyId)){
            ssmsc.setCompanyId("1932");
        }
        ssmsc.setSaleBodyDes(saleBodyDes);
        ssmsc.setSaleBody(saleBody);
        ssmsc.setGmtModify(new Date());
        System.out.println(ssmsc.toString());
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
    public boolean delete(String Id) {
        Long l = Long.parseLong(Id);
        SalesSubjectMeetingSubjectControl ssmsc = salesSubjectMeetingSubjectMapper.select(l);
        ssmsc.setId(l);
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
