package com.marc.aemet_marc_backend.domain.services;

import java.util.List;

import com.marc.aemet_marc_backend.domain.documents.MunicipioDao;

public interface MunicipioService {

  /**
   * Retrieves a list of MunicipioDao objects.
   *
   * @return a list of MunicipioDao objects
   */
  List<MunicipioDao> getMunicipios();

  /**
   * Retrieves a list of MunicipioDao objects based on the given query.
   * Limited to the first 10 results.
   *
   * @param query the query used to filter the municipios
   * @return a list of MunicipioDao objects that match the query
   * @see MunicipioDao
   */
  List<MunicipioDao> findMunicipiosByQuery(String query);
}
