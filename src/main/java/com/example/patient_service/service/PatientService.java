package com.example.patient_service.service;

import com.example.patient_service.DTO.PatientRequestDTO;
import com.example.patient_service.DTO.PatientResponseDTO;
import com.example.patient_service.exception.EmailAlreadyExitsException;
import com.example.patient_service.exception.PatientNotFoundException;
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


    public PatientResponseDTO getPatientById(long id) throws PatientNotFoundException
    {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null)
            throw new PatientNotFoundException("Patient with id : " + id + " do not exist");

        return new PatientResponseDTO(patient);
    }


    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO) throws EmailAlreadyExitsException
    {
        String email = patientRequestDTO.getEmail();
        if(patientRepository.existsByEmail(email))
            throw new EmailAlreadyExitsException("User with an email id : " + email + " already exists");

        Patient patient = new Patient(patientRequestDTO);
        patient = patientRepository.save(patient);
        return new PatientResponseDTO(patient);
    }


    public PatientResponseDTO updatePatient(long id, PatientRequestDTO patientRequestDTO) throws PatientNotFoundException
    {
        Patient patient = patientRepository.findById(id).orElse(null);

        if(patient == null)
            throw new PatientNotFoundException("Patient with id : " + id + " do not exits");

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        return new PatientResponseDTO(patientRepository.save(patient));

    }


    public PatientResponseDTO deletePatient(long id) throws PatientNotFoundException
    {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null)
            throw new PatientNotFoundException("Patient with id : " + id + " not found");

        patientRepository.deleteById(id);
        return new PatientResponseDTO(patient);
    }

}
