package com.example.patient_service.model;

import com.example.patient_service.DTO.PatientRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate registeredDate;


    public Patient(PatientRequestDTO patientRequestDTO)
    {
        this.name = patientRequestDTO.getName();
        this.email = patientRequestDTO.getEmail();
        this.address = patientRequestDTO.getAddress();
        this.dateOfBirth = LocalDate.parse(patientRequestDTO.getDateOfBirth());
        this.registeredDate = LocalDate.parse(patientRequestDTO.getRegisteredDate());
    }
}
