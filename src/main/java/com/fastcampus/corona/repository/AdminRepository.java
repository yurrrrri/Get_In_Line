package com.fastcampus.corona.repository;

import com.fastcampus.corona.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
