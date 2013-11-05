package com.israelekpo.strata.endpoints;

import java.nio.ByteBuffer;

import javax.websocket.Session;

public interface DjangoWebsocket {

  public void opener(Session session);

  public void handleTextMessage(Session session, String msg);

  public void handleBinaryMessage(Session session, ByteBuffer bb);

  public void closer();

  public void onError(Throwable t) throws Throwable;
}
