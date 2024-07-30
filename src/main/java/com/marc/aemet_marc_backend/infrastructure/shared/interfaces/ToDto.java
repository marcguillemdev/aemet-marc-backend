package com.marc.aemet_marc_backend.infrastructure.shared.interfaces;

import java.util.List;

public interface ToDto<T, D> {

  D toDto(T entity);

  List<D> toDto(List<T> entities);
}
