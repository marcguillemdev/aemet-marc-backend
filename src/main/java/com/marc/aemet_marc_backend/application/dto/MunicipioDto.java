package com.marc.aemet_marc_backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents a MunicipioDto object.
 * 
 * This class is used to store information about a municipality.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MunicipioDto {

  private String id;
  private String nombre;

}
