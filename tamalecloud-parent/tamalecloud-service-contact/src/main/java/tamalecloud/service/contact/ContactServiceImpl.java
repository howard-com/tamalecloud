package tamalecloud.service.contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.netflix.client.ClientFactory;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.loadbalancer.AbstractLoadBalancer;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;

import reactivefeign.cloud.CloudReactiveFeign;
import reactivefeign.webclient.WebReactiveFeign;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tamalecloud.api.entity.TSEntity;
import tamalecloud.service.contact.feign.ReactorEntityCacheServiceFeign;

//Entity Service的实现类
@RestController
@RefreshScope
public class ContactServiceImpl {

	@Autowired
	DiscoveryClient discoveryClient;
	// 注入其他service的Feign接口，以调用其业务方法
//	@Autowired
//	private EntityCacheServiceFeign entityCacheServiceFeign;
//
//	@Autowired
//	private ObjectProvider<EntityCacheServiceFeign> entityCacheFeign;

//	private static final String WebReactiveFeign = null;

	// 绑定配置中心里面的数据
	@Value("${firm_id}")
	private String firm_id;

	@Value("${firm_name}")
	private String firm_name;

	@Value("${cache_service_name}")
	private String cacheServiceName;

	// 业务方法：从返回string类型的 entity信息
//	@RequestMapping("/showEntityById")
//	public String showEntityById(@RequestParam("id") String id) {
//		String res;
//		String baseUrl = "[调用类]:%s\n[结果]:\n%s";
//		TSEntity entity = entityCacheServiceFeign.getEntityById(id);
//
//		if (entity != null) {
//			res = new StringBuffer("Entity id=").append(entity.getId()).append("\nEntity name=").append(entity.getName()).toString();
//		} else {
//			res = new StringBuffer("未找到id为").append(id).append("的entity").toString();
//		}
//		
//		return String.format(baseUrl, this.getClass().toString(), res);
//	}

	@RequestMapping("/getAllContacts")
	public Mono<TSEntity[]> getAllContacts() {
//		ReactorEntityCacheServiceFeign client = WebReactiveFeign.<ReactorEntityCacheServiceFeign>builder()
//				.target(ReactorEntityCacheServiceFeign.class, "http://localhost:64885/");
//		
//		Mono<TSEntity[]> res = client.getAllEntities();
//		
//		return res;

		String cacheSvcUrl = null;
		//System.out.println("cacheServiceName=" + cacheServiceName);

		List<ServiceInstance> serviceList = discoveryClient.getInstances(cacheServiceName);
		if (serviceList == null || serviceList.size() == 0) {
			return null;
		}

		ServiceInstance serviceInstance = serviceList.get(0);
		cacheSvcUrl = serviceInstance.getUri().toString();

		//System.out.println("cacheSvcUrl=" + cacheSvcUrl);

		ReactorEntityCacheServiceFeign client = CloudReactiveFeign
				.<ReactorEntityCacheServiceFeign>builder(WebReactiveFeign.builder())
				.setLoadBalancerCommandFactory(s -> LoadBalancerCommand.builder()
						.withLoadBalancer(
								AbstractLoadBalancer.class.cast(ClientFactory.getNamedLoadBalancer(cacheServiceName)))
						.withRetryHandler(new DefaultLoadBalancerRetryHandler(1, 1, true)).build())
				.fallback(new ReactorEntityCacheServiceFeign() {
					@Override
					public Mono<TSEntity> getEntityById(@RequestParam("id") String id) {
						return null;
					}

					@Override
					public Mono<TSEntity[]> getAllEntities() {
						return null;
					}

					@Override
					public Mono<String> getAllEntityNames() {
						return null;
					}
				}).target(ReactorEntityCacheServiceFeign.class, cacheSvcUrl);

		Mono<TSEntity[]> res = client.getAllEntities();
		
		res.map(entityArray -> {
			System.out.println("11111111111111111");
			Flux<TSEntity> t = Flux.fromArray(entityArray);
			return t;
		})
		.map(entity -> {
			System.out.println(entity);
			return entity;
		});
		
		res.subscribe();

		return res;

//		return Mono.create(sink -> {
//			TSEntity[] entities = entityCacheServiceFeign.getAllEntities();
//			StringBuffer res = new StringBuffer();
////			TSEntity[] entities = {new TSEntity(), new TSEntity()};
//			for (TSEntity t : entities) {
//				res.append(new StringBuffer("Entity id=").append(t.getId()).append("\nEntity name=").append(t.getName())
//						.append("\n"));
//			}
//
//			sink.success(res.toString());
//		});
	}
}
