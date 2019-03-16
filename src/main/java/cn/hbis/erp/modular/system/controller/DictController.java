
package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.core.common.annotion.BussinessLog;
import cn.hbis.erp.core.common.annotion.Permission;
import cn.hbis.erp.core.common.constant.Const;
import cn.hbis.erp.core.common.constant.dictmap.DictMap;
import cn.hbis.erp.core.common.constant.factory.ConstantFactory;
import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.common.page.PageFactory;
import cn.hbis.erp.core.log.LogObjectHolder;
import cn.hbis.erp.modular.system.model.DictDto;
import cn.hbis.erp.modular.system.service.DictService;
import cn.hbis.erp.modular.system.warpper.DictWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 字典控制器
 *
 *
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    /**
     * 新增字典
     *添加字典条目传dictTypeId,字典类型不传
     *
     */
    @ApiOperation(value = "新增字典")
    @PostMapping("add")
    @Permission(Const.ADMIN_NAME)
    public ResponseData add(@Valid DictDto dictDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.dictService.addDict(dictDto);
        return SUCCESS_TIP;
    }

    /**
     * 获取所有字典列表
     *
     *
     */
    @ApiOperation(value = "获取所有字典列表")
    @PostMapping("list")
    @Permission(Const.ADMIN_NAME)
    public Object list(@RequestParam(required = false) String condition,@RequestParam String limit,@RequestParam String page) {
        Page<Map<String, Object>> list = this.dictService.list(condition);
        Page<Map<String, Object>> warpper = new DictWrapper(list).wrap();
        return PageFactory.createPageInfo(warpper);
    }

    /**
     * 删除字典记录
     *
     *
     */
    @ApiOperation(value = "删除字典记录")
    @BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
    @PostMapping("delete")
    @Permission(Const.ADMIN_NAME)
    public ResponseData delete(@RequestParam String dictId) {

        //缓存被删除的名称
        LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));

        this.dictService.delteDict(dictId);

        return SUCCESS_TIP;
    }

}
