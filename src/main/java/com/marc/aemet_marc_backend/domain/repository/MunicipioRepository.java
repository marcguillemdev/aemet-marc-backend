package com.marc.aemet_marc_backend.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.marc.aemet_marc_backend.domain.documents.MunicipioDao;

/**
 * The MunicipioRepository interface represents a repository for managing
 * MunicipioDao objects.
 * It provides methods for retrieving MunicipioDao objects based on their nombre
 * property.
 */
@Repository
public interface MunicipioRepository extends MongoRepository<MunicipioDao, String> {

  MunicipioDao findByNombre(String nombre);

  List<MunicipioDao> findFirst10ByNombreContainingIgnoreCase(String nombre);
}
