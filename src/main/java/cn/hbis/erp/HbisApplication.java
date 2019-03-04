
package cn.hbis.erp;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot方式启动类
 *
 *
 */
@SpringBootApplication(exclude = {WebAutoConfiguration.class})
public class HbisApplication {

    private final static Logger logger = LoggerFactory.getLogger(HbisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HbisApplication.class, args);
        logger.info(HbisApplication.class.getSimpleName() + " is success!");
    }
}
