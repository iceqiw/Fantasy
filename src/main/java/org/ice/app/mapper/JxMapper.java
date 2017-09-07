package org.ice.app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ice.app.domain.Jx;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
public interface JxMapper {
    @Insert("INSERT INTO jx_copy(id,itemtype,itemno,imglink,answer,question,optiona,optionb,optionc,optiond) " +
            "VALUES(#{id},#{itemtype},#{itemno},#{imglink},#{answer},#{question},#{optiona},#{optionb},#{optionc},#{optiond})")
    void addjx(Jx jx);

    @Select("select * from jx where question like '%${name}%' ")
    List<Jx> search(@Param("name") String name);
}
