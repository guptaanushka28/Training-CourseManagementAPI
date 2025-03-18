package com.training.center.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "training_center")
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainingCenterId;

    @NotBlank(message = "Center name is required")
    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;

    @NotBlank(message = "Center Code is required")
    @Size(min = 12, max = 12, message = "Center Code must be exactly 12 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Center Code must be alphanumeric")
    private String centerCode;

    @Embedded
    private Address address;

    @Min(value = 1, message = "Student Capacity must be at least 1")
    private Integer studentCapacity;


    @OneToMany(mappedBy = "trainingCenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> coursesOffered = new HashSet<>();


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact phone must be exactly 10 digits")
    private String contactPhone;

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDateTime.now();    }
}
