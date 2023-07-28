package com.fastcampus.corona.repository;

import com.fastcampus.corona.constant.EventStatus;
import com.fastcampus.corona.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository {

    // TODO : 인스턴스 생성 편의를 위해 임시로 default 사용, Repository layer 구현이 완료되면 삭제
    default List<EventDto> findEvents(
            Long placeId,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDatetime,
            LocalDateTime eventEndDatetime
    ) { return List.of(); }

    default Optional<EventDto> findEvent(Long eventId) { return Optional.empty(); }

    default boolean insertEvent(EventDto eventDTO) { return false; }

    default boolean updateEvent(Long eventId, EventDto dto) { return false; }

    default boolean deleteEvent(Long eventId) { return false; }

}