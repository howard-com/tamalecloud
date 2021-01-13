package tamalecloud.api.service.cache;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tamalecloud.api.entity.TSEntry;

@RequestMapping("/Entry")
public interface IEntryCache {
	@RequestMapping("/getEntryById")
	public TSEntry getEntryById(@RequestParam("id") String id);
	
	@RequestMapping("/getAllEntries")
	public String getAllEntries();
}
