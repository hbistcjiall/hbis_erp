package cn.hbis.erp.modular.system.service;

import cn.hbis.erp.modular.system.entity.UserTest;
import cn.hbis.erp.modular.system.mapper.UserTestMapper;
import cn.hbis.erp.modular.system.warpper.UserTestWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiarsi
 * @since 2019-03-11
 */
@Service
public class UserTestService extends ServiceImpl<UserTestMapper, UserTest> implements UserTestWrapper {

}
