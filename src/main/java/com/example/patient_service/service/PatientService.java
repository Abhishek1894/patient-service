package com.example.patient_service.service;

import com.example.patient_service.DTO.PatientRequestDTO;
import com.example.patient_service.DTO.PatientResponseDTO;
import com.example.patient_service.model.Patient;
import com.example.patient_service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService
{
    @Autowired
    PatientRepository patientRepository;

    public List<PatientResponseDTO> getPatients()
    {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map((patient -> new PatientResponseDTO(patient))).toList(); // used stream api to simplify the code
    }

    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO)
    {
        Patient patient = new Patient(patientRequestDTO);
        patient = patientRepository.save(patient);
        return new PatientResponseDTO(patient);
    }
}
