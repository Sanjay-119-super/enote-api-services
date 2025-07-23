package com.sanjay.enote.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ProjectConfig ek configuration class hai jisme hum
 * reusable Spring-managed beans define karte hain.
 *
 * Iska main purpose yeh hota hai ki commonly used objects
 * (jaise ModelMapper) ko ek hi jagah par create karke
 * unko poore application me inject kiya ja sake.
 */
@Configuration
public class ProjectConfig {

    /**
     * Ye method ek single instance (singleton) ModelMapper ka create karta hai.
     *
     * Purpose:
     * - ModelMapper ka use hota hai DTO aur Entity ke objects ke beech
     *   automatic data mapping karne ke liye.
     * - Isse manual setter/getter use karna nahi padta data conversion ke time.
     *
     * Usage:
     * - Jahan bhi ModelMapper chahiye, wahan @Autowired se ye bean inject ho jayegi.
     *
     * Example:
     * Category category = modelMapper.map(categoryDto, Category.class);
     *
     * @return ModelMapper ka instance
     */
    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
