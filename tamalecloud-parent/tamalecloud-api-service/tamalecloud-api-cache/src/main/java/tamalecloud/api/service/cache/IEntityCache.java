package tamalecloud.api.service.cache;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tamalecloud.api.entity.TSEntity;

@RequestMapping("/Entity")
public interface IEntityCache {
	@RequestMapping("/getEntityById")
	public TSEntity getEntityById(@RequestParam("id") String id);

	@RequestMapping("/getAllEntities")
	public TSEntity[] getAllEntities();
	
	@RequestMapping("/getAllEntityNames")
	public String getAllEntityNames();
}
