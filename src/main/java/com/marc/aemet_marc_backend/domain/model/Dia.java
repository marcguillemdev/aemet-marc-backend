package com.marc.aemet_marc_backend.domain.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Dia {

  private Date fecha;
  private int uvMax;
  private List<ProbabilidadPrecipitacion> probPrecipitacion;
  private List<CotaNieveProv> cotaNieveProv;
  private List<EstadoCielo> estadoCielo;
  private List<Viento> viento;
  private List<RachaMaxima> rachaMax;
  private Clima temperatura;
  private Clima sensTermica;
  private Clima humedadRelativa;
}
