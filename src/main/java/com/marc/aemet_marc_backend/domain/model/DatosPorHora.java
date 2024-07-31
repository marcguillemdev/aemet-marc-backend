package com.marc.aemet_marc_backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DatosPorHora {
  private int value;
  private String hora;
}
