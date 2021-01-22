package tamalecloud.service.cache;

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
		return "EntityCacheServiceImpl/getAllEntities";
	}

	@RequestMapping("/addEntity")
	public void addEntity(TSEntity o) {
		cacheData.addEntity(o);
	}
}
