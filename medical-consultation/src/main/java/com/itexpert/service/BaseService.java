package com.itexpert.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, F> {

  T get(Long id);

  T create(T t);

  void delete(Long id);

  Page<T> getAll(F f, Pageable pageable);

  Page<T> getAll(Pageable pageable);

  T update(T t, Long id);
}
