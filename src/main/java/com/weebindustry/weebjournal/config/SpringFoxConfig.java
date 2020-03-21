package com.weebindustry.weebjournal.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.weebindustry.weebjournal.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("WeebJournal REST API")
                .description("The REST API of WeebJournal")
                .contact(new Contact("HarryCoder", "https://twitter.com/harrycodertw", "harrycoder1408@gmail.com"))
                .license("MIT LICENSE")
                .licenseUrl("https://choosealicense.com/licenses/mit/")
                .version("1.0.0")
                .build();
    }
}
