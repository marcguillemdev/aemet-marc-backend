package com.marc.aemet_marc_backend.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Clima {
  private int maxima;
  private int minima;
  private List<DatosPorHora> dato;
}
