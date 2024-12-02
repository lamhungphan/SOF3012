package com.fpoly.sof3012.controller;

import com.fpoly.sof3012.entity.Message;
import com.fpoly.sof3012.utils.MessageDecoder;
import com.fpoly.sof3012.utils.MessageEncoder;
import jakarta.websocket.Session;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.PathParam;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(
        value = "/json/chat/{username}",
        encoders = MessageEncoder.class,
        decoders = MessageDecoder.class
)
public class ChatServerEndpoint {
    private static Map<String, Session> sessions = new HashMap<>();

    private void broadcast(Message message) {
        sessions.forEach((username, session) -> {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        if (sessions.containsKey(username)) {
            throw new RuntimeException("Username already exists");
        } else {
            session.getUserProperties().put("username", username);
            sessions.put(username, session);
            Message message = new Message("joined the chat", 0, username, sessions.size());
            this.broadcast(message);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    }

    @OnMessage
    public void onMessage(Message message, Session session) {
    }

    @OnClose
    public void onClose(Session session) {
    }
}
