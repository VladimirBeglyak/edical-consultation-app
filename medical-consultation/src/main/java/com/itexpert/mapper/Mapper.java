package com.itexpert.mapper;

public interface Mapper<F, T> {

  T mapFrom(F f);
}
