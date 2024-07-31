package com.marc.aemet_marc_backend.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Viento extends DatosBase {
  private String direccion;
  private int velocidad;
}
