package com.marc.aemet_marc_backend.domain.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

/**
 * A service interface for AemetBaseService.
 */
public interface AemetBaseService {

  /**
   * Sends an HTTP GET request to the specified URL and parses the response into
   * an object of the specified response type.
   *
   * @param url          the URL to send the HTTP GET request to
   * @param responseType the class representing the type of the response object
   * @param <T>          the type of the response object
   * @return the parsed response object
   */
  <T> T sendHttpGetRequestAndParseResponse(String url, Class<T> responseType);

  /**
   * Sends an HTTP GET request to the specified URL and parses the response into a
   * list of objects of type T.
   *
   * @param url          the URL to send the HTTP GET request to
   * @param responseType the parameterized type reference representing the type of
   *                     objects in the response list
   * @param <T>          the type of objects in the response list
   * @return a list of objects of type T parsed from the HTTP response
   */
  <T> List<T> sendHttpGetRequestAndParseResponse(String url, ParameterizedTypeReference<List<T>> responseType);

  /**
   * Converts a temperature value from Celsius to Fahrenheit.
   *
   * @param celsius the temperature value in Celsius
   * @return the temperature value in Fahrenheit
   */
  Double convertCelsiusToFahrenheit(double celsius);
}
