package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.Test;
import cn.hbis.erp.modular.system.mapper.TestMapper;
import cn.hbis.erp.modular.system.warpper.TestWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-10
 */
@Service
public class TestService extends ServiceImpl<TestMapper, Test> implements TestWrapper {

}
