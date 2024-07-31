package com.marc.aemet_marc_backend.application.dto;

import java.util.List;

import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class PrediccionMunicipioDto {

  private int temperaturaMedia;
  private UnidadTemperatura unidadTemperatura;
  private List<ProbabilidadPrecipitacionDto> probabilidadPrecipitacion;

}
