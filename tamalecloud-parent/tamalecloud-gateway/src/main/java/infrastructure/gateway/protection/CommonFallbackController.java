package infrastructure.gateway.protection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonFallbackController {
	@RequestMapping("/error/fallback")
	public Object fallback(){
		return "***这是网关熔断页面，当前服务不可用，请稍候再试***";
	}
	
}