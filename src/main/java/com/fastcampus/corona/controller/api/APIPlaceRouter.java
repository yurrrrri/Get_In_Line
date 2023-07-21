package com.fastcampus.corona.controller.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RouterFunctions.route;

/**
 * 함수형 프로그래밍
 * APIPlaceController와 충돌하므로 실행 시 컨트롤러나 라우터의 어노테이션을 주석 처리한다.
 */
@Configuration
public class APIPlaceRouter {

//    @Bean
//    public RouterFunction<ServerResponse> placeRouter() {
//        return route().nest(path("/api/places"), builder -> builder
//                .GET("", req -> ServerResponse.ok().body(List.of("place1", "place2")))
//                .POST("", req -> ServerResponse.ok().body(true))
//                .GET("/{placeId}", req -> ServerResponse.ok().body("place " + req.pathVariable("placeId")))
//                .PUT("/{placeId}", req -> ServerResponse.ok().body(true))
//                .DELETE("/{placeId}", req -> ServerResponse.ok().body(true))
//        ).build();
//    }

    @Bean
    public RouterFunction<ServerResponse> placeRouter(APIPlaceHandler apiPlaceHandler) {
        return route().nest(path("/api/places"), builder -> builder
                .GET("", apiPlaceHandler::getPlaces)
                .POST("", apiPlaceHandler::createPlace)
                .GET("/{placeId}", apiPlaceHandler::getPlace)
                .PUT("/{placeId}", apiPlaceHandler::modifyPlace)
                .DELETE("/{placeId}", apiPlaceHandler::removePlace)
        ).build();
    }

}
