package org.ice.core.module;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.ice.core.filter.LoginFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiwei
 * @time 2017/9/7
 * @description  jersey 模块
 */
public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("com.sun.jersey.config.property.packages", "org.ice.app.controller");
        parameters.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        filter("/*").through(LoginFilter.class);
        serve("/*").with(GuiceContainer.class, parameters);
    }

}