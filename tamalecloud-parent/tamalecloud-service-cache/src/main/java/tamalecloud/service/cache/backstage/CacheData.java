package tamalecloud.service.cache.backstage;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import tamalecloud.api.entity.TSEntity;
import tamalecloud.api.entity.TSEntry;

@Service
public class CacheData {
	static private CacheData cacheData = new CacheData();
	private HashMap<String, TSEntity> entityMap = new HashMap<String, TSEntity>();
	private HashMap<String, TSEntry> entryMap = new HashMap<String, TSEntry>();

	private CacheData() {
	}

	public static CacheData getCacheData() {
		return cacheData;
	}

	public void addEntity(TSEntity o) {
		this.entityMap.put(o.getId(), o);
		return;
	}

	public void addEntry(TSEntry o) {
		this.entryMap.put(o.getId(), o);
		return;
	}

	public TSEntity getEntityById(String id) {
		return this.entityMap.get(id);
	}

	public TSEntry getEntryById(String id) {
		return this.entryMap.get(id);
	}

	public ArrayList<TSEntity> getAllEntities() {
		ArrayList<TSEntity> res = new ArrayList<TSEntity>();

		for (HashMap.Entry<String, TSEntity> entry : entityMap.entrySet()) {
			res.add(entry.getValue());
		}

		return res;
	}

	public ArrayList<TSEntry> getAllEntries() {
		ArrayList<TSEntry> res = new ArrayList<TSEntry>();

		for (HashMap.Entry<String, TSEntry> entry : entryMap.entrySet()) {
			res.add(entry.getValue());
		}

		return res;
	}
}
