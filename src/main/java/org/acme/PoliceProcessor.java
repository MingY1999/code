package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.common.annotation.Blocking;

@ApplicationScoped
public class PoliceProcessor {
    
    @Incoming("quote-requests")
    public String policMessage(String message){
        System.out.println("@Hello: " + message);
        return message;
    }
}
