package tamalecloud.service.entity.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import tamalecloud.api.entity.TSEntity;

@Component
public class EntityCacheFallbackFactory implements FallbackFactory<TSEntity> {
	@Override
	public TSEntity create(Throwable cause) {
		System.out.println("触发降级方法 EntityCacheCallback， Entity Cache service 当前不可用");
		return null;
	}
}
