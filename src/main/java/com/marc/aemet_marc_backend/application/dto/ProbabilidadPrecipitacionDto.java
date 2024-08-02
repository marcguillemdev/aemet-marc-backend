package com.marc.aemet_marc_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a data transfer object for probability of precipitation.
 */
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProbabilidadPrecipitacionDto {

  private int probabilidad;
  private String periodo;

}
