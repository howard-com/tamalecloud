package tamalecloud.service.contact.feign;

import org.springframework.cloud.openfeign.FeignClient;

import tamalecloud.api.service.cache.IEntityCache;

//直接继承service的接口，降低代码重复量。
//服务降级在这个接口实现
@FeignClient(value="${cache_service_name}")
public interface EntityCacheServiceFeign extends IEntityCache {
}