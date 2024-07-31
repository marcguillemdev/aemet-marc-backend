package com.marc.aemet_marc_backend.domain.services;

import com.marc.aemet_marc_backend.application.dto.PrediccionMunicipioDto;
import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;

public interface PrediccionService {
  PrediccionMunicipioDto getPrediccionMunicipio(String idMunicipio, UnidadTemperatura unidadTemperatura);
}
