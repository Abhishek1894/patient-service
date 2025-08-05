package com.example.patient_service.controller;

import com.example.patient_service.DTO.PatientRequestDTO;
import com.example.patient_service.DTO.PatientResponseDTO;
import com.example.patient_service.exception.EmailAlreadyExitsException;
import com.example.patient_service.exception.PatientNotFoundException;
import com.example.patient_service.service.PatientService;
import com.example.patient_service.validators.CreatePatientValidationGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Patients", description = "API for managing Patients")
public class PatientController
{
    @Autowired
    PatientService patientService;  // field injection

    @GetMapping("")
    @Operation(summary = "Get all patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients()
    {
        List<PatientResponseDTO> list = patientService.getPatients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a patient by id")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable long id) throws PatientNotFoundException
    {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }


    @PostMapping("")
    @Operation(description = "Add a Patient")
    public ResponseEntity<PatientResponseDTO> addPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) throws EmailAlreadyExitsException
    {
        PatientResponseDTO patientResponseDTO = patientService.addPatient(patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(description = "Update a Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable long id, @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) throws PatientNotFoundException
    {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(description = "Delete a Patient")
    public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable long id) throws PatientNotFoundException
    {
        PatientResponseDTO patientResponseDTO = patientService.deletePatient(id);
        return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
    }

}
