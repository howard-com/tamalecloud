package tamalecloud.service.contact.feign;

import org.springframework.web.bind.annotation.RequestParam;

import feign.Headers;
import feign.RequestLine;
import reactor.core.publisher.Mono;
import tamalecloud.api.entity.TSEntity;

//@FeignClient(value="${cache_service_name}")
@Headers({ "Accept: application/json" })
public interface ReactorEntityCacheServiceFeign {
	@RequestLine("GET /Entity/getEntityById")
	public Mono<TSEntity> getEntityById(@RequestParam("id") String id);

	@RequestLine("GET /Entity/getAllEntities")
	public Mono<TSEntity[]> getAllEntities();
	
	@RequestLine("GET /Entity/getAllEntityNames")
	public Mono<String> getAllEntityNames();
}
