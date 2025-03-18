package com.training.center.service;

import com.training.center.dto.TrainingCenterDTO;

import java.util.List;

public interface TrainingCenterService {
    TrainingCenterDTO createTrainingCenter(TrainingCenterDTO trainingCenterDTO);
    List<TrainingCenterDTO> getAllTrainingCenters();
    List<TrainingCenterDTO> searchByCourseName(String courseName);
    List<TrainingCenterDTO> advancedSearchByCourseName(String partialCourseName);
    TrainingCenterDTO updateTrainingCenter(Long trainingCenterId, TrainingCenterDTO trainingCenterDTO);
    void deleteTrainingCenter(Long trainingCenterId);
    TrainingCenterDTO searchByCenterName(String centerName);
}
