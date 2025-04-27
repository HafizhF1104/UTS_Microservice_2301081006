package com.teknologiinformasi.restapi.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teknologiinformasi.restapi.patient.model.Patient;
import com.teknologiinformasi.restapi.patient.respository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
        patient.setNama(patientDetails.getNama());
        patient.setJenisKelamin(patientDetails.getJenisKelamin());
        patient.setAlamat(patientDetails.getAlamat());
        patient.setNotelp(patientDetails.getNotelp());
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
        patientRepository.delete(patient);
    }
}