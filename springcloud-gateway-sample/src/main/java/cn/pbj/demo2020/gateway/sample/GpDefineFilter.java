package cn.pbj.demo2020.gateway.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @pClassName: GpDefineFilter
 * @author: pengbingjiang
 * @create: 2020/11/18 15:46
 * @description: TODO 自定义GlobalFilter
 *  getOrder表示该过滤器的执行顺序，值越小，执行优先级越高。
 */
@Service
@Slf4j
public class GpDefineFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("[pre]-Enter GpDefineFilter");
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            log.info("[post]-Return Result");
        }));
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
