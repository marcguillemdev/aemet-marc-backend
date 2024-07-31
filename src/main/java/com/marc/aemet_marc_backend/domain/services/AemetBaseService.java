package com.marc.aemet_marc_backend.domain.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

public interface AemetBaseService {
  <T> T sendHttpGetRequest(String url, Class<T> responseType);

  <T> List<T> sendHttpGetRequest(String url, ParameterizedTypeReference<List<T>> responseType);

  Double convertCelsiusToFahrenheit(double celsius);
}
