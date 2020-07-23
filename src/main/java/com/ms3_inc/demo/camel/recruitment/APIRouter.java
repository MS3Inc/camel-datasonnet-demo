package com.ms3_inc.demo.camel.recruitment;

import javax.annotation.Generated;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

/**
 * Generated from OpenApi specification by Camel REST DSL generator.
 */
@Generated("org.apache.camel.generator.openapi.PathGenerator")
@Component
public final class APIRouter extends RouteBuilder {
    /**
     * Defines Apache Camel routes using REST DSL fluent API.
     */
    public void configure() {

        restConfiguration().component("undertow");

        rest()
            .post("/recruitment-events")
                .id("POST_recruitment-events")
                .consumes("application/json")
                .produces("application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                .endParam()
                .to("direct:POST_recruitment-events");
    }
}
