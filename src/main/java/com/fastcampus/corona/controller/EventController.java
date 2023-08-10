package com.fastcampus.corona.controller;

import com.fastcampus.corona.constant.EventStatus;
import com.fastcampus.corona.dto.EventResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/events")
@Controller
public class EventController {

    @GetMapping("/")
    public ModelAndView events() {
        Map<String, Object> map = new HashMap<>();

        // TODO : 임시 데이터
        map.put("events", List.of(EventResponse.of(
                        1L,
                        1L,
                        "오후 운동",
                        EventStatus.OPENED,
                        LocalDateTime.of(2023, 1, 1, 13, 0, 0),
                        LocalDateTime.of(2023, 1, 1, 16, 0, 0),
                        0,
                        24,
                        "마스크 꼭 착용하세요."
                ), EventResponse.of(
                        2L,
                        1L,
                        "오후 운동",
                        EventStatus.OPENED,
                        LocalDateTime.of(2023, 1, 1, 13, 0, 0),
                        LocalDateTime.of(2023, 1, 1, 16, 0, 0),
                        0,
                        24,
                        "마스크 꼭 착용하세요."
                )
        ));

        return new ModelAndView("event/index", map);
    }

    @GetMapping("/{eventId}")
    public ModelAndView eventDetail(@PathVariable Integer eventId) {
        Map<String, Object> map = new HashMap<>();

        // TODO : 임시 데이터
        map.put("event", EventResponse.of(
                1L,
                1L,
                "오후 운동",
                EventStatus.OPENED,
                LocalDateTime.of(2023, 1, 1, 13, 0, 0),
                LocalDateTime.of(2023, 1, 1, 16, 0, 0),
                0,
                24,
                "마스크 꼭 착용하세요."
        ));

        return new ModelAndView("event/detail", map);
    }

}
