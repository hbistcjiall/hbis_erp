package cn.hbis.erp.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * 销售主体例会主体对照的包装
 *
 *
 */
public class SalesSubjectMeetingSubjectWrapper extends BaseControllerWrapper {

    public SalesSubjectMeetingSubjectWrapper(Map<String, Object> single) {
        super(single);
    }

    public SalesSubjectMeetingSubjectWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public SalesSubjectMeetingSubjectWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public SalesSubjectMeetingSubjectWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}
