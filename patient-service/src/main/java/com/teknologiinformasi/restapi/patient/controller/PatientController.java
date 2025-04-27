package com.teknologiinformasi.restapi.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.patient.model.Patient;
import com.teknologiinformasi.restapi.patient.service.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

   @Autowired
   private PatientService patientService;


   // Endpoint untuk mengambil semua produk
   @GetMapping
   public List<Patient> getAllPatient() {
    log.info("GET /api/patient accessed");
       return patientService.getAllPatient();
   }


   // Endpoint untuk mengambil produk berdasarkan id
   @GetMapping("/{id}")
   public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
    log.info("GET /api/patient/{} accessed", id);
       return patientService.getPatientById(id)
               .map(patient -> ResponseEntity.ok().body(patient))
               .orElse(ResponseEntity.notFound().build());
   }


   // Endpoint untuk membuat produk baru
   @PostMapping
   public Patient createPatient(@RequestBody Patient patient) {
    log.info("POST /api/patient accessed with body: {}", patient);
       return patientService.createPatient(patient);
   }


   // Endpoint untuk mengupdate produk yang sudah ada
   @PutMapping("/{id}")
   public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
    log.info("PUT /api/patient/{} accessed with body: {}", id, patientDetails);
          
    try {
           Patient updatedPatient = patientService.updatePatient(id, patientDetails);
           return ResponseEntity.ok(updatedPatient);
       } catch (RuntimeException e) {
        log.warn("PUT /api/patient/{} failed: {}", id, e.getMessage());
           return ResponseEntity.notFound().build();
       }
   }


//     Endpoint untuk menghapus produk
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePatient(@PathVariable Long id) {
        log.info("DELETE /api/patient/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            patientService.deletePatient(id);
            response.put("message", "Patient berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Patient tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/patient/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}