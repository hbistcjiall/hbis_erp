
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.constant.dictmap.NoticeMap;
import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.log.LogObjectHolder;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.hbis.erp.modular.system.entity.Notice;
import cn.hbis.erp.modular.system.service.NoticeService;
import cn.hbis.erp.modular.system.warpper.NoticeWrapper;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通知控制器
 *
 *
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 获取通知列表
     *
     *
     */
    @PostMapping("list")
    public Object list(@RequestParam(required = false) String condition,
                       @RequestParam String limit,
                       @RequestParam String page) {
        Page<Map<String, Object>> list = this.noticeService.list(condition);
        Page<Map<String, Object>> wrap = new NoticeWrapper(list).wrap();
        return PageFactory.createPageInfo(wrap);
    }

    /**
     * 新增通知
     *
     *
     */
    @PostMapping("add")
    @BussinessLog(value = "新增通知", key = "title", dict = NoticeMap.class)
    public Object add(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getTitle(), notice.getContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreateUser(ShiroKit.getUserNotNull().getId());
        notice.setCreateTime(new Date());
        this.noticeService.save(notice);
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     *
     */
    @PostMapping("delete")
    @BussinessLog(value = "删除通知", key = "noticeId", dict = NoticeMap.class)
    public Object delete(@RequestParam String noticeId) {

        //缓存通知名称
        LogObjectHolder.me().set(ConstantFactory.me().getNoticeTitle(noticeId));

        this.noticeService.removeById(noticeId);

        return SUCCESS_TIP;
    }

    /**
     * 修改通知
     *
     *
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @BussinessLog(value = "修改通知", key = "title", dict = NoticeMap.class)
    public Object update(Notice notice) {
        if (ToolUtil.isOneEmpty(notice, notice.getNoticeId(), notice.getTitle(), notice.getContent())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Notice old = this.noticeService.getById(notice.getNoticeId());
        //获取菜单当前信息，记录日志用
        LogObjectHolder.me().set(old);

        old.setTitle(notice.getTitle());
        old.setContent(notice.getContent());
        this.noticeService.updateById(old);
        return SUCCESS_TIP;
    }

}
