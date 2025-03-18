package com.training.center.repository;

import com.training.center.entity.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {


    //for finding training center through course name
    @Query("SELECT DISTINCT tc FROM TrainingCenter tc JOIN tc.coursesOffered c WHERE c.courseName = :courseName")
    List<TrainingCenter> findByCourseName(@Param("courseName") String courseName);

    //for finding training center through course name but in this we can give 2-3 so based on that it give all possible result
    @Query("SELECT DISTINCT tc FROM TrainingCenter tc JOIN tc.coursesOffered c WHERE LOWER(c.courseName) LIKE LOWER(CONCAT('%', :partialCourseName, '%'))")
    List<TrainingCenter> findByCourseNameContaining(@Param("partialCourseName") String partialCourseName);

    //find training center through its name
    Optional<TrainingCenter> findByCenterName(String centerName);
}
