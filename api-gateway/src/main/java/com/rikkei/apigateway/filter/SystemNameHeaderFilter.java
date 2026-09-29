package com.rikkei.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SystemNameHeaderFilter implements GlobalFilter, Ordered {

    public static final String HEADER_NAME = "X-System-Name";
    public static final String HEADER_VALUE = "Api-Gateway-System";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getResponse().beforeCommit(() -> {
            // set() tranh lap header khi dong thoi bat AddResponseHeader trong properties.
            exchange.getResponse().getHeaders().set(HEADER_NAME, HEADER_VALUE);
            return Mono.empty();
        });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

