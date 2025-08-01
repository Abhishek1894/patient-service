package com.example.patient_service.DTO;

import com.example.patient_service.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO
{
    private long id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;

    public PatientResponseDTO(Patient patient)
    {
        this.id = patient.getId();
        this.name = patient.getName();
        this.email = patient.getEmail();
        this.address = patient.getAddress();
        this.dateOfBirth = patient.getDateOfBirth().toString();
    }
}
