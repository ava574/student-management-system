package com.project.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.placement.entity.Placement;

public interface PlacementRepository extends JpaRepository<Placement, Long> {

    List<Placement> findByStudentId(Long studentId);
}
