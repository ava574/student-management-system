package com.project.placement.service;

import java.util.List;

import com.project.placement.entity.Placement;

public interface PlacementService {

    Placement savePlacement(Placement p);

    Placement getPlacementById(Long id);

    List<Placement> getAllPlacements();

    List<Placement> getPlacementsByStudent(Long studentId);

    void deletePlacement(Long id);
}
