package com.project.placement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.placement.entity.Placement;
import com.project.placement.repository.PlacementRepository;
import com.project.placement.service.PlacementService;

@Service
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository repo;

    public PlacementServiceImpl(PlacementRepository repo) {
        this.repo = repo;
    }

    @Override
    public Placement savePlacement(Placement p) {
        return repo.save(p);
    }

    @Override
    public Placement getPlacementById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Placement> getAllPlacements() {
        return repo.findAll();
    }

    @Override
    public List<Placement> getPlacementsByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    @Override
    public void deletePlacement(Long id) {
        repo.deleteById(id);
    }
}
