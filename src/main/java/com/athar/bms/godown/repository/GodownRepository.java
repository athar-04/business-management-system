package com.athar.bms.godown.repository;

import com.athar.bms.godown.entity.Godown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GodownRepository extends JpaRepository<Godown, Long> {

    boolean existsByName(String name);

    Optional<Godown> findByName(String name);

}