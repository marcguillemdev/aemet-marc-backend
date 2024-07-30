package com.marc.aemet_marc_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.marc.aemet_marc_backend.domain.repository.MunicipioRepository;
import com.marc.aemet_marc_backend.domain.services.MunicipioService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableConfigurationProperties
@RequiredArgsConstructor
@EnableMongoRepositories(basePackageClasses = MunicipioRepository.class)
public class AemetMarcBackendApplication {

  private final MunicipioService municipioService;
  private final MunicipioRepository municipioRepository;

  @Value("${client.origin}")
  private String clientOrigin;

  public static void main(String[] args) {
    SpringApplication.run(AemetMarcBackendApplication.class, args);
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(clientOrigin);
      }
    };
  }

  @EventListener(ApplicationReadyEvent.class)
  private void afterSpringBootInit() {
    this.getAndSaveMunicipiosInMongoIfNedeed();
  }

  /**
   * Check if there are municipios in the database, if not, get them from the
   * AEMET API and save them in the database.
   */
  private void getAndSaveMunicipiosInMongoIfNedeed() {
    if (municipioRepository.count() == 0) {
      municipioRepository.saveAll(municipioService.getMunicipios());
    }
  }
}
