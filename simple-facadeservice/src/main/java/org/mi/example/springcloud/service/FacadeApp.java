package org.mi.example.springcloud.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients; 
import org.springframework.cloud.openfeign.FeignClient; 
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Facade App
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class FacadeApp implements ApplicationContextAware 
{
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main( String[] args )
    {
       SpringApplication.run(FacadeApp.class, args); 
       //String[] beans = applicationContext.getBeanDefinitionNames();
       //for (String bean : beans) {
       //     Class<?> beanType = applicationContext
	   // 			.getType(bean);
	   // 	System.out.println("BeanName:" + bean);
	   // 	System.out.println("Bean的类型：" + beanType);
	   // 	System.out.println("Bean所在的包：" + beanType.getPackage());
       // }

    }
}

interface MessageService {
    String invokeRemoteService();
}

@Service
class RibbonService implements MessageService {
    @Autowired
    private RestTemplate restTemplate;

    
    @Override
    @HystrixCommand(fallbackMethod = "reliable")
    public String invokeRemoteService() {
        return restTemplate.getForObject("http://simple-microservice/message", String.class);
    }

    public String reliable() {
        return "degrage service";
    }

}


@FeignClient(name = "simple-microservice", fallback = FeignFallback.class, qualifier = "feignService")
interface FeignService extends MessageService {

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    String invokeRemoteService();

    //@RequestMapping(value = "addUser", method = RequestMethod.POST, consumes = "application/json")
    //int update(User user);
}

@Component
class FeignFallback implements FeignService {
    @Override
    public String invokeRemoteService() {
        return "degrade service";
    }
}

@RestController
class FacadeRestController {
    @Autowired
    @Qualifier("feignService")
    private MessageService messageService;

    @RequestMapping("/facade")
    public String facade() {
        return messageService.invokeRemoteService();
    }
}



