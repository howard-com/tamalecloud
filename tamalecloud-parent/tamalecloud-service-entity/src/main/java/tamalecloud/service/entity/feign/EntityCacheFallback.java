package tamalecloud.service.entity.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import tamalecloud.api.entity.TSEntity;
import tamalecloud.api.service.cache.IEntityCache;

@Component
@RequestMapping("fallback/")
public class EntityCacheFallback implements EntityCacheServiceFeign {

	@Override
	public TSEntity getEntityById(String id) {
		System.out.println("异常处理");
		return null;
	}

	@Override
	public String getAllEntities() {
		System.out.println("异常处理");
		return null;
	}

}
