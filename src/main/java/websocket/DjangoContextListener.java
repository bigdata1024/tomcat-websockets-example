package websocket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class DjangoContextListener implements ServletContextListener {

  private static final Logger log = Logger.getLogger(DjangoContextListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    log.info("Initialized Context Listener");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

    log.info("Destroying Context Listener");
  }
}
