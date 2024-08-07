package com.marc.aemet_marc_backend.domain.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.marc.aemet_marc_backend.application.dto.PrediccionMunicipioDto;
import com.marc.aemet_marc_backend.application.dto.ProbabilidadPrecipitacionDto;
import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;
import com.marc.aemet_marc_backend.domain.model.DatosPorHora;
import com.marc.aemet_marc_backend.domain.model.Dia;
import com.marc.aemet_marc_backend.domain.model.PrediccionMunicipio;
import com.marc.aemet_marc_backend.domain.model.ProbabilidadPrecipitacion;
import com.marc.aemet_marc_backend.domain.repository.MunicipioRepository;
import com.marc.aemet_marc_backend.domain.services.AemetBaseService;
import com.marc.aemet_marc_backend.domain.services.PrediccionService;
import com.marc.aemet_marc_backend.infrastructure.config.aemet.AemetConfig;
import com.marc.aemet_marc_backend.infrastructure.exceptions.models.NotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * This class implements the PrediccionService interface and provides methods to
 * retrieve weather forecast information for a specific municipality.
 * It uses the AemetBaseService to send HTTP GET requests and parse the
 * response, and the AemetConfig to retrieve API URLs.
 * The PrediccionServiceImpl class is responsible for transforming the retrieved
 * data into a PrediccionMunicipioDto object, which contains the average
 * temperature, precipitation probability, and temperature unit.
 * 
 * @param aemetService        The AemetBaseService used to send HTTP GET
 *                            requests and parse the response.
 * @param aemetConfig         The AemetConfig used to retrieve API URLs.
 * @param municipioRepository The MunicipioRepository used to check if a
 *                            municipality exists.
 */
@Service
@RequiredArgsConstructor
public class PrediccionServiceImpl implements PrediccionService {

  private final AemetBaseService aemetService;
  private final AemetConfig aemetConfig;
  private final MunicipioRepository municipioRepository;

  @Override
  public PrediccionMunicipioDto getPrediccionMunicipio(String idMunicipio, UnidadTemperatura unidadTemperatura) {
    if (!municipioRepository.existsById(idMunicipio)) {
      throw new NotFoundException("No se ha encontrado el municipio con id: " + idMunicipio);
    }
    final String url = String.format(aemetConfig.getApi().getUrls().getPrediccion().getMunicipio(), idMunicipio);
    List<PrediccionMunicipio> prediccionMunicipios = aemetService.sendHttpGetRequestAndParseResponse(url,
        new ParameterizedTypeReference<List<PrediccionMunicipio>>() {
        });
    return transformPrediccion(prediccionMunicipios, unidadTemperatura);
  }

  private PrediccionMunicipioDto transformPrediccion(List<PrediccionMunicipio> prediccionList,
      UnidadTemperatura unidadTemperatura) {

    Dia tomorrow = getTomorrowDay(prediccionList).orElseThrow(
        () -> new RuntimeException("No se ha encontrado la predicción para mañana"));

    double temperaturaMedia = this.calculateTemperaturaMedia(tomorrow);
    if (unidadTemperatura == UnidadTemperatura.G_FAH) {
      temperaturaMedia = aemetService.convertCelsiusToFahrenheit(temperaturaMedia);
    }

    List<ProbabilidadPrecipitacionDto> probabilidadPrecipitacion = this.mapProbabilidadPrecipitacion(
        tomorrow.getProbPrecipitacion());

    return PrediccionMunicipioDto.builder()
        .temperaturaMedia((int) temperaturaMedia)
        .unidadTemperatura(unidadTemperatura)
        .probabilidadPrecipitacion(probabilidadPrecipitacion)
        .build();
  }

  private List<ProbabilidadPrecipitacionDto> mapProbabilidadPrecipitacion(
      List<ProbabilidadPrecipitacion> probabilidadPrecipitacionList) {
    return probabilidadPrecipitacionList.stream()
        .filter(probabilidad -> !List.of("00-24", "00-12", "12-24").contains(probabilidad.getPeriodo()))
        .map(probabilidad -> ProbabilidadPrecipitacionDto.builder()
            .periodo(probabilidad.getPeriodo())
            .probabilidad(probabilidad.getValue())
            .build())
        .collect(Collectors.toList());
  }

  private Double calculateTemperaturaMedia(Dia dia) {
    if (dia.getTemperatura().getDato() == null) {
      return (dia.getTemperatura().getMaxima() + dia.getTemperatura().getMinima()) / 2.0;
    }
    return dia.getTemperatura().getDato().stream()
        .mapToDouble(DatosPorHora::getValue)
        .average()
        .orElse(0.0);
  }

  private Optional<Dia> getTomorrowDay(List<PrediccionMunicipio> prediccionMunicipios) {
    return prediccionMunicipios.stream()
        .flatMap(prediccionMunicipio -> prediccionMunicipio.getPrediccion().getDia().stream())
        .filter(dia -> dia.getFecha().after(new Date()))
        .findFirst();
  }

}
