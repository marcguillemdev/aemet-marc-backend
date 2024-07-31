package com.marc.aemet_marc_backend.infrastructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.aemet_marc_backend.application.dto.PrediccionMunicipioDto;
import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;
import com.marc.aemet_marc_backend.domain.model.PrediccionMunicipio;
import com.marc.aemet_marc_backend.domain.services.PrediccionService;
import com.marc.aemet_marc_backend.infrastructure.shared.interfaces.ToDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prediccion")
public class PrediccionController implements ToDto<PrediccionMunicipio, PrediccionMunicipioDto> {

  private final ObjectMapper objectMapper;
  private final PrediccionService prediccionService;

  @GetMapping("/diaria/municipio")
  public ResponseEntity<PrediccionMunicipioDto> getMethodName(
      @RequestParam("idMunicipio") String idMunicipioParam,
      @RequestParam("unidadTemperatura") Optional<UnidadTemperatura> unidadTemperaturaParam) {
    final UnidadTemperatura unidadTemperatura = unidadTemperaturaParam.orElse(UnidadTemperatura.G_CEL);
    return ResponseEntity.ok(
        prediccionService.getPrediccionMunicipio(idMunicipioParam, unidadTemperatura));
  }

  @Override
  public PrediccionMunicipioDto toDto(PrediccionMunicipio entity) {
    return objectMapper.convertValue(entity, PrediccionMunicipioDto.class);
  }

  @Override
  public List<PrediccionMunicipioDto> toDto(List<PrediccionMunicipio> entities) {
    return objectMapper.convertValue(
        entities,
        objectMapper.getTypeFactory().constructCollectionType(List.class, PrediccionMunicipioDto.class));
  }

}