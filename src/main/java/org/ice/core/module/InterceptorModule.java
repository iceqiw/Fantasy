package org.ice.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import org.ice.app.service.CommonService;
import org.ice.core.interceptor.LoggerMethodInterceptor;

public class InterceptorModule   extends AbstractModule {
    protected void configure() {
        bindInterceptor(Matchers.subclassesOf(CommonService.class), Matchers.any(),
                new LoggerMethodInterceptor());
    }
}