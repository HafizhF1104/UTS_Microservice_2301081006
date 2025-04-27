package com.teknologiinformasi.restapi.appointment.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long idPatient;
    private Long idDoctor;
    private Date tanggal;
    
    // Constructor tanpa parameter
    public Appointment() {}

    // Constructor dengan parameter
    public Appointment(Long idPatient, Long idDoctor, Date tanggal) {
        this.idPatient = idPatient;
        this.idDoctor = idDoctor;
        this.tanggal = tanggal;
    }

    // Getters dan Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdPatient() {
        return idPatient;
    }
    public void setIdPatient(Long idPatient) {
        this.idPatient = idPatient;
    }
    public Long getIdDoctor() {
        return idDoctor;
    }
    public void setIdDoctor(Long idDoctor) {
        this.idDoctor = idDoctor;
    }
    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
}