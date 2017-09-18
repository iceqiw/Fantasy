package org.ice.app.mapper;

import org.apache.ibatis.annotations.Select;
import org.ice.app.domain.TrainParams;

import java.util.List;

/**
 * Created by admin on 2017/7/10.
 */
public interface TrainParamsMapper {

    @Select("select * from trainsearch ")
    List<TrainParams> searchAll();
}
