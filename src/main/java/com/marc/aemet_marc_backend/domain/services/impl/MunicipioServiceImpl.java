package com.marc.aemet_marc_backend.domain.services.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.marc.aemet_marc_backend.domain.documents.MunicipioDao;
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
  public List<MunicipioDao> getMunicipios() {
    return aemetBaseService.sendHttpGetRequest(aemetConfig.getApi().getUrls().getMunicipios(),
        new ParameterizedTypeReference<List<MunicipioDao>>() {
        });
  }

  @Override
  public List<MunicipioDao> findMunicipiosByQuery(String query) {
    initMunicipiosIfNeeded();
    return municipioRepository.findFirst10ByNombreContainingIgnoreCase(query);
  }

  private void initMunicipiosIfNeeded() {
    if (municipioRepository.count() == 0) {
      municipioRepository.saveAll(getMunicipios());
    }
  }

}
