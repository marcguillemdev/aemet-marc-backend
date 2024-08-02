package com.marc.aemet_marc_backend.infrastructure.shared.interfaces;

import java.util.List;

/**
 * This interface represents a contract for converting entities to DTOs.
 *
 * @param <T> the type of the entity
 * @param <D> the type of the DTO
 */
public interface ToDto<T, D> {

  /**
   * Converts an entity of type T to a DTO of type D.
   *
   * @param entity the entity to be converted
   * @return the DTO representation of the entity
   */
  D toDto(T entity);

  /**
   * Converts a list of entities to a list of DTOs.
   *
   * @param entities the list of entities to be converted
   * @return the list of DTOs
   */
  List<D> toDto(List<T> entities);
}
