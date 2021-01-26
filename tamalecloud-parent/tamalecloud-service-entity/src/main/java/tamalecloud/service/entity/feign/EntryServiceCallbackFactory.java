package tamalecloud.service.entity.feign;

import org.springframework.cloud.openfeign.FallbackFactory;

public class EntryServiceCallbackFactory<String> implements FallbackFactory<String> {

	@Override
	public String create(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

}
