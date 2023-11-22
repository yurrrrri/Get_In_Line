package com.fastcampus.corona.repository;

import com.fastcampus.corona.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}