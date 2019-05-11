package org.mi.example.springcloud.configserver;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


/**
 * ConfigServerApp
 *
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConfigServerApp.class, args);
    }
}
