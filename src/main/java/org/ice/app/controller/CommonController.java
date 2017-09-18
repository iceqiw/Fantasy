package org.ice.app.controller;

import com.google.inject.Inject;
import org.ice.app.service.CommonService;
import org.ice.app.vo.ResultVo;
import org.ice.core.CMediaType;
import org.ice.core.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by admin on 2017/7/4.
 */

@Path("/")
public class CommonController extends BaseController {


    @Inject
    CommonService commonService;


    @GET
    @Produces(CMediaType.TEXT_PLAIN)
    public Response index(@Context HttpServletRequest req) {
        System.out.println(req.getSession().getId());
        URI uri = UriBuilder.fromUri("https://www.baidu.com").build();
        return Response.seeOther(uri).build();
    }


    @GET
    @Path("jxSearch/search/{topic}")
    @Produces(CMediaType.APPLICATION_JSON)
    public ResultVo getUser(@PathParam("topic") String topic) {
        return successful(commonService.searchTopic(topic));
    }


}
