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

      log.info("Loading Annotated Endpoints");

      for (Class<?> clazz : scanned) {

        log.info("Loading Annotated Endpoint " + clazz.getName());

        results.add(clazz);

      }

      return results;
  }
}
