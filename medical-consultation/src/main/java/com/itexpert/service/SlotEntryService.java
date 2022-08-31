package com.itexpert.service;

import com.itexpert.domain.SlotEntry;
import com.itexpert.dto.SlotEntryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SlotEntryService {

  SlotEntry get(Long id);

  SlotEntry create(SlotEntry slotEntry);

  void delete(Long id);

  Page<SlotEntry> getAll(SlotEntryFilter filter, Pageable pageable);

  Page<SlotEntry> getAll(Pageable pageable);

  SlotEntry update(SlotEntry userAccount, Long id);
}
