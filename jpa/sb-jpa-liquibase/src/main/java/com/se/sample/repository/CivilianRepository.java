package com.se.sample.repository;


import com.se.sample.entity.Civilian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CivilianRepository extends JpaRepository<Civilian, Long> {
}
