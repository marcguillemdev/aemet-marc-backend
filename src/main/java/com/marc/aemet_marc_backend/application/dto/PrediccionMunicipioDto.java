package com.marc.aemet_marc_backend.application.dto;

import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PrediccionMunicipioDto {

  private int temperaturaMedia;
  private UnidadTemperatura unidadTemperatura;
  private PrediccionDto prediccion;

}
