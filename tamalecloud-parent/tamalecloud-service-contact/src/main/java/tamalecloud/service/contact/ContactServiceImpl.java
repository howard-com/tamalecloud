package tamalecloud.service.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import tamalecloud.api.entity.TSEntity;
import tamalecloud.service.contact.feign.EntityCacheServiceFeign;

//Entity Service的实现类
@RestController
@RefreshScope
public class ContactServiceImpl {

	// 注入其他service的Feign接口，以调用其业务方法
	@Autowired
	private EntityCacheServiceFeign entityCacheServiceFeign;

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

	@RequestMapping("/getAllContacts")
	public Mono<String> getAllContacts() {

		return Mono.create(sink-> {
			TSEntity[] entities = entityCacheServiceFeign.getAllEntities();
			StringBuffer res = new StringBuffer();
//			TSEntity[] entities = {new TSEntity(), new TSEntity()};
			for (TSEntity t : entities) {
				res.append(new StringBuffer("Entity id=").append(t.getId()).append("\nEntity name=").append(t.getName()).append("\n"));
			}			
			
			sink.success(res.toString());
		} );
	}
}
