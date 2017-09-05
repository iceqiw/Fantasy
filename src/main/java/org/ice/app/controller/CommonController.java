package org.ice.app.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import org.ice.app.domain.User;
import org.ice.app.service.CommonService;
import org.ice.core.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by admin on 2017/7/4.
 */

@Path("/")
public class CommonController {

    @Inject
    CommonService commonService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response index(@Context HttpServletRequest req) {
        System.out.println(req.getSession().getId());
        URI uri = UriBuilder.fromUri("https://www.baidu.com").build();
        return Response.seeOther(uri).build();
    }


    @GET
    @Path("user/{userId}/{userName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@PathParam("userId") String userId, @PathParam("userName") String userName) {
        return "User ID: " + userId + ", user name: " + userName;
    }

    @POST
    @Path("g")
    @Produces({MediaType.APPLICATION_JSON})
    public String test() {
        User s=commonService.sayHello();
        Gson gson=new Gson();
        return  gson.toJson(s);
    }

}
