package com.itexpert.dto;

import java.time.LocalDateTime;

public record ScheduleDto(
    Long id,
    LocalDateTime createAt,
    String description,
    UserAccountDto userAccount
) {

  public ScheduleDto(Long id, LocalDateTime createAt, UserAccountDto userAccountDto) {
    this(id, createAt, null, userAccountDto);
  }
}
