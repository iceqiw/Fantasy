package org.ice.app.mapper;

import org.apache.ibatis.annotations.Select;
import org.ice.app.domain.User;

/**
 * Created by admin on 2017/7/10.
 */
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    public User getById(final Long id);

}
