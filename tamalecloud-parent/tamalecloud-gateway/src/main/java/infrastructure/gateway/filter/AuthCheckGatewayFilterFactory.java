package infrastructure.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthCheckGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

	@Override
	public GatewayFilter apply(Object config) {
		// 返回身份认证过滤器
		return new AuthCheckGatewayFilter();
	}

}
