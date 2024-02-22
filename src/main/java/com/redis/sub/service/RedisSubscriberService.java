package com.redis.sub.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.redis.sub.webSocket.WebSocketHandler;

@Service(value = "redisSubscriberService")
public class RedisSubscriberService implements MessageListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisSubscriberService.class);
	
	@Resource(name = "webSocketHandler")
	private WebSocketHandler webSocketHandler;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String channel = new String(message.getChannel());
		String content = new String(message.getBody());
		LOGGER.debug(channel + ": " + content);
		
		try (WebSocketSession wSession = webSocketHandler.getFirstWebSocketsession()){
			wSession.sendMessage(new TextMessage(content));
		} catch (IOException e) {
			LOGGER.debug(e.toString());
		}
	}
}
