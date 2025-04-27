package com.teknologiinformasi.restapi.doctor.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.doctor.model.Doctor;
 

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}