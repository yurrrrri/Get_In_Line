package com.fastcampus.corona.controller.api;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.ok;

/**
 * 메서드 구현 시 HandlerFunction을 구현하고 메서드 오버라이딩 한 후에 삭제시켜주면 쉽다.
 */
@Component
public class APIPlaceHandler {

    public ServerResponse getPlaces(ServerRequest request) {
        return ok().body(List.of("place1", "place2"));
    }

    // TODO: 1은 구현할 때 제대로 넣기
    public ServerResponse createPlace(ServerRequest request) {
        return created(URI.create("/api/places/1")).body(true);
    }

    public ServerResponse getPlace(ServerRequest request) {
        return ok().body(List.of("place " + request.pathVariable("placeId")));
    }

    public ServerResponse modifyPlace(ServerRequest request) {
        return ok().body(true);
    }

    public ServerResponse removePlace(ServerRequest request) {
        return ok().body(true);
    }

}
