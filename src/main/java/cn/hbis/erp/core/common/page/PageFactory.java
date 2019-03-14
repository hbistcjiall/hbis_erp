package cn.hbis.erp.core.common.page;

import cn.stylefeng.roses.core.util.HttpContext;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * Table默认的分页参数创建
 * @author zhangb
 * @Date 2019-03-08
 **/
public class PageFactory {
    /**
     * 获取 table的分页参数
     *
     *
     */
    public static Page defaultPage() {
        HttpServletRequest request = HttpContext.getRequest();

        //每页多少条数据
        int limit = Integer.valueOf(request.getParameter("limit"));

        //第几页
        int page = Integer.valueOf(request.getParameter("page"));

        return new Page(page, limit);
    }

    /**
     * 创建能识别的分页响应参数
     *
     *
     */
    public static PageInfo createPageInfo(IPage page) {
        PageInfo result = new PageInfo();
        result.setCount(page.getTotal());
        result.setData(page.getRecords());
        result.setPageSize(page.getSize());
        result.setPageNo(page.getCurrent());
        return result;
    }
}
