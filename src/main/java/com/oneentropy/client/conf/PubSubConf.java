package com.oneentropy.client.conf;

import com.oneentropy.udp.pub.sub.config.UdpChannelProperties;
import com.oneentropy.udp.pub.sub.model.ChannelController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "client")
@Getter
@Setter
@NoArgsConstructor
public class PubSubConf {

    private UdpChannelProperties udpChannelProperties;

    @Bean
    public UdpChannelProperties createUdpChannelProperties(){
        return this.udpChannelProperties;
    }

    @Bean
    public ChannelController createChannelController(){
        return new ChannelController();
    }



}
