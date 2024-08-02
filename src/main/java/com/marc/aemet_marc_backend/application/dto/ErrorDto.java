package com.marc.aemet_marc_backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represents an error response.
 * 
 * This class is used to encapsulate error information and provide it as a
 * response to clients.
 * It contains a message and a code to describe the error.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorDto {
  private String mensaje;
  private int codigo;
}
