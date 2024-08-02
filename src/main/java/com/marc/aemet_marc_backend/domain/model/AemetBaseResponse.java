package com.marc.aemet_marc_backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a base response from the Aemet API.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AemetBaseResponse {

  private String descripcion;
  private int estado;
  private String datos;
  private String metadatos;

}
