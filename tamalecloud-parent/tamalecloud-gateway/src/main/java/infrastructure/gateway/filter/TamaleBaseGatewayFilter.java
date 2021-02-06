package infrastructure.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class TamaleBaseGatewayFilter implements GatewayFilter, Ordered {

	// 优先级 值越小,优先级越高
	@Override
	public int getOrder() {
		return 0;
	}

	// Filter功能方法
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String path = exchange.getRequest().getPath().toString();
		String userId = exchange.getRequest().getHeaders().getValuesAsList("User-id").get(0);
		
        log.info("***【日志】准备调用服务：" + path + " userId:" + userId + "***");
        return chain.filter(exchange).then(Mono.fromRunnable(() ->
        {
            log.info("***【日志】服务调用完毕 " + path + "***");
        }));
	}

}
 