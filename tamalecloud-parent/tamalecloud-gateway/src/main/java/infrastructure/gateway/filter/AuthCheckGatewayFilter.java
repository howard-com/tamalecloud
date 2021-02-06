package infrastructure.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class AuthCheckGatewayFilter implements GatewayFilter, Ordered {

	String[] USER_LIST = { "userA", "userB", "userC" };

	// 优先级 值越小,优先级越高
	@Override
	public int getOrder() {
		return 0;
	}

	// Filter功能方法
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		// 1. 获取token
		String token = request.getHeaders().getFirst("token");

		// 2. Token不存在，返回
		if (token == null || (Integer.parseInt(token) < 1 || Integer.parseInt(token) > 3)) {
			response.setStatusCode(HttpStatus.UNAUTHORIZED);

			log.info("***【日志】非法Token.***");
			return response.setComplete();
		}

		// 3. 将用户id传递给后端服务
		ServerWebExchange build;
		int tokenNum = Integer.parseInt(token) - 1;
		ServerHttpRequest host = exchange.getRequest().mutate().header("User-id", USER_LIST[tokenNum]).build();
		build = exchange.mutate().request(host).build();

		log.info("***【日志】验证用户:" + USER_LIST[tokenNum] + "***");
		return chain.filter(build);
	}
}
