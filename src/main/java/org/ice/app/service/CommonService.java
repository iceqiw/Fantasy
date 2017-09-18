package org.ice.app.service;

import com.google.inject.Inject;
import org.ice.app.domain.Jx;
import org.ice.app.domain.User;
import org.ice.app.mapper.JxMapper;
import org.ice.app.mapper.UserMapper;

import java.util.List;


public class CommonService {

    @Inject
    UserMapper userMapper;

    @Inject
    JxMapper jxMapper;

    public User sayHello() {
        return userMapper.getById(1L);
    }

    public void addJx(Jx j) {
        jxMapper.addjx(j);
    }

    public List<Jx> searchTopic(String topic) {
        List<Jx> j = jxMapper.search(topic);
        return j;
    }
}
