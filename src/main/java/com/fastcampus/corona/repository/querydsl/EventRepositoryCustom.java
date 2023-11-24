package com.fastcampus.corona.repository.querydsl;

import com.fastcampus.corona.constant.EventStatus;
import com.fastcampus.corona.dto.EventViewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface EventRepositoryCustom {

    Page<EventViewResponse> findEventViewPageBySearchParams(String placeName,
                                                            String eventName,
                                                            EventStatus eventStatus,
                                                            LocalDateTime eventStartDatetime,
                                                            LocalDateTime eventEndDatetime,
                                                            Pageable pageable);
}
