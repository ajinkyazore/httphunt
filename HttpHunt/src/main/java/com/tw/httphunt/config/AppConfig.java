package com.tw.httphunt.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages={"com.tw.httphunt"})
public class AppConfig {	
	
	 @Bean
	 public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	 }
}
