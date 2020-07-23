package com.ms3_inc.demo.camel.recruitment;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ImplementationRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:POST_recruitment-events")
                .transform(datasonnet("resource:classpath:recruitment-model.ds"))
                .log("${body}")
                .transform(datasonnet("{message: 'thank you!'}"));
    }
}
