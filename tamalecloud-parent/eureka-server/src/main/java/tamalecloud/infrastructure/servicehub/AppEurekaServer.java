package tamalecloud.infrastructure.servicehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppEurekaServer {

	public static void main(String[] args) {
		// Start the Eureda server
		SpringApplication.run(AppEurekaServer.class, args);
	}
}