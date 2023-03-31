package com.techie.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<?> techie = Mono.just("techie")
                .then(Mono.error(new RuntimeException("Exception occurred")))
                .log();
        techie.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxString = Flux.just("Spring", "Microservice", "Hibernate")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .concatWithValues("Cloud")
                .log();
        fluxString.subscribe(System.out::println,(e) -> System.out.println(e.getMessage()));
    }
}
