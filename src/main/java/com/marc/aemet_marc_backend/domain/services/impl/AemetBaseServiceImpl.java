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

/**
 * A service implementation for AEMET base operations.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AemetBaseServiceImpl implements AemetBaseService {

  private final AemetConfig aemetConfig;

  @Override
  public <T> T sendHttpGetRequestAndParseResponse(String url, Class<T> responseType) {
    RestTemplate restTemplate = createRestTemplate();
    HttpHeaders headers = getHttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);
    String fullUrl = aemetConfig.getApi().getUrls().getBase() + url;
    log.info("Calling AEMET API with URL: {}", fullUrl);

    AemetBaseResponse response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity, AemetBaseResponse.class)
        .getBody();
    return processObjectResponse(response, responseType, restTemplate, headers);
  }

  /**
   * Processes the object response from the AEMET API.
   * 
   * @param response     the AemetBaseResponse object containing the response data
   * @param responseType the class type of the response object
   * @param restTemplate the RestTemplate object used for making HTTP requests
   * @param headers      the HttpHeaders object containing the request headers
   * @return the response object of type T
   */
  private <T> T processObjectResponse(AemetBaseResponse response, Class<T> responseType, RestTemplate restTemplate,
      HttpHeaders headers) {
    String url = response.getDatos();
    log.info("Processing object response from AEMET API with URL: {}", url);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
  }

  @Override
  public <T> List<T> sendHttpGetRequestAndParseResponse(String url, ParameterizedTypeReference<List<T>> responseType) {
    RestTemplate restTemplate = createRestTemplate();
    HttpHeaders headers = getHttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(headers);
    String fullUrl = aemetConfig.getApi().getUrls().getBase() + url;
    log.info("Calling AEMET API with URL: {}", fullUrl);

    AemetBaseResponse response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity, AemetBaseResponse.class)
        .getBody();
    return processListResponse(response, responseType, restTemplate, headers);
  }

  /**
   * Processes a list response from the AEMET API.
   * 
   * @param response     The AemetBaseResponse object containing the response
   *                     data.
   * @param responseType The ParameterizedTypeReference representing the type of
   *                     the response.
   * @param restTemplate The RestTemplate used to make the API request.
   * @param headers      The HttpHeaders to be included in the API request.
   * @return A List of objects of type T representing the processed response.
   */
  private <T> List<T> processListResponse(AemetBaseResponse response, ParameterizedTypeReference<List<T>> responseType,
      RestTemplate restTemplate, HttpHeaders headers) {
    String url = response.getDatos();
    log.info("Processing list response from AEMET API with URL: {}", url);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
  }

  /**
   * Creates a new instance of RestTemplate with a custom configuration.
   * 
   * @return the created RestTemplate instance
   */
  private RestTemplate createRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
    restTemplate.getMessageConverters().add(converter);
    return restTemplate;
  }

  /**
   * Returns the HTTP headers for the AemetBaseServiceImpl.
   *
   * @return the HTTP headers containing the "Api_key" header with the configured
   *         API key
   */
  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Api_key", aemetConfig.getApi().getKey());
    return headers;
  }

  @Override
  public Double convertCelsiusToFahrenheit(double celsius) {
    return celsius * 9 / 5 + 32;
  }

}