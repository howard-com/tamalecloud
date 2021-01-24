package infrastructure.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class TamaleBaseGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
	@Override
	public GatewayFilter apply(Object config) {
		// 返回通用过滤器
		return new TamaleBaseGatewayFilter();
	}
}
