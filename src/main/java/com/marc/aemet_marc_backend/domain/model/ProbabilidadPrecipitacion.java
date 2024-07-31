package com.marc.aemet_marc_backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProbabilidadPrecipitacion {
  private int value;
  private String periodo;
}