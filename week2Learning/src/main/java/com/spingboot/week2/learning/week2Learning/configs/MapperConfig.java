package com.spingboot.week2.learning.week2Learning.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper getModalMapper(){
        return  new ModelMapper();
    }
}
