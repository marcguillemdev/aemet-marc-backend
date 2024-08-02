package com.marc.aemet_marc_backend.application.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marc.aemet_marc_backend.application.dto.PrediccionMunicipioDto;
import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;
import com.marc.aemet_marc_backend.domain.services.PrediccionService;

import lombok.RequiredArgsConstructor;

/**
 * This class is a controller for Prediccion entities.
 * It handles HTTP requests related to Prediccion operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/prediccion")
public class PrediccionController {

  private final PrediccionService prediccionService;

  /**
   * Retrieves the daily weather forecast for a specific municipality.
   *
   * @param idMunicipioParam       The ID of the municipality.
   * @param unidadTemperaturaParam (Optional) The temperature unit to use for the
   *                               forecast. If not provided, the default unit is
   *                               Celsius.
   * @return The weather forecast for the municipality.
   */
  @GetMapping("/diaria/municipio")
  public ResponseEntity<PrediccionMunicipioDto> getMunicipalityForecast(
      @RequestParam("idMunicipio") String idMunicipioParam,
      @RequestParam("unidad") Optional<UnidadTemperatura> unidadTemperaturaParam) {
    final UnidadTemperatura unidadTemperatura = unidadTemperaturaParam.orElse(UnidadTemperatura.G_CEL);
    return ResponseEntity.ok(
        prediccionService.getPrediccionMunicipio(idMunicipioParam, unidadTemperatura));
  }

}