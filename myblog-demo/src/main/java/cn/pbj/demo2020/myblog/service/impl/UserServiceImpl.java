package cn.pbj.demo2020.myblog.service.impl;

import cn.pbj.demo2020.myblog.entity.User;
import cn.pbj.demo2020.myblog.mapper.UserMapper;
import cn.pbj.demo2020.myblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peng4444
 * @since 2020-11-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
