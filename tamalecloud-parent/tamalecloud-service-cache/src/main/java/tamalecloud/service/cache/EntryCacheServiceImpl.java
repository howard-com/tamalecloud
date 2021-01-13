package tamalecloud.service.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tamalecloud.api.entity.TSEntry;
import tamalecloud.api.service.cache.IEntryCache;
import tamalecloud.service.cache.backstage.CacheData;

//Entity Cache的实现类
@RestController
public class EntryCacheServiceImpl implements IEntryCache {

	CacheData cacheData = CacheData.getCacheData();
	
	@Autowired
	Environment environment;
	
	public TSEntry getEntryById(@RequestParam("id") String id) {
		TSEntry entry = cacheData.getEntryById(id);
		TSEntry res = null;
		if (entry != null) {
			res = new TSEntry();
			res.setId(entry.getId());
			res.setName(entry.getName());
			res.setEntity(entry.getEntity());
			//res.setName(res.getName() + " *Get from cache service[" + this.environment.getProperty("local.server.port") + "]");
		}
		
		return res;
	}

	public String getAllEntries() {
		return "EntryCacheServiceImpl/getAllEntries";
	}
}
