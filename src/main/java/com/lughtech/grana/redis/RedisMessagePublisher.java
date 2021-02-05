package com.lughtech.grana.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lughtech.grana.dto.EmailDto;

@Service
public class RedisMessagePublisher {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private static final String STREAM_KEY = "emails";

	public RedisMessagePublisher() {
	}

	public void publish(EmailDto email) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			json = ow.writeValueAsString(email);
			ObjectRecord<String, String> record = StreamRecords.newRecord().ofObject(json).withStreamKey(STREAM_KEY);
			redisTemplate.opsForStream().add(record);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
