package org.ice.app.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.ice.app.domain.TrainParams;
import org.ice.app.mapper.TrainParamsMapper;
import org.ice.app.vo.TrainVo;
import org.ice.core.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class TrainService {

    @Inject
    TrainParamsMapper trainParamsMapper;

    public List<TrainVo> searhAll() throws Exception {
        List<TrainVo> out = new ArrayList<>();
        List<TrainParams> list = trainParamsMapper.searchAll();
        for (TrainParams t : list) {
            TrainVo vo = search(t);
            out.add(vo);
        }
        return out;
    }

    private TrainVo search(TrainParams t) throws Exception {
        String url = String.format("http://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=%s&leftTicketDTO.from_station=%s&leftTicketDTO.to_station=%s&purpose_codes=ADULT",
                t.getDate(), t.getStartStation(), t.getEndStation());
        CloseableHttpClient instance = HttpUtil.getHttpClient();
        HttpGet request = getRequest(url);
        CloseableHttpResponse response = instance.execute(request);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        JsonObject returnData = new JsonParser().parse(bodyAsString).getAsJsonObject();
        TrainVo vo = parseData(returnData.getAsJsonObject("data"), t);
        return vo;
    }

    private TrainVo parseData(JsonObject returnData, TrainParams t) {
        TrainVo vo = null;
        JsonObject map = returnData.getAsJsonObject("map");
        JsonArray result = returnData.getAsJsonArray("result");
        for (JsonElement j : result) {
            vo = parseTrain(j, map);
            if (vo.getTrainNo().equals(t.getTrainNo())) {
                break;
            }
        }
        vo.setDate(t.getDate());
        vo.setEndStation(map.get(t.getEndStation()).getAsString());
        vo.setStartStation(map.get(t.getStartStation()).getAsString());
        return vo;
    }

    private TrainVo parseTrain(JsonElement j, JsonObject map) {
        TrainVo vo = new TrainVo();
        String[] line = j.getAsString().split("\\|");
        vo.setTrainNo(line[3]);
        vo.setNumRw(line[23]);
        vo.setNumYw(line[28]);
        vo.setNumYz(line[29]);
        return vo;
    }

    private HttpGet getRequest(String url) {
        HttpGet request = new HttpGet(url);
        request.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
        request.setHeader(HttpHeaders.ACCEPT, "*/*");
        request.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        request.setHeader(HttpHeaders.REFERER, "https://kyfw.12306.cn/otn/leftTicket/init");
        request.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
        request.setHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6");
        request.setHeader("cookie", "JSESSIONID=D8F410CA7F4C9F56DE7C3D62DB898E86; _jc_save_detail=true; RAIL_OkLJUJ=FFB2V0iAeAjwLyc10PPqi6w7T9pGMmAO; fp_ver=4.5.1; RAIL_EXPIRATION=1505014513552; RAIL_DEVICEID=itvSDLsvzejr7dfkjxqu0e2xdz91iSG65yjk-Do5ocN81JY3O_ljXsi-ThJOm5_tmQQC07sXwNuihXPQRd03EvDqeR2Z_AkgPBdDQSScBUikITPy4o3sBWKyXy3I0_g9m0COBAanlscLrFNIDtcybTjiJWg9a1mT; _jc_save_showIns=true; route=6f50b51faa11b987e576cdb301e545c4; BIGipServerotn=417857802.50210.0000; BIGipServerpassport=837288202.50215.0000; _jc_save_fromStation=%u897F%u5B89%2CXAY; _jc_save_toStation=%u798F%u5DDE%2CFZS; _jc_save_fromDate=2017-10-06; _jc_save_toDate=2017-09-08; _jc_save_wfdc_flag=dc");
        return request;
    }

}
