package com.fastcampus.corona.integration;

import com.fastcampus.corona.dto.EventDto;
import com.fastcampus.corona.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EventServiceSociableTest {

    @Autowired
    private EventService sut;

    @DisplayName("검색 조건 없이 이벤트를 검색하면, 전체 결과를 출력하여 보여준다.")
    @Test
    void givenNothing_whenSearchingEvents_thenReturnsEntireEventList() {
        // Given

        // When
        List<EventDto> list = sut.getEvents(null);

        // Then
        assertThat(list).isEmpty();
    }

}
