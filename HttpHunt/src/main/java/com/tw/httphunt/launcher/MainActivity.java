package com.tw.httphunt.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.tw.httphunt.controller.HomeController;


@Component
public class MainActivity implements CommandLineRunner {
	
	@Autowired HomeController homeController;
	
	
	private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("CommandLine Runner Invoked");
		homeController.calculateVowels();
	}

}
