package com.itexpert.dto;

import com.itexpert.domain.SlotEntryMeetingType;
import com.itexpert.domain.SlotEntryStatusType;
import java.time.LocalDateTime;

public record SlotEntryDto(
    Long id,
    LocalDateTime startTime,
    SlotEntryStatusType statusType,
    SlotEntryMeetingType meetingType,
    Long doctorId,
    Long patientId
) {

}
