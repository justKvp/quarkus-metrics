package org.acme.service;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    @Timed(value = "greetings.service.method.hello.time", longTask = true, extraTags = {"extraFlag1", "extraFlag2"})
    @Counted(value = "greetings.service.method.hello.count", extraTags = {"extraFlag1", "extraFlag2"})
    public String getHello() {
        return "Hello from Quarkus REST";
    }
}
