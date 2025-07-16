package com.study.demo.common;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class YjsWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, List<WebSocketSession>> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String docId = getDocId(session);
        sessions.computeIfAbsent(docId, k -> new CopyOnWriteArrayList<>()).add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String docId = getDocId(session);
        for (WebSocketSession s : sessions.get(docId)) {
            if (s.isOpen() && !s.equals(session)) {
                s.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.values().forEach(list -> list.remove(session));
    }

    private String getDocId(WebSocketSession session) {
        URI uri = session.getUri();
        return uri != null ? uri.getQuery().split("=")[1] : "default";
    }
}
