package com.training.center.service.impl;

import com.training.center.dto.AddressDTO;
import com.training.center.dto.CourseDTO;
import com.training.center.dto.TrainingCenterDTO;
import com.training.center.entity.Address;
import com.training.center.entity.Course;
import com.training.center.entity.TrainingCenter;
import com.training.center.repository.TrainingCenterRepository;
import com.training.center.service.TrainingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainingCenterServiceImpl implements TrainingCenterService {

    @Autowired
    private TrainingCenterRepository trainingCenterRepository;

    @Override
    public TrainingCenterDTO createTrainingCenter(TrainingCenterDTO trainingCenterDTO) {
        TrainingCenter trainingCenter = mapToEntity(trainingCenterDTO);
        trainingCenter = trainingCenterRepository.save(trainingCenter);
        return mapToDTO(trainingCenter);
    }

    @Override
    public List<TrainingCenterDTO> getAllTrainingCenters() {
        List<TrainingCenter> centers = trainingCenterRepository.findAll();
        return centers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    @Override
    public TrainingCenterDTO updateTrainingCenter(Long trainingCenterId, TrainingCenterDTO trainingCenterDTO) {
        TrainingCenter existingCenter = trainingCenterRepository.findById(trainingCenterId)
                .orElseThrow(() -> new RuntimeException("Training Center not found"));

        existingCenter.setCenterName(trainingCenterDTO.getCenterName());
        existingCenter.setCenterCode(trainingCenterDTO.getCenterCode());
        existingCenter.setStudentCapacity(trainingCenterDTO.getStudentCapacity());
        existingCenter.setContactEmail(trainingCenterDTO.getContactEmail());
        existingCenter.setContactPhone(trainingCenterDTO.getContactPhone());
        Address addressEntity = mapAddressToEntity(trainingCenterDTO.getAddress());
        existingCenter.setAddress(addressEntity);

        existingCenter = trainingCenterRepository.save(existingCenter);
        return mapToDTO(existingCenter);
    }


    @Override
    public void deleteTrainingCenter(Long trainingCenterId) {
        TrainingCenter existingCenter = trainingCenterRepository.findById(trainingCenterId)
                .orElseThrow(() -> new RuntimeException("Training Center not found"));

        trainingCenterRepository.delete(existingCenter);
    }

    @Override
    public TrainingCenterDTO searchByCenterName(String centerName) {
        TrainingCenter trainingCenter = trainingCenterRepository.findByCenterName(centerName)
                .orElseThrow(() -> new RuntimeException("Training Center not available"));
        return mapToDTO(trainingCenter);
    }


    @Override
    public List<TrainingCenterDTO> searchByCourseName(String courseName) {
        List<TrainingCenter> centers = trainingCenterRepository.findByCourseName(courseName);
        return centers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //for finding training center through course name but in this we can give 2-3 so based on that it give all possible result
    //for example in your dv you have course java and javascript and you type "ja" so it give both java and javascript
    @Override
    public List<TrainingCenterDTO> advancedSearchByCourseName(String partialCourseName) {
        List<TrainingCenter> centers = trainingCenterRepository.findByCourseNameContaining(partialCourseName);
        return centers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private TrainingCenter mapToEntity(TrainingCenterDTO dto) {
        TrainingCenter center = new TrainingCenter();
        center.setCenterName(dto.getCenterName());
        center.setCenterCode(dto.getCenterCode());

        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setDetailedAddress(dto.getAddress().getDetailedAddress());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setPincode(dto.getAddress().getPincode());
            center.setAddress(address);
        }
        center.setStudentCapacity(dto.getStudentCapacity());
        center.setContactEmail(dto.getContactEmail());
        center.setContactPhone(dto.getContactPhone());

        if (dto.getCoursesOffered() != null) {
            Set<Course> courses = dto.getCoursesOffered().stream().map(courseDTO -> {
                Course course = new Course();
                course.setCourseName(courseDTO.getCourseName());
                course.setTrainingCenter(center);
                return course;
            }).collect(Collectors.toSet());

            center.setCoursesOffered(courses);
        }

        return center;
    }

    private TrainingCenterDTO mapToDTO(TrainingCenter center) {
        TrainingCenterDTO dto = new TrainingCenterDTO();
        dto.setCenterName(center.getCenterName());
        dto.setCenterCode(center.getCenterCode());
        if (center.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setDetailedAddress(center.getAddress().getDetailedAddress());
            addressDTO.setCity(center.getAddress().getCity());
            addressDTO.setState(center.getAddress().getState());
            addressDTO.setPincode(center.getAddress().getPincode());
            dto.setAddress(addressDTO);
        }
        dto.setStudentCapacity(center.getStudentCapacity());
        dto.setContactEmail(center.getContactEmail());
        dto.setContactPhone(center.getContactPhone());

        if (center.getCoursesOffered() != null) {
            List<CourseDTO> courseDTOs = center.getCoursesOffered().stream().map(course -> {
                CourseDTO courseDTO = new CourseDTO();
                courseDTO.setCourseName(course.getCourseName());
                courseDTO.setTrainingCenter(center.getTrainingCenterId());
                return courseDTO;
            }).collect(Collectors.toList());

            dto.setCoursesOffered(courseDTOs);
        }
        return dto;
    }

    private Address mapAddressToEntity(AddressDTO dto) {
        return new Address(dto.getDetailedAddress(), dto.getCity(), dto.getState(), dto.getPincode());
    }
}
