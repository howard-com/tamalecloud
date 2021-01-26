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

	@RequestMapping("/showEntityById")
	public String showEntityById(@RequestParam("id") String id) {
		System.out.println("showEntityById 线程名称" + Thread.currentThread().getName());
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
	@HystrixCommand(fallbackMethod = "showEntityByIdCallBack")
	@RequestMapping("/showEntityByIdWithHystrix")
	public String showEntityByIdWithHystrix(@RequestParam("id") String id) {
		System.out.println("showEntityByIdWithHystrix 线程名称" + Thread.currentThread().getName());
		return showEntityById(id);
	}
	
	public String showEntityByIdCallBack(String id) {
		return "服务降级提示：showEntityById服务当前不可用";
	}

	@RequestMapping("/getEntityByName")
	public String getEntityByName() {
		return "getEntityById";
	}

	@RequestMapping("/getAllEntities")
	public String getAllEntities() {
		return "getAllEntities";
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
	public String testTimeout() {
        try{
            //睡5秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
		return "此方法调用时间3秒";
	}	
}
