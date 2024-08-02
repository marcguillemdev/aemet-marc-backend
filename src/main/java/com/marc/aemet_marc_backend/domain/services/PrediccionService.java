package com.marc.aemet_marc_backend.domain.services;

import com.marc.aemet_marc_backend.application.dto.PrediccionMunicipioDto;
import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;

public interface PrediccionService {

  /**
   * Retrieves the weather forecast for a specific municipality.
   *
   * @param idMunicipio       The ID of the municipality for which to retrieve the
   *                          forecast.
   * @param unidadTemperatura The unit of temperature to use for the forecast.
   * @return The weather forecast for the specified municipality.
   * @see PrediccionMunicipioDto
   */
  PrediccionMunicipioDto getPrediccionMunicipio(String idMunicipio, UnidadTemperatura unidadTemperatura);
}
