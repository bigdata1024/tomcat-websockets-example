package com.israelekpo.strata.config;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

import org.apache.log4j.Logger;

import com.israelekpo.strata.endpoints.EchoEndpoint;

public class DjangoConfig implements ServerApplicationConfig {

  private static final Logger log = Logger.getLogger(DjangoConfig.class);

  private static final String ENDPOINT_PACKAGE_PREFIX = "com.israelekpo.strata.endpoints";

  /**
   * Used for Filtering Endpoints for Loading
   *
   * You can implement your own logic here
   *
   * @param clazz
   * @return
   */
  private static boolean isValidEndpint(Class<?> clazz) {

    final String classPackageName = clazz.getPackage().getName();

    log.info("Checking if Package prefix for class " + clazz + " is valid" );

    return classPackageName.startsWith(ENDPOINT_PACKAGE_PREFIX);
  }

  @Override
  public Set<ServerEndpointConfig> getEndpointConfigs(
          Set<Class<? extends Endpoint>> scanned) {

      Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();

      log.info("Loading Programmatic Endpoints");

      if (scanned.contains(EchoEndpoint.class)) {
          result.add(ServerEndpointConfig.Builder.create(
                  EchoEndpoint.class,
                  "/websocket/echoProgrammatic").build());

          log.info("Loading Programmatic Endpoint" + EchoEndpoint.class.getName());
      }

      return result;
  }


  @Override
  public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {

    // Deploy all WebSocket endpoints defined by annotations
      Set<Class<?>> results = new HashSet<Class<?>>();

      log.info("Filtering Annotated Endpoints from " + scanned.size() + " scanned matches");

      for (Class<?> clazz : scanned) {
        if (isValidEndpint(clazz)) {
          log.info("Loading Annotated Endpoint " + clazz.getName());
          results.add(clazz);
        }
      }

      return results;
  }
}
