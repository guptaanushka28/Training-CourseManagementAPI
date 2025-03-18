package com.training.center.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCenterDTO {
    @NotBlank(message = "Center Name is required")
    @Size(max = 40, message = "Center Name must be less than 40 characters")
    private String centerName;

    @NotBlank(message = "Center Code is required")
    @Size(min = 12, max = 12, message = "Center Code must be exactly 12 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Center Code must be alphanumeric")
    private String centerCode;

    @NotNull(message = "Address is required")
    private AddressDTO address;

    @Min(value = 1, message = "Student Capacity must be at least 1")
    private Integer studentCapacity;

    private List<CourseDTO> coursesOffered;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @NotBlank(message = "Contact Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be exactly 10 digits")
    private String contactPhone;

}
