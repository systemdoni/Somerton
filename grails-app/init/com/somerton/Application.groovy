package com.somerton

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@CompileStatic
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean
    static WebMvcConfigurer webConfigurer() {
        new WebMvcConfigurer() {
            @Override
            void addResourceHandlers(ResourceHandlerRegistry registry) {
                if (!registry.hasMappingForPattern("/webjars/**")) {
                    registry
                            .addResourceHandler("/webjars/**")
                            .addResourceLocations("classpath:/META-INF/resources/webjars/")
                }
                if (!registry.hasMappingForPattern("/uploads/**")) {
                    registry
                            .addResourceHandler("/uploads/**")
                            .addResourceLocations("file:C:\\Users\\lushi\\uploads\\")
                }
            }
        }
    }

}