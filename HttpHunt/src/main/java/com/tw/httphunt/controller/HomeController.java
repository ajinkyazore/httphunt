package com.tw.httphunt.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	
	@Autowired RestTemplate restTemplate; 
	
	private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

	//challenge#1
	public void calculateLetters() throws JsonParseException, JsonMappingException, IOException {
		String inputText = getInput();
		Map<String,Object> outputResponse = new HashMap<>();
		outputResponse.put("count", inputText.length());
		sendOutput(outputResponse);
	}
	
	//challenge#2
	public void calculateWords() throws JsonParseException, JsonMappingException, IOException{
		String inputText = getInput();
		Map<String,Object> outputResponse = new HashMap<>();
		outputResponse.put("wordCount", new StringTokenizer(inputText).countTokens());
		sendOutput(outputResponse);
	}
	
	//challenge#3
		public void calculateSentences() throws JsonParseException, JsonMappingException, IOException{
			String inputText = getInput();
			Map<String,Object> outputResponse = new HashMap<>();
			outputResponse.put("sentenceCount", inputText.split("([?.])").length);
			logger.info("Response Output : "+outputResponse.get("sentenceCount"));
			sendOutput(outputResponse);
		}
		
		//challenge#4
				public void calculateVowels() throws JsonParseException, JsonMappingException, IOException{
					String inputText = getInput();
					Map<String,Object> outputResponse = new LinkedHashMap<>();
					List<Character> vowels = Arrays.asList('a','e','i','o','u');					
					vowels.forEach((vow)->{
							outputResponse.put(vow.toString(), inputText.chars().filter(ch->ch==vow).count());
						});					
					
					//outputResponse.put("sentenceCount", inputText.split("([?.])").length);
					logger.info("Response Output : "+outputResponse);
					sendOutput(outputResponse);
				}


	private void sendOutput(Map<String,Object> outputResponse) throws JsonProcessingException {
			
		String outputString = new ObjectMapper().writeValueAsString(outputResponse);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("userId","ulMldvYvn");
	    headers.setContentType(MediaType.APPLICATION_JSON);
	      
	      HttpEntity<String> entity = new HttpEntity<String>(outputString,headers);
	      String responseString = restTemplate.exchange("https://http-hunt.thoughtworks-labs.net/challenge/output", HttpMethod.POST, entity, String.class).getBody();
	      System.out.println(responseString);
	}

	private String getInput() throws JsonParseException, JsonMappingException, IOException {
		  HttpHeaders headers = new HttpHeaders();
	      headers.add("userId","ulMldvYvn");
	      HttpEntity<String> entity = new HttpEntity<String>(headers);
	      String responseString = restTemplate.exchange("https://http-hunt.thoughtworks-labs.net/challenge/input", HttpMethod.GET, entity, String.class).getBody();
	      Map<String, Object> responseMap =  new ObjectMapper().readValue(responseString, HashMap.class);
	      return responseMap.get("text").toString();
	       
	}

}
