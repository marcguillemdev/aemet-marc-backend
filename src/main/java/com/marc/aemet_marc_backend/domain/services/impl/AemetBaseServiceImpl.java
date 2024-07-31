package com.marc.aemet_marc_backend.domain.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.marc.aemet_marc_backend.domain.model.AemetBaseResponse;
import com.marc.aemet_marc_backend.domain.services.AemetBaseService;
import com.marc.aemet_marc_backend.infrastructure.config.aemet.AemetConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AemetBaseServiceImpl implements AemetBaseService {

  private final AemetConfig aemetConfig;

  @Override
  public <T> T sendHttpGetRequest(String url, Class<T> responseType) {
    RestTemplate restTemplate = createRestTemplate();
    HttpHeaders headers = getHttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);
    String fullUrl = aemetConfig.getApi().getUrls().getBase() + url;
    log.info("Calling AEMET API with URL: {}", fullUrl);

    AemetBaseResponse response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity, AemetBaseResponse.class)
        .getBody();
    return processObjectResponse(response, responseType, restTemplate, headers);
  }

  private <T> T processObjectResponse(AemetBaseResponse response, Class<T> responseType, RestTemplate restTemplate,
      HttpHeaders headers) {
    String url = response.getDatos();
    log.info("Processing object response from AEMET API with URL: {}", url);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
  }

  @Override
  public <T> List<T> sendHttpGetRequest(String url, ParameterizedTypeReference<List<T>> responseType) {
    RestTemplate restTemplate = createRestTemplate();
    HttpHeaders headers = getHttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);
    String fullUrl = aemetConfig.getApi().getUrls().getBase() + url;
    log.info("Calling AEMET API with URL: {}", fullUrl);

    AemetBaseResponse response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity, AemetBaseResponse.class)
        .getBody();
    return processListResponse(response, responseType, restTemplate, headers);
  }

  private <T> List<T> processListResponse(AemetBaseResponse response, ParameterizedTypeReference<List<T>> responseType,
      RestTemplate restTemplate, HttpHeaders headers) {
    String url = response.getDatos();
    log.info("Processing list response from AEMET API with URL: {}", url);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
  }

  private RestTemplate createRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
    restTemplate.getMessageConverters().add(converter);
    return restTemplate;
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Api_key", aemetConfig.getApi().getKey());
    return headers;
  }

}