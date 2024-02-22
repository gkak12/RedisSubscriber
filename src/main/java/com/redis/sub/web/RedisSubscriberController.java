package com.redis.sub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedisSubscriberController {

	@GetMapping(value = "/")
	public String getMainPage() {
		return "main";
	}
}
