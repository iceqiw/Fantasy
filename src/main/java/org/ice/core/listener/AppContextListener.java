package org.ice.core.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.ice.core.module.DatabaseModule;
import org.ice.core.module.InterceptorModule;
import org.ice.core.module.WebModule;

/**
 * Created by admin on 2017/7/4.
 */
public class AppContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

        return Guice.createInjector(new WebModule(), new DatabaseModule(), new InterceptorModule());
    }


}