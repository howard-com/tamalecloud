package tamalecloud.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tamalecloud.api.entity.TSEntry;

public interface IEntryService {
	//Show xxx will return in String format
	@RequestMapping("/showEntryById")
	public String showEntryById(@RequestParam("id") String id);
	
	@RequestMapping("/getEntryById")
	public TSEntry getEntryById(String id);
	
	@RequestMapping("/getEntryByName")
	public String getEntryByName();
	
	@RequestMapping("/getAllEntries")
	public String getAllEntries();
}
