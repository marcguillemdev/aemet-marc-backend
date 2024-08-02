package com.marc.aemet_marc_backend.infrastructure.config.property_sources;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropertySourcesPlacerholderConfiguration {

  @Bean
  public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
    properties.setLocation(new FileSystemResource("config.yml"));
    properties.setIgnoreResourceNotFound(false);
    return properties;
  }

}
