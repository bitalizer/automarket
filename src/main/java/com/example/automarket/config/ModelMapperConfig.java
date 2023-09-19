package com.example.automarket.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true);
		modelMapper.getConfiguration().setFieldAccessLevel(PRIVATE);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper;
	}

}