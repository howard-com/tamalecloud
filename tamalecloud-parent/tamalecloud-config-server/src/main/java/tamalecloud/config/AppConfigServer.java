package tamalecloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// 作为整个项目的配置服务中心
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class AppConfigServer {

	// 自动读取云端的配置文件，并缓存起来。
	// 配置文件的命名规则是: 服务-环境.properties
	public static void main(String[] args) {
		SpringApplication.run(AppConfigServer.class, args);
	}

}
