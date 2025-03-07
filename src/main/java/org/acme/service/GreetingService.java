package org.acme.service;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class GreetingService {

    @Timed(value = "greetings.service.method.hello.time", longTask = true, extraTags = {"extraFlag1", "extraFlag2"})
    @Counted(value = "greetings.service.method.hello.count", extraTags = {"extraFlag1", "extraFlag2"})
    public String getHello() {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        try {
            Thread.sleep(randomNum * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Hello from Quarkus REST";
    }
}
