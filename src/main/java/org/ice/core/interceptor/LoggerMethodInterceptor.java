package org.ice.core.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerMethodInterceptor implements MethodInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoggerMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();

        long startTime = System.nanoTime();
        logger.info("{},{}", methodName, startTime);
        return invocation.proceed();
    }
}
