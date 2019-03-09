package com.xxx.api.service.async;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture: async usage
 * @ClassName AsyncService
 * @Description Async Service
 * @Author e079726
 * @Date 2019-02-14 16:38
 * @Version
 * @since JDK 1.8
 **/
public interface AsyncService {
    CompletableFuture<String> sayHello(String name);
}
