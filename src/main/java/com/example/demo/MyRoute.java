package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.builder.Namespaces;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

    @Override
    public void configure() {
        Namespaces namespaces = new Namespaces();
        namespaces.add("ns", "http://example.com/");
        namespaces.add("ns1", "http://example.com/ns1");
        from("file:input/")
                .log("read file")
                .split().xtokenize("//ns:value/ns1:*", namespaces).streaming()
                .process(exchange -> {
                    log.info(exchange.getMessage().getBody(String.class));
                })
                .end()
                .log("processed file");
    }
}