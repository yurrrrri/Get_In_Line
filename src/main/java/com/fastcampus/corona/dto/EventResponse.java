package com.fastcampus.corona.dto;

import com.fastcampus.corona.constant.EventStatus;

import java.time.LocalDateTime;

public record EventResponse(
        Long placeId,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDatetime,
        LocalDateTime eventEndDatetime,
        Integer currentNumberOfPeople,
        Integer capacity,
        String memo
) {

    public static EventResponse of(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new EventResponse(
                placeId,
                eventName,
                eventStatus,
                eventStartDatetime,
                eventEndDatetime,
                currentNumberOfPeople,
                capacity,
                memo
        );
    }

    public static EventResponse from(EventDto dto) {
        if (dto == null) { return null; }

        return EventResponse.of(
                dto.getPlaceId(),
                dto.getEventName(),
                dto.getEventStatus(),
                dto.getEventStartDatetime(),
                dto.getEventEndDatetime(),
                dto.getCurrentNumberOfPeople(),
                dto.capacity(),
                dto.memo()
        );
    }

}