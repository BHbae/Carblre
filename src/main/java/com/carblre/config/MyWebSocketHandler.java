package com.carblre.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@EnableWebSocket
public class MyWebSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        logger.info("New WebSocket connection established: " + session.getId());
        logger.info("Total active sessions: " + sessions.size());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        logger.info("WebSocket connection closed: " + session.getId());
        logger.info("Total active sessions: " + sessions.size());  // 현재 세션 수 로깅
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Received message: " + message.getPayload());
    }

    public void sendMessageToAll(String message) {
        logger.info("Sending message to all sessions: " + message);
        if (sessions.isEmpty()) {
            logger.warn("No active WebSocket sessions found.");
            return; // 세션이 없으면 함수 종료
        }
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));
                logger.info("Message sent to session: " + session.getId());
            } catch (Exception e) {
                logger.error("Error while sending message to session: " + session.getId(), e);
            }
        }
    }

    public int getActiveSessionsCount() {
        return sessions.size();
    }



}