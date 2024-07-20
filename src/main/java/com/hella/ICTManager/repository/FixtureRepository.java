package com.hella.ICTManager.repository;

import com.hella.ICTManager.models.entities.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {
}
