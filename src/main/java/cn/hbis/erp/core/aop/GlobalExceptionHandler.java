package cn.hbis.erp.core.aop;

import cn.hbis.erp.core.common.exception.BizExceptionEnum;
import cn.hbis.erp.core.log.LogManager;
import cn.hbis.erp.core.log.factory.LogTaskFactory;
import cn.hbis.erp.core.shiro.ShiroKit;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;
import static cn.stylefeng.roses.core.util.HttpContext.getRequest;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseData bussiness(ServiceException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        getRequest().setAttribute("tip", e.getMessage());
        log.error("业务异常:", e);
        return new ErrorResponseData(e.getCode(), e.getMessage());
    }

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "login";
    }

    /**
     * 账号被冻结异常
     */
    @ExceptionHandler(DisabledAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map accountLocked(DisabledAccountException e) {
        Map map = new HashMap();
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号被冻结", getIp()));
        map.put("msg", "账号被冻结");
        return map;
    }

    /**
     * 账号密码错误异常
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map credentials(CredentialsException e) {
        Map map = new HashMap();
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号密码错误", getIp()));
        map.put("msg", "账号密码错误");
        return map;
    }

    /**
     * 无权访问该资源异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponseData credentials(UndeclaredThrowableException e) {
        getRequest().setAttribute("tip", "权限异常");
        log.error("权限异常!", e);
        return new ErrorResponseData(BizExceptionEnum.NO_PERMITION.getCode(), BizExceptionEnum.NO_PERMITION.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseData notFount(RuntimeException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        getRequest().setAttribute("tip", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new ErrorResponseData(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
    }
}
