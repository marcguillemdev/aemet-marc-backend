package com.marc.aemet_marc_backend.application.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.aemet_marc_backend.application.dto.MunicipioDto;
import com.marc.aemet_marc_backend.domain.documents.MunicipioDao;
import com.marc.aemet_marc_backend.domain.services.MunicipioService;
import com.marc.aemet_marc_backend.infrastructure.shared.interfaces.ToDto;

import lombok.RequiredArgsConstructor;

/**
 * This class is a controller for Municipio entities.
 * It handles HTTP requests related to Municipio operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/municipios")
public class MunicipioController implements ToDto<MunicipioDao, MunicipioDto> {

  private final MunicipioService municipioService;
  private final ObjectMapper objectMapper;

  /**
   * Retrieves a list of MunicipioDto objects based on the provided municipio
   * query.
   *
   * @param municipio The municipio query string.
   * @return A ResponseEntity containing a list of MunicipioDto objects.
   */
  @GetMapping("/busqueda")
  public ResponseEntity<List<MunicipioDto>> findMunicipiosByQuery(@RequestParam("municipio") String municipio) {
    return ResponseEntity.ok(
        toDto(municipioService.findMunicipiosByQuery(municipio)));
  }

  @Override
  public MunicipioDto toDto(MunicipioDao entity) {
    return objectMapper.convertValue(entity, MunicipioDto.class);
  }

  @Override
  public List<MunicipioDto> toDto(List<MunicipioDao> entities) {
    return objectMapper.convertValue(
        entities,
        objectMapper.getTypeFactory().constructCollectionType(List.class, MunicipioDto.class));
  }
}
