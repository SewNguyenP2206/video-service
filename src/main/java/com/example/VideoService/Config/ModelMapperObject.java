package com.example.VideoService.Config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperObject {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
