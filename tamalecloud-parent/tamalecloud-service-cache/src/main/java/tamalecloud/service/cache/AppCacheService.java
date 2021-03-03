package tamalecloud.service.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import tamalecloud.api.entity.TSEntity;
import tamalecloud.api.entity.TSEntry;
import tamalecloud.service.cache.backstage.CacheData;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AppCacheService {

	static CacheData cacheData = CacheData.getCacheData();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AppCacheService.class, args);

		//初始化一些数据在cache里面
		for (int i = 1; i <= 10; i++) {
			TSEntity entityObj = new TSEntity();
			entityObj.setId(String.valueOf(i));
			entityObj.setName("Entity-" + String.valueOf(i));
			cacheData.addEntity(entityObj);
			
			entityObj = new TSEntity();
			entityObj.setId(String.valueOf(i));
			entityObj.setName("Contact-" + String.valueOf(i));
			cacheData.addEntity(entityObj);
			
			TSEntry entryObj = new TSEntry();
			entryObj.setId(String.valueOf(i));
			entryObj.setName("Entry-" + String.valueOf(i));
			cacheData.addEntry(entryObj);
		}
	}
}
