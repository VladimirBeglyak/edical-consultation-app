package com.itexpert.controller;

import com.itexpert.domain.DoctorSchedule;
import com.itexpert.dto.PageResponse;
import com.itexpert.dto.ScheduleFilter;
import com.itexpert.service.ScheduleService;
import org.springframework.data.domain.Page;
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
@RequestMapping("/schedule/doctor")
public class DoctorScheduleController {

  private final ScheduleService<DoctorSchedule> doctorScheduleService;

  public DoctorScheduleController(ScheduleService<DoctorSchedule> doctorScheduleService) {
    this.doctorScheduleService = doctorScheduleService;
  }

  @GetMapping("{id}")
  public DoctorSchedule get(@PathVariable Long id) {
    return doctorScheduleService.get(id);
  }

  @PostMapping("{id}")
  public DoctorSchedule create(@RequestBody DoctorSchedule doctorSchedule, @PathVariable Long id) {
    return doctorScheduleService.create(doctorSchedule, id);
  }

  @PutMapping("{id}")
  public DoctorSchedule update(@PathVariable Long id, @RequestBody DoctorSchedule doctorSchedule) {
    return doctorScheduleService.update(doctorSchedule, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    doctorScheduleService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<DoctorSchedule> getAllByFilter(ScheduleFilter filter, Pageable pageable) {
    Page<DoctorSchedule> page = doctorScheduleService.getAll(filter, pageable);
    return PageResponse.of(page);
  }

  @GetMapping
  public Page<DoctorSchedule> getAll(Pageable pageable) {
    return doctorScheduleService.getAll(pageable);
  }
}
