package tamalecloud.service.entity.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EntityCacheFallbackFactory implements FallbackFactory<String> {
	@Override
	public String create(Throwable cause) {
		System.out.println("触发降级方法 EntityCacheCallback， Entity Cache service 当前不可用");
		return (String) "异常信息：Entity cache 不可用";
	}
}
