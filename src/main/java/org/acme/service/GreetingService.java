package org.acme.service;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class GreetingService {

    @Inject
    MeterRegistry registry;

    private final Timer timer;

    public GreetingService(MeterRegistry registry) {
        this.registry = registry;
        this.timer = registry.timer("my_timer");
    }

    @Timed(value = "greetings.service.method.hello.time", longTask = true, extraTags = {"extraFlag1", "extraFlag2"})
    @Counted(value = "greetings.service.method.hello.count", extraTags = {"extraFlag1", "extraFlag2"})
    public String getHello() {
        long start = System.nanoTime();

        try {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
            Thread.sleep(randomNum * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            long end = System.nanoTime();
            timer.record(end - start, TimeUnit.NANOSECONDS);
        }
        return "Hello from Quarkus REST";
    }
}
