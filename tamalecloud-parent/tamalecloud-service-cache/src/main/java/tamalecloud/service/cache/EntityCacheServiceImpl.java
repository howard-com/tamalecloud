package tamalecloud.service.cache;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tamalecloud.api.entity.TSEntity;
import tamalecloud.api.service.cache.IEntityCache;
import tamalecloud.service.cache.backstage.CacheData;

//Entity Cache的实现类
@RestController
public class EntityCacheServiceImpl implements IEntityCache {

	CacheData cacheData = CacheData.getCacheData();
	
	@Autowired
	Environment environment;

	@RequestMapping("/getEntityById")
	public TSEntity getEntityById(@RequestParam("id") String id) {
		TSEntity entity = cacheData.getEntityById(id);
		TSEntity res = null;
		if (entity != null) {
			res = new TSEntity();
			res.setId(entity.getId());
			res.setName(entity.getName());
			res.setName(res.getName() + " *Get from cache service[" + this.environment.getProperty("local.server.port") + "]");
		}
		
		return res;
	}

	@RequestMapping("/getAllEntities")
	public String getAllEntities() {
		System.out.println("***调用Cache getAllEntities***");
		
		ArrayList<TSEntity> entities = cacheData.getAllEntities();
		//fakeWorkload();
		StringBuffer resBuf = new StringBuffer("Entity列表：\n");
		for (TSEntity i : entities) {
			resBuf.append("id:" + i.getId()).append(" name:").append(i.getName()).append("\n");
		}
		
		return resBuf.toString();
	}

	@RequestMapping("/addEntity")
	public void addEntity(TSEntity o) {
		cacheData.addEntity(o);
	}
	
	//故意增加访问时间出发降级，熔断
	private void fakeWorkload() {
		System.out.println("***Entity CacheService 开始繁重的处理工作***");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
