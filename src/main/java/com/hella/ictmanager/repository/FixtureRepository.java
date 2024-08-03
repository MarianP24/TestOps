package com.hella.ictmanager.repository;

import com.hella.ictmanager.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Long> {
}
