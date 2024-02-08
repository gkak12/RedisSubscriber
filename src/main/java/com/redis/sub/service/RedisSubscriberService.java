package com.redis.sub.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service(value = "redisSubscriberService")
public class RedisSubscriberService implements MessageListener{

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String channel = new String(message.getChannel());
		String content = new String(message.getBody());
		
		System.out.println(channel + ": " + content);
	}
}
