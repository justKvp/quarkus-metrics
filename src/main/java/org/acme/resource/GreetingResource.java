package org.acme.resource;

import io.micrometer.core.annotation.Counted;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.service.GreetingService;

@Path("/hello")
public class GreetingResource {
    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(value = "get.greetings.hello.requests", extraTags = {"extraTag1"})
    public String hello() {
        return greetingService.getHello();
    }
}
