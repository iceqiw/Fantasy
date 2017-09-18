package org.ice.app.vo;


import lombok.Data;

@Data
public class TrainVo {
    private String trainNo;
    private String date;
    private String startStation;
    private String endStation;
    private String numRw;
    private String numYw;
    private String numYz;

}
