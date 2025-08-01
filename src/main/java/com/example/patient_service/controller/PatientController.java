package com.example.patient_service.controller;

import com.example.patient_service.DTO.PatientRequestDTO;
import com.example.patient_service.DTO.PatientResponseDTO;
import com.example.patient_service.exception.EmailAlreadyExitsException;
import com.example.patient_service.exception.PatientNotFoundException;
import com.example.patient_service.service.PatientService;
import com.example.patient_service.validators.CreatePatientValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController
{
    @Autowired
    PatientService patientService;  // field injection

    @GetMapping("")
    public ResponseEntity<List<PatientResponseDTO>> getPatients()
    {
        List<PatientResponseDTO> list = patientService.getPatients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable long id) throws PatientNotFoundException
    {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PatientResponseDTO> addPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) throws EmailAlreadyExitsException
    {
        PatientResponseDTO patientResponseDTO = patientService.addPatient(patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable long id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) throws PatientNotFoundException
    {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable long id) throws PatientNotFoundException
    {
        PatientResponseDTO patientResponseDTO = patientService.deletePatient(id);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
    }

}
