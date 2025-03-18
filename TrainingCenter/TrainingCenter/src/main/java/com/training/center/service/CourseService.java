package com.training.center.service;

import com.training.center.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);
    List<CourseDTO> getAllCourses();
    CourseDTO updateCourse(Long courseId, CourseDTO courseDTO);
    void deleteCourse(Long courseId);
}
