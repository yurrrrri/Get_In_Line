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
import org.springframework.dao.InvalidDataAccessApiUsageException;
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

    @DisplayName("이벤트 뷰 데이터 검색어에 따른 조회 결과가 없으면, 빈 데이터를 페이징 정보와 함께 리턴한다.")
    @Test
    void givenSearchParams_whenFindingNonexistentEventViewPage_thenReturnsEmptyEventViewResponsePage() {
        // Given

        // When
        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                "없는 장소",
                "없는 이벤트",
                null,
                LocalDateTime.of(1000, 1, 1, 1, 1, 1),
                LocalDateTime.of(1000, 1, 1, 1, 1, 0),
                PageRequest.of(0, 5)
        );

        // Then
        assertThat(eventPage).hasSize(0);
    }

    @DisplayName("이벤트 뷰 데이터를 검색 파라미터 없이 페이징 값만 주고 조회하면, 전체 데이터를 페이징 처리하여 리턴한다.")
    @Test
    void givenPagingInfoOnly_whenFindingEventViewPage_thenReturnsEventViewResponsePage() {
        // Given

        // When
        Page<EventViewResponse> eventPage = eventRepository.findEventViewPageBySearchParams(
                null,
                null,
                null,
                null,
                null,
                PageRequest.of(0, 5)
        );

        // Then
        assertThat(eventPage).hasSize(5);
    }

    @DisplayName("이벤트 뷰 데이터를 페이징 정보 없이 조회하면, 에러를 리턴한다.")
    @Test
    void givenNothing_whenFindingEventViewPage_thenThrowsError() {
        // Given

        // When
        Throwable t = catchThrowable(() -> eventRepository.findEventViewPageBySearchParams(
                null,
                null,
                null,
                null,
                null,
                null
        ));

        // Then
        assertThat(t).isInstanceOf(InvalidDataAccessApiUsageException.class);
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