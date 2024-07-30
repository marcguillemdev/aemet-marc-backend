package com.marc.aemet_marc_backend.infrastructure.config.aemet;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "aemet")
public class AemetConfig {

  private Api api;

  @Data
  @AllArgsConstructor
  @RequiredArgsConstructor
  public static class Api {
    private String key;
    private Urls urls;

    @Data
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Urls {
      private String base;
      private String municipios;
      private Prediccion prediccion;
    }

    @Data
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Prediccion {
      private String base;
      private String municipio;
    }
  }

}
