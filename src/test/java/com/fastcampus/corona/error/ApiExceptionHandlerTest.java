package com.fastcampus.corona.error;

import com.fastcampus.corona.constant.ErrorCode;
import com.fastcampus.corona.dto.APIErrorResponse;
import com.fastcampus.corona.exception.GeneralException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ApiExceptionHandlerTest {

    private ApiExceptionHandler sut;
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        sut = new ApiExceptionHandler();
        webRequest = new DispatcherServletWebRequest(new MockHttpServletRequest());
    }

    @DisplayName("검증 오류 - 응답 데이터 정의")
    @Test
    void givenValidationException_whenHandlingApiException_thenReturnsResponseEntity() {
        // given
        ConstraintViolationException e = new ConstraintViolationException(Set.of());

        // when
        ResponseEntity<Object> response = sut.validation(e, webRequest);

        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(false, ErrorCode.VALIDATION_ERROR, e))
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY)
                .hasFieldOrPropertyWithValue("statusCode", HttpStatus.BAD_REQUEST);
    }

    @DisplayName("프로젝트 일반 오류 - 응답 데이터 정의")
    @Test
    void givenGeneralException_whenHandlingApiException_thenReturnsResponseEntity() {
        // given
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        GeneralException e = new GeneralException(errorCode);

        // when
        ResponseEntity<Object> response = sut.general(e, webRequest);

        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(false, errorCode, e))
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY)
                .hasFieldOrPropertyWithValue("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DisplayName("전체 오류 - 응답 데이터 정의")
    @Test
    void givenOtherException_whenHandlingApiException_thenReturnsResponseEntity() {
        // given
        Exception e = new Exception();

        // when
        ResponseEntity<Object> response = sut.exception(e, webRequest);

        // then
        assertThat(response)
                .hasFieldOrPropertyWithValue("body", APIErrorResponse.of(false, ErrorCode.INTERNAL_ERROR, e))
                .hasFieldOrPropertyWithValue("headers", HttpHeaders.EMPTY)
                .hasFieldOrPropertyWithValue("statusCode", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}