package com.redis.sub.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.redis.sub.service.RedisSubscriberService;

@Configuration
public class RedisSubscriberConfig {

	@Value("${spring.redis.channel}")
	private String channel;
	
	@Resource(name = "redisSubscriberService")
	private RedisSubscriberService redisSubscriberService;
	
	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer(
				RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		container.addMessageListener(messageListenerAdapter, new ChannelTopic(channel));
		
		return container;
	}
	
	@Bean
	public MessageListenerAdapter messageListenerAdapter() {
		return new MessageListenerAdapter(redisSubscriberService);
	}
}
