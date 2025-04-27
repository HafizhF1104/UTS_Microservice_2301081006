package com.teknologiinformasi.restapi.patient.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.patient.model.Patient;
 

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}