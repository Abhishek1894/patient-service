package com.example.patient_service.controller;

import com.example.patient_service.DTO.PatientRequestDTO;
import com.example.patient_service.DTO.PatientResponseDTO;
import com.example.patient_service.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("")
    public ResponseEntity<PatientResponseDTO> addPatient(@Valid  @RequestBody PatientRequestDTO patientRequestDTO)
    {
        PatientResponseDTO patientResponseDTO = patientService.addPatient(patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO,HttpStatus.CREATED);
    }

}
