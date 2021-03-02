package tamalecloud.service.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//启动contact服务
public class AppContactService {
	public static void main(String[] args) {
		SpringApplication.run(AppContactService.class, args);
	}
}
