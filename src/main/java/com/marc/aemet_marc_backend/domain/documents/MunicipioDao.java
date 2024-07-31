package com.marc.aemet_marc_backend.domain.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "municipios")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipioDao {

  @Id
  private String id;

  private String nombre;
  private String latitud;
  private String url;
  private String altitud;
  private String capital;
  private String destacada;
  private String longitud;

  @JsonProperty("id_old")
  private String idOld;

  @JsonProperty("latitud_dec")
  private String latitudDec;

  @JsonProperty("num_hab")
  private String numHab;

  @JsonProperty("zona_comarcal")
  private String zonaComarcal;

  @JsonProperty("longitud_dec")
  private String longitudDec;

  /**
   * Set the id of the Municipio. We need to remove the first two characters
   * because the id is in the format "id1234".
   * 
   * @param id The id of the Municipio.
   */
  public void setId(String id) {
    this.id = id.substring(2, id.length());
  }

}
