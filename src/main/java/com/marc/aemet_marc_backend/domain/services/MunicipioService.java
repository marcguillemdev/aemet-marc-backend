package com.marc.aemet_marc_backend.domain.services;

import java.util.List;

import com.marc.aemet_marc_backend.domain.documents.MunicipioDao;

public interface MunicipioService {
  List<MunicipioDao> getMunicipios();

  List<MunicipioDao> findMunicipiosByQuery(String query);
}
