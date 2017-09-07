package org.ice.core.module;

import com.google.inject.name.Names;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author qiwei
 * @time 2017/9/7
 * @description 数据库模块
 */
public class DatabaseModule extends MyBatisModule {

    @Override
    protected void initialize() {
        bindDataSourceProviderType(PooledDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        addMapperClasses("org.ice.app.mapper");//将包下类都作为mapper导入
        Names.bindProperties(binder(), createTestProperties());
    }


    protected Properties createTestProperties() {
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
