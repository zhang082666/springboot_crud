package com.dj.demo.user.service.impl;

import com.dj.demo.user.entity.User;
import com.dj.demo.user.mapper.UserMapper;
import com.dj.demo.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangxc
 * @since 2018-11-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
