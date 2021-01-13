package tamalecloud.service.entity.feign;

import org.springframework.cloud.openfeign.FeignClient;

import tamalecloud.api.service.IEntryService;

//直接继承service的接口，降低代码重复量。
//服务降级在这个接口实现
@FeignClient("${entry_service_name}")
public interface EntryServiceFeign extends IEntryService {
}
