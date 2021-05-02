package com.meli.api.coupon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Configuration for swagger documentation
 *
 * @author stefanny.rodriguez
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String MAINTAINER = "srodriguez2104@gmail.com";
    private static final Set<String> DEFAULT_PRODUCES = new HashSet<>(Arrays.asList("application/json"));
    private static final Set<String> DEFAULT_CONSUMES = new HashSet<>(Arrays.asList("application/json"));

    public static final Contact DEFAULT_CONTACT = new Contact("Stefanny Rodríguez Llanos", MAINTAINER, MAINTAINER);
    public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation for MercadoLibre Coupon API",
            "This API returns a list of items that can be bought with a coupon, without exceeding its amount.",
            "1.0",
            "urn:tos",
            DEFAULT_CONTACT,
            "Stefanny Rodríguez Llanos rights reserved",
            MAINTAINER,
            new ArrayList<>());

    @Bean
    public Docket api() {
        //return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT).select().paths(PathSelectors.any()).build();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT).produces(DEFAULT_PRODUCES).consumes(DEFAULT_CONSUMES);
    }
}
