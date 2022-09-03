package com.itexpert.controller;

import com.itexpert.dto.PageResponse;
import com.itexpert.dto.SlotEntryDto;
import com.itexpert.dto.SlotEntryFilter;
import com.itexpert.service.SlotEntryService;
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
@RequestMapping("/slot-entry")
public class SlotEntryController {

  private final SlotEntryService slotEntryService;

  public SlotEntryController(SlotEntryService slotEntryService) {
    this.slotEntryService = slotEntryService;
  }

  @GetMapping("{id}")
  public SlotEntryDto get(@PathVariable Long id) {
    return slotEntryService.get(id);
  }

  @PostMapping
  public SlotEntryDto create(@RequestBody SlotEntryDto slotEntry) {
    return slotEntryService.create(slotEntry);
  }

  @PutMapping("{id}")
  public SlotEntryDto update(@PathVariable Long id, @RequestBody SlotEntryDto slotEntryDto) {
    return slotEntryService.update(slotEntryDto, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    slotEntryService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<SlotEntryDto> getAllByFilter(SlotEntryFilter filter, Pageable pageable) {
    return PageResponse.of(slotEntryService.getAll(filter, pageable));
  }

  @GetMapping
  public PageResponse<SlotEntryDto> getAll(Pageable pageable) {
    return PageResponse.of(slotEntryService.getAll(pageable));
  }
}
