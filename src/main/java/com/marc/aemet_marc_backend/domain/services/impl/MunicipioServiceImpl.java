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

/**
 * This class implements the MunicipioService interface and provides the
 * functionality to retrieve and manipulate MunicipioDao objects.
 * It depends on the AemetBaseService, AemetConfig, and MunicipioRepository
 * interfaces for its operations.
 */
@Service
@RequiredArgsConstructor
public class MunicipioServiceImpl implements MunicipioService {

  private final AemetBaseService aemetBaseService;
  private final AemetConfig aemetConfig;
  private final MunicipioRepository municipioRepository;

  @Override
  public List<MunicipioDao> getMunicipios() {
    return aemetBaseService.sendHttpGetRequestAndParseResponse(aemetConfig.getApi().getUrls().getMunicipios(),
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
