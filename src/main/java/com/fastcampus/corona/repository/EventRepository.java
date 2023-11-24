package com.fastcampus.corona.repository;

import com.fastcampus.corona.domain.Event;
import com.fastcampus.corona.domain.QEvent;
import com.fastcampus.corona.repository.querydsl.EventRepositoryCustom;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface EventRepository extends JpaRepository<Event, Long>,
        EventRepositoryCustom,
        QuerydslPredicateExecutor<Event>,
        QuerydslBinderCustomizer<QEvent> {

    @Override
    default void customize(QuerydslBindings bindings, QEvent root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.place.placeName, root.eventName, root.eventStatus, root.eventStartDatetime, root.eventEndDatetime);
        bindings.bind(root.place.placeName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.eventName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.eventStartDatetime).first(ComparableExpression::goe);
        bindings.bind(root.eventEndDatetime).first(ComparableExpression::loe);
    }
}