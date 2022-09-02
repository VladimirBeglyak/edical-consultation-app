package com.itexpert.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<R, C, F> {

  R get(Long id);

  R create(C c);

  void delete(Long id);

  Page<R> getAll(F f, Pageable pageable);

  Page<R> getAll(Pageable pageable);

  R update(C c, Long id);
}
