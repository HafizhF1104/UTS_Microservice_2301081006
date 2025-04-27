package com.teknologiinformasi.restapi.appointment.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.appointment.model.Appointment;
 

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}