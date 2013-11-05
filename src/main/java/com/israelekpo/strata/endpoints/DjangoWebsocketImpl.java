package com.israelekpo.strata.endpoints;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

// @TODO: Add Encoders and Decoders for JSON objects using Google GSON

@ServerEndpoint("/websocket/calculator")
public class DjangoWebsocketImpl implements DjangoWebsocket {

  private static final Logger log = Logger.getLogger(DjangoWebsocketImpl.class);

  public DjangoWebsocketImpl() {

    log.info("Creating Instance of Object");
  }

  @OnOpen
  public void opener(Session session) {
    log.info("Opening Connection with session id " + session.getId());

  }

  @OnMessage
  public void handleTextMessage(Session session, String msg) {
    try {
      log.info("Handling Text Message for session id " + session.getId());
      session.getBasicRemote().sendText(msg);
    } catch (IOException e) {
      log.error(e);
    }

  }

  @OnMessage
  public void handleBinaryMessage(Session session, ByteBuffer bb) {
    try {
      session.getBasicRemote().sendBinary(bb);
    } catch (IOException e) {
      log.error(e);
    }

  }

  @OnClose
  public void closer() {
    log.info("Closing Connection with session id ");

  }

  @OnError
  public void onError(Throwable t) throws Throwable {
    log.error("Error Occurred " + t.getStackTrace());

  }
}
