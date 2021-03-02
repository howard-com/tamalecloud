package tamalecloud.service.contact;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

//Entity Service的实现类
@RestController
@RefreshScope
public class ContactServiceImpl {

	// 注入其他service的Feign接口，以调用其业务方法
//	@Autowired
//	private EntityCacheServiceFeign entityCacheServiceFeign;

	// 绑定配置中心里面的数据
	@Value("${firm_id}")
	private String firm_id;

	@Value("${firm_name}")
	private String firm_name;

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

	// 业务方法：返回所有entity列表。Feign接口的已经启用熔断机制。一发生错误将返回提示信息。
	@RequestMapping("/getAllContacts")
	public Mono<String> getAllContacts() {
		String res= "All contact list.";
		ok().
		return Mono.just(res);
	}
}
