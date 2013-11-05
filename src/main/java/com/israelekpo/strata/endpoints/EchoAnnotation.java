package com.israelekpo.strata.endpoints;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint("/websocket/echoAnnotation")
public class EchoAnnotation {

  private static final Logger log = Logger.getLogger(EchoAnnotation.class);

  private Session session = null;

   public EchoAnnotation() {

     log.info("Creating Instance of " + EchoAnnotation.class.getName());

     log.info("Instantiation EchoAnnotation for thread id " + Thread.currentThread().getId());
   }

    @OnClose
    public void closer() {

      log.info("Closing Connection with Websocket for session id " + session.getId());

      log.info("Closing Connection for thread id " + Thread.currentThread().getId());
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
      log.error(t.getStackTrace());
    }

    @OnOpen
    public void opener(Session session) {

      log.info("Incoming Connection "  + session.getId());

      log.info("Opening Connection for thread id " + Thread.currentThread().getId());

      this.session = session;
    }

    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {

      log.info("Incoming Message in echoTextMessage() using this.session " + this.session.getId() + " and session id " + session.getId());

        try {

          log.info("Processing Plain Text Message for thread id " + Thread.currentThread().getId());
            if (session.isOpen()) {
                session.getBasicRemote().sendText(msg, last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }

    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer bb,
            boolean last) {

      log.info("Incoming Message in echoBinaryMessage() using Session ID " + session.getId());

        try {

          log.info("Processing Binary Message for thread id " + Thread.currentThread().getId());
            if (session.isOpen()) {
                session.getBasicRemote().sendBinary(bb, last);
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }

    /**
     * Process a received pong. This is a NO-OP.
     *
     * @param pm    Ignored.
     */
    @OnMessage
    public void echoPongMessage(PongMessage pm) {

      log.info("Pong Message: " + pm.getApplicationData().toString());

      log.info("Pong Message for thread id " + Thread.currentThread().getId());
    }
}
