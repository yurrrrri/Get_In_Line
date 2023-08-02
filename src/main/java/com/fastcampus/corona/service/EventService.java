package com.fastcampus.corona.service;

import com.fastcampus.corona.constant.ErrorCode;
import com.fastcampus.corona.constant.EventStatus;
import com.fastcampus.corona.dto.EventDto;
import com.fastcampus.corona.exception.GeneralException;
import com.fastcampus.corona.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDto> getEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) {
        try {
            return eventRepository.findEvents(
                    placeId,
                    eventName,
                    eventStatus,
                    eventStartDatetime,
                    eventEndDatetime
            );
        } catch (Exception e) {
            throw new GeneralException(ErrorCode.DATA_ACCESS_ERROR, e);
        }
    }

    public Optional<EventDto> getEvent(Long eventId) {
        return eventRepository.findEvent(eventId);
    }

    public boolean createEvent(EventDto eventDto) {
        return eventRepository.insertEvent(eventDto);
    }

    public boolean modifyEvent(Long eventId, EventDto dto) {
        return eventRepository.updateEvent(eventId, dto);
    }

    public boolean removeEvent(Long eventId) {
        return eventRepository.deleteEvent(eventId);
    }
}