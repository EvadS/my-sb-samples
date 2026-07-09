package com.se.sample.repository;

import com.se.sample.entity.Cyberware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CyberwareRepository extends JpaRepository<Cyberware, Long> {
}
