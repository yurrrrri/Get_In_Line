package com.fastcampus.corona.repository;

import com.fastcampus.corona.constant.EventStatus;
import com.fastcampus.corona.constant.PlaceType;
import com.fastcampus.corona.domain.Event;
import com.fastcampus.corona.domain.Place;
import com.fastcampus.corona.dto.EventViewResponse;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static com.fastcampus.corona.constant.EventStatus.OPENED;
import static org.assertj.core.api.Assertions.*;

@DisplayName("DB - 이벤트")
@DataJpaTest
class EventRepositoryTest {

    private final EventRepository eventRepository;
    private final TestEntityManager em;

    public EventRepositoryTest(@Autowired EventRepository eventRepository, @Autowired TestEntityManager em) {
        this.eventRepository = eventRepository;
        this.em = em;
    }

    @Test
    void findAll() {
        // given
        Place place = createPlace();
        Event event = createEvent(place);
        em.persist(place);
        em.persist(event);

        // when
        Iterable<Event> events = eventRepository.findAll(new BooleanBuilder());

        // then
        assertThat(events).hasSize(7); // data.sql 데이터도 포함됨
    }

    @Test
    void givenSearchParams_whenFindingEventViewResponse_thenReturnsEventViewResponsePage() {
        // given

        // when
        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                "배드민턴",
                "운동1",
                OPENED,
                LocalDateTime.of(2021, 1, 1, 0, 0, 0),
                LocalDateTime.of(2021, 1, 2, 0, 0, 0),
                PageRequest.of(0, 5)
        );

        // then
        assertThat(eventPage.getTotalPages()).isEqualTo(1);
        assertThat(eventPage.getNumberOfElements()).isEqualTo(1);
        assertThat(eventPage.getContent().get(0))
                .hasFieldOrPropertyWithValue("placeName", "서울 배드민턴장")
                .hasFieldOrPropertyWithValue("eventName", "운동1")
                .hasFieldOrPropertyWithValue("eventStatus", OPENED)
                .hasFieldOrPropertyWithValue("eventStartDatetime", LocalDateTime.of(2021, 1, 1, 9, 0, 0))
                .hasFieldOrPropertyWithValue("eventEndDatetime", LocalDateTime.of(2021, 1, 1, 12, 0, 0));
    }

    private Event createEvent(Place place) {
        return createEvent(place, "test event", EventStatus.ABORTED, LocalDateTime.now(), LocalDateTime.now());
    }

    private Event createEvent(
            Place place,
            String eventName,
            EventStatus eventStatus,
            LocalDateTime eventStartDateTime,
            LocalDateTime eventEndDateTime
    ) {
        return Event.of(
                place,
                eventName,
                eventStatus,
                eventStartDateTime,
                eventEndDateTime,
                0,
                24,
                "마스크 꼭 착용하세요"
        );
    }

    private Place createPlace() {
        return Place.of(PlaceType.COMMON, "test place", "test address", "010-1234-1234", 10, null);
    }
}