package org.ice.app.service;

import com.google.inject.Inject;
import org.ice.app.domain.User;
import org.ice.app.mapper.UserMapper;


public class CommonService {

    @Inject
    UserMapper userMapper;

    public User sayHello() {
        return userMapper.getById(1L);
    }

}
