package com.itexpert.controller;

import com.itexpert.dto.PageResponse;
import com.itexpert.dto.ScheduleDto;
import com.itexpert.dto.ScheduleFilter;
import com.itexpert.service.ScheduleService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

  private final ScheduleService doctorScheduleService;

  public ScheduleController(ScheduleService doctorScheduleService) {
    this.doctorScheduleService = doctorScheduleService;
  }

  @GetMapping("{id}")
  public ScheduleDto get(@PathVariable Long id) {
    return doctorScheduleService.get(id);
  }

  @PostMapping
  public ScheduleDto create(@RequestBody ScheduleDto scheduleDto) {
    return doctorScheduleService.create(scheduleDto);
  }

  @PutMapping("{id}")
  public ScheduleDto update(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
    return doctorScheduleService.update(scheduleDto, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    doctorScheduleService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<ScheduleDto> getAllByFilter(ScheduleFilter filter, Pageable pageable) {
    return PageResponse.of(doctorScheduleService.getAll(filter, pageable));
  }

  @GetMapping
  public PageResponse<ScheduleDto> getAll(Pageable pageable) {
    return PageResponse.of(doctorScheduleService.getAll(pageable));
  }
}
