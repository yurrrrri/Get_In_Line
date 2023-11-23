package com.fastcampus.corona.dto;

import com.fastcampus.corona.constant.EventStatus;

import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        PlaceDto place,
        String eventName,
        EventStatus eventStatus,
        LocalDateTime eventStartDatetime,
        LocalDateTime eventEndDatetime,
        Integer currentNumberOfPeople,
        Integer capacity,
        String memo
) {

    public static EventResponse of(
            Long id,
            PlaceDto place,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new EventResponse(
                id,
                place,
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
                dto.id(),
                dto.placeDto(),
                dto.eventName(),
                dto.eventStatus(),
                dto.eventStartDatetime(),
                dto.eventEndDatetime(),
                dto.currentNumberOfPeople(),
                dto.capacity(),
                dto.memo()
        );
    }

}