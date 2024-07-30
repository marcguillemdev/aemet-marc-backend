package com.marc.aemet_marc_backend.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.marc.aemet_marc_backend.domain.documents.Municipio;

@Repository
public interface MunicipioRepository extends MongoRepository<Municipio, String> {

  Municipio findByNombre(String nombre);

  List<Municipio> findFirst10ByNombreContainingIgnoreCase(String nombre);
}
