package com.fastcampus.corona.service;

import com.fastcampus.corona.constant.EventStatus;
import com.fastcampus.corona.dto.EventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    public List<EventDto> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        return List.of();
    }

    public Optional<EventDto> findEvent(Long eventId) {
        return Optional.empty();
    }

    public boolean createEvent(EventDto dto) {
        return true;
    }

    public boolean modifyEvent(Long eventId, EventDto dto) {
        return true;
    }

    public boolean removeEvent(Long eventId) {
        return true;
    }

}
