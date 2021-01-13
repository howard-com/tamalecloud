package tamalecloud.service.entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//启动entry服务
public class AppEntryService {

	public static void main(String[] args) {
		SpringApplication.run(AppEntryService.class, args);
	}

}
