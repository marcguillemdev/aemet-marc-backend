package com.marc.aemet_marc_backend.application.dto;

import java.util.List;

import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a DTO (Data Transfer Object) for the prediction of a municipality.
 * This class contains information about the average temperature, temperature
 * unit, and probability of precipitation.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PrediccionMunicipioDto {

  private int temperaturaMedia;
  private UnidadTemperatura unidadTemperatura;
  private List<ProbabilidadPrecipitacionDto> probabilidadPrecipitacion;

}
