package com.marc.aemet_marc_backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorDto {
  private String mensaje;
  private int codigo;
}
