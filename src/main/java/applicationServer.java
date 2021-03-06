import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.ice.core.listener.AppContextListener;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.ResourceBundle;

/**
 * Created by admin on 2017/7/11.
 */
public class applicationServer {

    public static void main(String[] args) throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("app");
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setName(rb.getString("app.name"));
        connector.setHost("0.0.0.0");
        connector.setPort(Integer.parseInt(rb.getString("app.port")));
        server.addConnector(connector);


        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setServer(server);
        webAppContext.setResourceBase(rb.getString("app.resourceBase"));
        //访问地址：http://127.0.0.1:8080/test/
        webAppContext.setContextPath(rb.getString("app.contextPath"));
        webAppContext.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        webAppContext.addEventListener(new AppContextListener());
        server.setHandler(webAppContext);
        server.start();
    }
}
