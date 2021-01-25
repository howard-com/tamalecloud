package infrastructure.gateway.protection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonFallbackController {
	@RequestMapping("/error/fallback")
	public Object fallback(){
		return "触发熔断";
	}
	
}