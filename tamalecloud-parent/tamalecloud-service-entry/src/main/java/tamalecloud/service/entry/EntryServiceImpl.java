package tamalecloud.service.entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tamalecloud.api.entity.TSEntity;
import tamalecloud.api.entity.TSEntry;
import tamalecloud.api.service.IEntryService;
import tamalecloud.service.entry.feign.EntityServiceFeign;
import tamalecloud.service.entry.feign.EntryCacheServiceFeign;

//Entry Service的实现类
@RestController

public class EntryServiceImpl implements IEntryService {

	// 注入其他service的Feign接口，以调用其业务方法
	@Autowired
	private EntityServiceFeign entityServiceFeign;

	@Autowired
	private EntryCacheServiceFeign entryCacheServiceFeign;

	@RequestMapping("/showEntryById")
	public String showEntryById(@RequestParam("id") String id) {
		String res;
		String baseUrl = "[调用类]:%s\n[结果]:\n%s";
		TSEntry entry = entryCacheServiceFeign.getEntryById(id);

		if (entry != null) {
			TSEntity entity = entityServiceFeign.getEntityById(id);
			entry.setEntity(entity);

			String entryStrTmp = "Entry id=%s,\nEntry name=%s,\nEntity id=%s,\nEntity Name=%s";
			res = String.format(entryStrTmp, entry.getId(), entry.getName(), entry.getEntity().getId(),
					entry.getEntity().getName());
		} else {
			res = new StringBuffer("未找到id为").append(id).append("的entry").toString();
		}

		return String.format(baseUrl, this.getClass().toString(), res);
	}

	@RequestMapping("/getEntryById")
	public TSEntry getEntryById(@RequestParam("id") String id) {
		TSEntry entry = entryCacheServiceFeign.getEntryById(id);

		if (entry != null) {
			TSEntity entity = entityServiceFeign.getEntityById(id);
			entry.setEntity(entity);
		}

		return entry;
	}

	@RequestMapping("/getEntryByName")
	public String getEntryByName() {
		return "getEntryById";
	}

	@RequestMapping("/getAllEntries")
	public String getAllEntries() {
		return "getAllEntries";
	}
}
