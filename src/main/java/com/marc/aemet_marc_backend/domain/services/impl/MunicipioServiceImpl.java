package com.marc.aemet_marc_backend.domain.services.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.marc.aemet_marc_backend.domain.documents.Municipio;
import com.marc.aemet_marc_backend.domain.repository.MunicipioRepository;
import com.marc.aemet_marc_backend.domain.services.AemetBaseService;
import com.marc.aemet_marc_backend.domain.services.MunicipioService;
import com.marc.aemet_marc_backend.infrastructure.config.aemet.AemetConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

  private final AemetBaseService aemetBaseService;
  private final AemetConfig aemetConfig;
  private final MunicipioRepository municipioRepository;

  @Override
  public List<Municipio> getMunicipios() {
    return aemetBaseService.sendHttpRequest(aemetConfig.getApi().getUrls().getMunicipios(),
        new ParameterizedTypeReference<List<Municipio>>() {
        });
  }

  @Override
  public List<Municipio> findMunicipiosByQuery(String query) {
    initMunicipiosIfNeeded();
    return municipioRepository.findFirst10ByNombreContainingIgnoreCase(query);
  }

  private void initMunicipiosIfNeeded() {
    if (municipioRepository.count() == 0) {
      municipioRepository.saveAll(getMunicipios());
    }
  }

}
