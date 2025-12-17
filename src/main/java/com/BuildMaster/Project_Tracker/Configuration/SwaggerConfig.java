package com.BuildMaster.Project_Tracker.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI projectTrackerOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Builder Project Tracker Api")
                        .description("API documentation for Project Tracker")
                        .version("1.0"));
    }
}
