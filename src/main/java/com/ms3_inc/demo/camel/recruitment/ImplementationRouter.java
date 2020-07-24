package com.ms3_inc.demo.camel.recruitment;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static org.apache.camel.component.jdbc.JdbcConstants.JDBC_PARAMETERS;

@Component
public class ImplementationRouter extends RouteBuilder {
    private static final String INSERT_PROSPECT =
            "insert into prospects " +
                "(date, name, college, phone, email, recruiter) " +
                "values (:?date, :?name, :?college, :?phone, :?email, :?recruiter)";

    @Override
    public void configure() throws Exception {
        from("direct:POST_recruitment-events")
                .transform(datasonnet("resource:classpath:prospects-array.ds", "application/json", "application/java"))
                .split(body())
                    .log("${body}")
                    .setHeader(JDBC_PARAMETERS, body())
                    .setBody(constant(INSERT_PROSPECT))
                    .to("jdbc:datasource?useHeadersAsParameters=true")
                    .setBody(header(JDBC_PARAMETERS))
                    .removeHeader(JDBC_PARAMETERS)
                    .choice()
                        .when(datasonnet("'phone' in payload && payload.phone != null", "application/java", "application/java"))
                            .transform(datasonnet("DS.Util.removeAll(payload, ['recruiter', 'date', 'email'])", "application/java", "application/json"))
                            .log("${body}")
                            .to(ExchangePattern.InOnly, "rabbitmq:call-log")
                    .end()
                .end()
                .setBody(datasonnet("{message: 'thank you!'}"));
    }
}
