package com.example.patient_service.DTO;

import com.example.patient_service.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO
{
    @NotNull(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed more than 100 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Address cannot be blank")
    private String address;

    @NotNull(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class ,message = "Registration date is required")
    private String registeredDate;
}
