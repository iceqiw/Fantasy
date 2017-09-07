package org.ice.app.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import org.ice.app.domain.Jx;
import org.ice.app.service.CommonService;
import org.ice.core.CMediaType;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by admin on 2017/7/4.
 */

@Path("/")
public class CommonController {


    @Inject
    CommonService commonService;


    @GET
    @Produces(CMediaType.TEXT_PLAIN)
    public Response index(@Context HttpServletRequest req) {
        System.out.println(req.getSession().getId());
        URI uri = UriBuilder.fromUri("https://www.baidu.com").build();
        return Response.seeOther(uri).build();
    }


    @POST
    @Path("jx")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(CMediaType.APPLICATION_JSON)
    public void test(List<Jx> jxs) {
        int i = 0;
        for (Jx j : jxs) {
            i++;
            j.setId(i + "");
            commonService.addJx(j);
        }
    }


    @POST
    @Path("jx2")
    public void test2(String json) {
        Gson gson = new Gson();
        String s2 = json.toLowerCase();
        List<Jx> jxs = gson.fromJson(s2,new TypeToken<List<Jx>>() {}.getType());
        int i = 0;
        for (Jx j : jxs) {
            i++;
            j.setId(i + "");
            commonService.addJx(j);
        }
    }


}
