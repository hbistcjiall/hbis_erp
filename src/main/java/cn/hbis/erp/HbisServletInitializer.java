package cn.hbis.erp;

import cn.hbis.erp.HbisApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Hbis Web程序启动类
 *
 */
public class HbisServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HbisApplication.class);
    }
}
