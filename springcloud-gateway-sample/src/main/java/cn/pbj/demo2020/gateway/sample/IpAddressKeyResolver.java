package cn.pbj.demo2020.gateway.sample;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @pClassName: IpAddressKeyResolver
 * @author: pengbingjiang
 * @create: 2020/11/18 15:25
 * @description: TODO KeyResolver接口主要用于设置限流请求的key
 */
@Service
public class IpAddressKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
