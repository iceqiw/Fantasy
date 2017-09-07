package org.ice.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import org.ice.app.service.CommonService;
import org.ice.core.interceptor.LoggerMethodInterceptor;

/**
 * @author qiwei
 * @time 2017/9/7
 * @description  拦截器
 */
public class InterceptorModule   extends AbstractModule {
    protected void configure() {
        bindInterceptor(Matchers.subclassesOf(CommonService.class), Matchers.any(),
                new LoggerMethodInterceptor());
    }
}