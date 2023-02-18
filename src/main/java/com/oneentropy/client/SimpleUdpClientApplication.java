package com.oneentropy.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.oneentropy.udp.pub.sub.annotations.EnableUdpPubSub;
import com.oneentropy.udp.pub.sub.model.ChannelCollection;
import com.oneentropy.udp.pub.sub.model.ChannelController;
import com.oneentropy.udp.pub.sub.model.UdpPacket;
import com.oneentropy.udp.pub.sub.service.UdpPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@EnableUdpPubSub
@Slf4j
public class SimpleUdpClientApplication implements CommandLineRunner {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

	@Autowired
	private ChannelCollection channelCollection;

	@Autowired
	private ChannelController channelController;

	public static void main(String[] args) {
		SpringApplication.run(SimpleUdpClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UdpPublisher<UdpPacket> sndrchnl1 = (UdpPublisher<UdpPacket>) channelCollection.getPublisher("SNDRCHNL1");

		Scanner scanner = new Scanner(System.in);
		Map<String, String> data = new HashMap<>();
		while(true) {

			//log.info("Enter message:");
			if(!scanner.hasNext())
				System.exit(0);
			String message = scanner.nextLine();
			if(message.equals("exit")){
				log.info("Messages Received:{}",channelController.getMessageCount());
				System.exit(0);
			}
			data.put("message",message);
			JsonNode jsonNode = null;
			try {
				String json = OBJECT_MAPPER.writeValueAsString(data);
				jsonNode = OBJECT_MAPPER.readTree(json);
				UdpPacket udpPacket = UdpPacket.builder().data(jsonNode).build();
				sndrchnl1.publish(udpPacket);
			} catch (JsonProcessingException e) {
				log.error(e.getMessage());
			}

		}

	}
}
