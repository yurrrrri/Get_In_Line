package com.fastcampus.corona.dto;

import com.fastcampus.corona.constant.EventStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record EventRequest(
        @NotNull @Positive Long placeId,
        @NotBlank String eventName,
        @NotNull EventStatus eventStatus,
        @NotNull LocalDateTime eventStartDatetime,
        @NotNull LocalDateTime eventEndDatetime,
        @NotNull @PositiveOrZero Integer currentNumberOfPeople,
        @NotNull @Positive Integer capacity,
        String memo
) {

    public static EventRequest of(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime,
            Integer currentNumberOfPeople,
            Integer capacity,
            String memo
    ) {
        return new EventRequest(
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

    public EventDto toDTO() {
        return EventDto.of(
                null,
                null, // TODO : 적절히 고쳐야 사용 가능
                this.eventName(),
                this.eventStatus(),
                this.eventStartDatetime(),
                this.eventEndDatetime(),
                this.currentNumberOfPeople(),
                this.capacity(),
                this.memo(),
                null,
                null
        );
    }
}