package tamalecloud.service.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.theme.SessionThemeResolver;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import tamalecloud.api.entity.TSEntity;
import tamalecloud.service.entity.feign.EntityCacheServiceFeign;

//Entity Service的实现类
@RestController
@RefreshScope
public class EntityServiceImpl {

	// 注入其他service的Feign接口，以调用其业务方法
	@Autowired
	private EntityCacheServiceFeign entityCacheServiceFeign;

	// 绑定配置中心里面的数据
	@Value("${firm_id}")
	private String firm_id;

	@Value("${firm_name}")
	private String firm_name;

	// 业务方法：从返回string类型的 entity信息
	@RequestMapping("/showEntityById")
	public String showEntityById(@RequestParam("id") String id) {
		String res;
		String baseUrl = "[调用类]:%s\n[结果]:\n%s";
		TSEntity entity = entityCacheServiceFeign.getEntityById(id);

		if (entity != null) {
			res = new StringBuffer("Entity id=").append(entity.getId()).append("\nEntity name=").append(entity.getName()).toString();
		} else {
			res = new StringBuffer("未找到id为").append(id).append("的entity").toString();
		}
		
		return String.format(baseUrl, this.getClass().toString(), res);
	}
	
	
	// 开启熔断机制
	// 业务方法：从返回string类型的 entity信息
	// @HystrixCommand(fallbackMethod = "showEntityByIdCallBack")
	@RequestMapping("/showEntityByIdWithHystrix")
	public String showEntityByIdWithHystrix(@RequestParam("id") String id) {
		System.out.println("showEntityByIdWithHystrix 线程名称" + Thread.currentThread().getName());
		return showEntityById(id);
	}
	
	// 业务方法，未添加熔断降级措施
	@RequestMapping("/getEntityByName")
	public String getEntityByName() {
		System.out.println("调用 getEntityByName（未开启熔断） 线程名称：" + Thread.currentThread().getName());
		testTimeout("getEntityByName");
		return "getEntityByName 调用成功";
	}

	// 业务方法，开启熔断降级措施
	@HystrixCommand(fallbackMethod = "getAllEntitiesCallback")
	@RequestMapping("/getAllEntities")
	public String getAllEntities() {
		System.out.println("调用 getAllEntities（开启熔断） 线程名称：" + Thread.currentThread().getName());
		testTimeout("getAllEntities");
		return "getAllEntities 调用成功";
	}
	
	// 服务降级时的回调方法
	public String getAllEntitiesCallback() {
		System.out.println("触发 getAllEntitiesCallback 服务降级方法");
		return "服务降级提示：服务当前不可用。";
	}

	@RequestMapping("/getEntityById")
	public TSEntity getEntityById(String id) {
		return entityCacheServiceFeign.getEntityById(id);
	}

	@RequestMapping("/getFirmInfo")
	public String getFirmInfo() {
		return new StringBuffer("firm_id:").append(firm_id).append("\nfirm_name:").append(firm_name).toString();
	}
	
	@RequestMapping("/timeout")
	public void testTimeout(String input) {
        try{
        	//System.out.println("模拟 " + input + " 服务的工作 线程名称：" + Thread.currentThread().getName());
            //等待一定时间，会触发熔断降级操作
            Thread.sleep(2000);
        }catch (Exception e){
        	//e.printStackTrace();
        }
		return;
	}	
}
