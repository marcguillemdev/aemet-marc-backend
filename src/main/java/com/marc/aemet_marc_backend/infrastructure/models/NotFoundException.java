package com.marc.aemet_marc_backend.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String message;

}
