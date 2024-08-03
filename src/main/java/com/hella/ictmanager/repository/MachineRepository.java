package com.hella.ictmanager.repository;

import com.hella.ictmanager.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
}
