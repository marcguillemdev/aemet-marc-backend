package com.marc.aemet_marc_backend.domain.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

public interface AemetBaseService {
  <T> T sendHttpRequest(String url, Class<T> responseType);

  <T> List<T> sendHttpRequest(String url, ParameterizedTypeReference<List<T>> responseType);
}
