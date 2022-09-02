package com.itexpert.controller;

import com.itexpert.domain.SlotEntry;
import com.itexpert.dto.PageResponse;
import com.itexpert.dto.SlotEntryFilter;
import com.itexpert.service.SlotEntryService;
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
@RequestMapping("/slot-entry")
public class SlotEntryController {

  private final SlotEntryService slotEntryService;

  public SlotEntryController(SlotEntryService slotEntryService) {
    this.slotEntryService = slotEntryService;
  }

  @GetMapping("{id}")
  public SlotEntry get(@PathVariable Long id) {
    return slotEntryService.get(id);
  }

  @PostMapping
  public SlotEntry create(@RequestBody SlotEntry slotEntry) {
    return slotEntryService.create(slotEntry);
  }

  @PutMapping("{id}")
  public SlotEntry update(@PathVariable Long id, @RequestBody SlotEntry slotEntry) {
    return slotEntryService.update(slotEntry, id);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    slotEntryService.delete(id);
  }

  @GetMapping("/filter")
  public PageResponse<SlotEntry> getAllByFilter(SlotEntryFilter filter, Pageable pageable) {
    Page<SlotEntry> page = slotEntryService.getAll(filter, pageable);
    return PageResponse.of(page);
  }

  @GetMapping
  public Page<SlotEntry> getAll(Pageable pageable) {
    return slotEntryService.getAll(pageable);
  }
}
