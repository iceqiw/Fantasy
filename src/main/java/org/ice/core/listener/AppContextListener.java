package org.ice.core.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.ice.core.module.InterceptorModule;
import org.ice.core.module.WebModule;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by admin on 2017/7/4.
 */
public class AppContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        MyBatisModule dataModule = new MyBatisModule() {
            @Override
            protected void initialize() {
                bindDataSourceProviderType(PooledDataSourceProvider.class);
                bindTransactionFactoryType(JdbcTransactionFactory.class);
                addMapperClasses("org.ice.app.mapper");//将包下类都作为mapper导入
                Names.bindProperties(binder(), createTestProperties());
            }
        };
        return Guice.createInjector(new WebModule(), dataModule,new InterceptorModule());
    }

    protected static Properties createTestProperties() {
        Properties properties = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("db");
        properties.setProperty("mybatis.environment.id", "demo1");
        properties.setProperty("JDBC.driver", rb.getString("db.driver"));
        properties.setProperty("JDBC.url", rb.getString("db.url"));
        properties.setProperty("JDBC.username", rb.getString("db.username"));
        properties.setProperty("JDBC.password", rb.getString("db.password"));
        properties.setProperty("JDBC.autoCommit", "false");
        return properties;
    }

}