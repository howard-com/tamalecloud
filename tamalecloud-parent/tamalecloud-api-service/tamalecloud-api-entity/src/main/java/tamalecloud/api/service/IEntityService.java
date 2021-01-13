package tamalecloud.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tamalecloud.api.entity.TSEntity;

public interface IEntityService {
	//Show xxx will return in String format
	@RequestMapping("/showEntityById")
	public String showEntityById(@RequestParam("id") String id);
	
	@RequestMapping("/getEntityById")
	public TSEntity getEntityById(@RequestParam("id") String id);
	
	@RequestMapping("/getEntityByName")
	public String getEntityByName();
	
	@RequestMapping("/getAllEntities")
	public String getAllEntities();
}
