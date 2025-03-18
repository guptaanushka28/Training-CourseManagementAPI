package com.training.center.controller;

import com.training.center.dto.TrainingCenterDTO;
import com.training.center.service.TrainingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/training-centers")
public class TrainingCenterController {

    @Autowired
    private TrainingCenterService trainingCenterService;

    @PostMapping
    public ResponseEntity<TrainingCenterDTO> createTrainingCenter(@RequestBody TrainingCenterDTO trainingCenterDTO) {
        TrainingCenterDTO createdCenter = trainingCenterService.createTrainingCenter(trainingCenterDTO);
        return ResponseEntity.ok(createdCenter);
    }

    @GetMapping
    public ResponseEntity<List<TrainingCenterDTO>> getAllTrainingCenters() {
        return ResponseEntity.ok(trainingCenterService.getAllTrainingCenters());
    }

    @PutMapping("/{trainingCenterId}")
    public ResponseEntity<TrainingCenterDTO> updateTrainingCenter(@PathVariable Long trainingCenterId, @RequestBody TrainingCenterDTO trainingCenterDTO) {
        TrainingCenterDTO updatedCenter = trainingCenterService.updateTrainingCenter(trainingCenterId, trainingCenterDTO);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{trainingCenterId}")
    public ResponseEntity<Void> deleteTrainingCenter(@PathVariable Long trainingCenterId) {
        trainingCenterService.deleteTrainingCenter(trainingCenterId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByCenterName")
    public ResponseEntity<TrainingCenterDTO> searchByCenterName(@RequestParam String centerName) {
        TrainingCenterDTO centerDTO = trainingCenterService.searchByCenterName(centerName);
        return ResponseEntity.ok(centerDTO);
    }


    @GetMapping("/searchByCourseName")
    public ResponseEntity<List<TrainingCenterDTO>> searchByCourseName(@RequestParam String courseName) {
        return ResponseEntity.ok(trainingCenterService.searchByCourseName(courseName));
    }
    @GetMapping("/advancedSearchByCourceNam")
    public ResponseEntity<List<TrainingCenterDTO>> advancedSearchByCourseName(@RequestParam String query) {
        return ResponseEntity.ok(trainingCenterService.advancedSearchByCourseName(query));
    }
}
