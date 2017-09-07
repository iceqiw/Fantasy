package org.ice.app.controller;

import com.google.gson.Gson;
import com.google.inject.Inject;
import org.ice.app.domain.User;
import org.ice.app.service.CommonService;
import org.ice.core.CMediaType;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author qiwei
 * @time 2017/9/5
 * @description
 */
@Path("/user")
public class UserController {

    @Inject
    CommonService commonService;


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(CMediaType.APPLICATION_JSON)
    public User test(User user) {
//        UserVo u = gson.fromJson(user, UserVo.class);

        User s = commonService.sayHello();
        return s;
    }

}
