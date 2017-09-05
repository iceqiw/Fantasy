package org.ice.core.module;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.ice.core.filter.LoginFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/4.
 */
public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("com.sun.jersey.config.property.packages", "org.ice.app.controller");
        filter("/*").through(LoginFilter.class);
        serve("/*").with(GuiceContainer.class, parameters);
    }

}