package com.marc.aemet_marc_backend.domain.services;

import java.util.List;

import com.marc.aemet_marc_backend.domain.documents.Municipio;

public interface MunicipioService {
  List<Municipio> getMunicipios();

  List<Municipio> findMunicipiosByQuery(String query);
}
