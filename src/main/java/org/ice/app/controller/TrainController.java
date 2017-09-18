package org.ice.app.controller;

import com.google.inject.Inject;
import org.ice.app.domain.User;
import org.ice.app.service.CommonService;
import org.ice.app.service.TrainService;
import org.ice.app.vo.ResultVo;
import org.ice.app.vo.TrainVo;
import org.ice.core.CMediaType;
import org.ice.core.controller.BaseController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * @author qiwei
 * @time 2017/9/5
 * @description
 */
@Path("/train")
public class TrainController extends BaseController {

    @Inject
    TrainService trainService;


    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultVo search() throws Exception {
        List<TrainVo> list=trainService.searhAll();
        return successful(list);
    }

}
