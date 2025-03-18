package com.training.center.service.impl;

import com.training.center.dto.CourseDTO;
import com.training.center.entity.Course;
import com.training.center.entity.TrainingCenter;
import com.training.center.repository.CourseRepository;
import com.training.center.repository.TrainingCenterRepository;
import com.training.center.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TrainingCenterRepository trainingCenterRepository;

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());

        if (courseDTO.getTrainingCenter() != null) {
            TrainingCenter trainingCenter = trainingCenterRepository.findById(courseDTO.getTrainingCenter())
                    .orElseThrow(() -> new RuntimeException("Training Center not found"));
            course.setTrainingCenter(trainingCenter);
        }

        courseRepository.save(course);
        return courseDTO;
    }


    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    CourseDTO courseDTO = new CourseDTO();
                    courseDTO.setCourseName(course.getCourseName());
                    if (course.getTrainingCenter() != null) {
                        courseDTO.setTrainingCenter(course.getTrainingCenter().getTrainingCenterId());
                    }
                    return courseDTO;
                })
                .collect(Collectors.toList());
    }
    @Override
    public CourseDTO updateCourse(Long courseId, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCourse.setCourseName(courseDTO.getCourseName());

        if (courseDTO.getTrainingCenter() != null) {
            TrainingCenter trainingCenter = trainingCenterRepository.findById(courseDTO.getTrainingCenter())
                    .orElseThrow(() -> new RuntimeException("Training Center not found"));
            existingCourse.setTrainingCenter(trainingCenter);
        }

        courseRepository.save(existingCourse);
        return courseDTO;
    }
    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
    }
}
