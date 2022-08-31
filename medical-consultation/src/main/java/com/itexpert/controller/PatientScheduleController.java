package com.itexpert.controller;

import com.itexpert.domain.PatientSchedule;
import com.itexpert.dto.PageResponse;
import com.itexpert.dto.ScheduleFilter;
import com.itexpert.service.ScheduleService;
import java.time.LocalDateTime;
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
@RequestMapping("schedule/patient")
public class PatientScheduleController {

  private final ScheduleService<PatientSchedule> patientScheduleService;

  public PatientScheduleController(ScheduleService<PatientSchedule> patientScheduleService) {
    this.patientScheduleService = patientScheduleService;
  }

  @GetMapping("{id}")
  public PatientSchedule get(@PathVariable Long id) {
    return patientScheduleService.get(id);
  }

  @PostMapping("{id}")
  public PatientSchedule create(@RequestBody PatientSchedule patientSchedule, @PathVariable Long id) {
    return patientScheduleService.create(patientSchedule, id);
  }

  @PutMapping("{id}")
  public PatientSchedule update(@PathVariable Long id, @RequestBody PatientSchedule patientSchedule) {
    return patientScheduleService.update(patientSchedule, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    patientScheduleService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<PatientSchedule> getAllByFilter(ScheduleFilter filter, Pageable pageable) {
    Page<PatientSchedule> page = patientScheduleService.getAll(filter, pageable);
    return PageResponse.of(page);
  }

  @GetMapping
  public Page<PatientSchedule> getAll(Pageable pageable) {
    return patientScheduleService.getAll(pageable);
  }
}
