package org.ice.app.domain;

import lombok.Data;

/**
 * Created by admin on 2017/7/10.
 */
@Data
public class TrainParams {
    private Long id;
    private String trainNo;
    private String date;
    private String startStation;
    private String endStation;
}
