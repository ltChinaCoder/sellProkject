package com.lt.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {
    private static CopyOnWriteArraySet<WebSocket> sessions = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        sessions.add(this);
        log.info("有新的websocket连接来，当前总连接为： " + sessions.size());
    }

    @OnClose
    public void onClose() {
        sessions.remove(this);
        log.info("有连接断开，当前总连接为： " + sessions.size());
    }

    @OnMessage
    public void onMessage(String messgae) {
        log.info("收到消息为： " + messgae);
    }

    public void sendMessage(String messgae) {
        for (WebSocket webSocket : sessions) {
            log.info("广播消息： " + messgae);
            try {
                webSocket.session.getBasicRemote().sendText(messgae);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
