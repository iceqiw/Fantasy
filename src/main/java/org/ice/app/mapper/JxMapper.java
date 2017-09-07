package org.ice.app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.ice.app.domain.Jx;

/**
 * Created by admin on 2017/7/10.
 */
public interface JxMapper {
    @Insert("INSERT INTO jx_copy(id,itemtype,itemno,imglink,answer,question,optiona,optionb,optionc,optiond) " +
            "VALUES(#{id},#{itemtype},#{itemno},#{imglink},#{answer},#{question},#{optiona},#{optionb},#{optionc},#{optiond})")
    public void addjx(Jx jx);
}
