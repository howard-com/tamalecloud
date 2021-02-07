package infrastructure.gateway;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

public class CustomizeBean {
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
	    return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
	    		//默认超时规则,默认1s,不使用断路器超时规则可以设置大一点
	    		.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
	    		//默认超时规则,断路器规则
	            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
	            .build());
	}
}
