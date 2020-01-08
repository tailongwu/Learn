package com.wtl.springlearning.webflux;


import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;

public class Reactor {
    public static void main(String[] args) {
        Flux();
    }

    public static void Flux() {
        Flux.fromArray(new Integer[]{1, 2, 3}).subscribe(System.out::print);
        Flux.just(1, 2, 3).subscribe(System.out::print);
        Flux.fromIterable(Arrays.asList(1, 2, 3)).subscribe(System.out::print);

        // map
        StepVerifier.create(Flux.range(1, 3)
                .map(i -> i * i))
                .expectNext(1, 4, 9)
                .verifyComplete();

        // flatMap
        StepVerifier.create(
                Flux.just("reactor", "flux")
                        .flatMap(s -> Flux.fromArray(s.split("\\s*"))
                                .delayElements(Duration.ofMillis(10)))
                        .doOnNext(System.out::print))
                .expectNextCount(11)
                .verifyComplete();

        // filter
        StepVerifier.create(Flux.range(1, 4).filter(i -> i % 2 == 0))
                .expectNext(2, 4)
                .verifyComplete();

        // zip
        StepVerifier.create(Flux.zip(
                Flux.fromIterable(Arrays.asList("This", "is", "sample")),
                Flux.interval((Duration.ofMillis(10))))
                .doOnNext(System.out::print))
                .expectNextCount(3)
                .verifyComplete();
    }
}
