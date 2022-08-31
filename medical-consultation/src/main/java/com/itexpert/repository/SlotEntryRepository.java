package com.itexpert.repository;

import com.itexpert.domain.SlotEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SlotEntryRepository extends
    JpaRepository<SlotEntry, Long>,
    QuerydslPredicateExecutor<SlotEntry> {

}
