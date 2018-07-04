package com.poslovna;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableTransactionManagement
public class PoslovnaApp implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(PoslovnaApp.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("Content-Type", "Date", "Total-Count", "Authorization")
                .maxAge(3600);
            }
        };
    }
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mm = new ModelMapper();
		return mm;
	}
}
