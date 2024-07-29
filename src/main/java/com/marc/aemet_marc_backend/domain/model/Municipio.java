package com.marc.aemet_marc_backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "municipios")
public class Municipio {

  @Id
  private String id;

  private String nombre;

}
