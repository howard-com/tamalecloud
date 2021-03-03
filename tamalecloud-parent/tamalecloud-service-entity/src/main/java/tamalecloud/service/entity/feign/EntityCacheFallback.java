package tamalecloud.service.entity.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import tamalecloud.api.entity.TSEntity;

@Component
@RequestMapping("fallback")
public class EntityCacheFallback implements EntityCacheServiceFeign {

	@Override
	public TSEntity getEntityById(String id) {
		System.out.println("***异常处理 Entity Cache service 当前不可用！***");
		return null;
	}

	@Override
	public TSEntity[] getAllEntities() {
		System.out.println("***异常处理 Entity Cache service 当前不可用！***");
		return null;
	}
	
	@Override
	public String getAllEntityNames() {
		System.out.println("***异常处理 Entity Cache service 当前不可用！***");
		return "---Cache service 不可用 请稍后再试---";
	}

}
