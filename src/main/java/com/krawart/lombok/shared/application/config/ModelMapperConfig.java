package com.krawart.lombok.shared.application.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        var modelMapper = new ModelMapper();

        /*
         * ModelMapper configuration
         *
         * MatchingStrategy = to strictly use exact field names
         */
        modelMapper
                .getConfiguration()
                .setSkipNullEnabled(true) // Handy when mapping from partial empty DTO to fetched Entity
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.validate();

        return modelMapper;
    }
}
