package infrastructure.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients
public class AppGatewayService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AppGatewayService.class, args);
	}
}
