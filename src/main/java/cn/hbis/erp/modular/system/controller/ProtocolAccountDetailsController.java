package cn.hbis.erp.modular.system.controller;

import cn.hbis.erp.modular.system.service.ProtocolAccountDetailsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 协议户明细控制器
 *
 *
 */
@Controller
@RequestMapping("/protocolAccountDetails")
public class ProtocolAccountDetailsController extends BaseController {

    private static String PREFIX = "/modular/system/protocolAccountDetails";

    @Autowired
    private ProtocolAccountDetailsService protocolAccountDetailsService;
}
