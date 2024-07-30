package com.marc.aemet_marc_backend.domain.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrediccionMunicipio {

  private String nombre;
  private String provincia;
  private Prediccion prediccion;
  private Date elaborado;
  private String id;
  private String version;

}
