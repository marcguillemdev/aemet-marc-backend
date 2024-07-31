package com.marc.aemet_marc_backend.domain.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.marc.aemet_marc_backend.application.dto.PrediccionMunicipioDto;
import com.marc.aemet_marc_backend.application.dto.ProbabilidadPrecipitacionDto;
import com.marc.aemet_marc_backend.domain.enums.UnidadTemperatura;
import com.marc.aemet_marc_backend.domain.model.DatosPorHora;
import com.marc.aemet_marc_backend.domain.model.Dia;
import com.marc.aemet_marc_backend.domain.model.PrediccionMunicipio;
import com.marc.aemet_marc_backend.domain.model.ProbabilidadPrecipitacion;
import com.marc.aemet_marc_backend.domain.services.AemetBaseService;
import com.marc.aemet_marc_backend.domain.services.PrediccionService;
import com.marc.aemet_marc_backend.infrastructure.config.aemet.AemetConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrediccionServiceImpl implements PrediccionService {

  private final AemetBaseService aemetService;
  private final AemetConfig aemetConfig;

  @Override
  public PrediccionMunicipioDto getPrediccionMunicipio(String idMunicipio, UnidadTemperatura unidadTemperatura) {
    final String url = String.format(aemetConfig.getApi().getUrls().getPrediccion().getMunicipio(), idMunicipio);
    List<PrediccionMunicipio> prediccionMunicipios = aemetService.sendHttpGetRequest(url,
        new ParameterizedTypeReference<List<PrediccionMunicipio>>() {
        });

    return transformPrediccion(prediccionMunicipios, unidadTemperatura);
  }

  private PrediccionMunicipioDto transformPrediccion(List<PrediccionMunicipio> prediccionList,
      UnidadTemperatura unidadTemperatura) {

    Dia tomorrow = getTomorrowDay(prediccionList);

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
        .map(probabilidad -> ProbabilidadPrecipitacionDto.builder()
            .periodo(probabilidad.getPeriodo())
            .probabilidad(probabilidad.getValue())
            .build())
        .toList();
  }

  private Double calculateTemperaturaMedia(Dia dia) {
    return dia.getTemperatura().getDato().stream()
        .mapToDouble(DatosPorHora::getValue)
        .average()
        .orElse(0.0);
  }

  private Dia getTomorrowDay(List<PrediccionMunicipio> prediccionMunicipios) {
    return prediccionMunicipios.stream()
        .flatMap(prediccionMunicipio -> prediccionMunicipio.getPrediccion().getDia().stream())
        .filter(dia -> dia.getFecha().after(new Date()))
        .findFirst()
        .orElse(null);
  }

}
