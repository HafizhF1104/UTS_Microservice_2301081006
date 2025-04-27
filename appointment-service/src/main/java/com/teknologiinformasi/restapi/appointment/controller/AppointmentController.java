package com.teknologiinformasi.restapi.appointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.appointment.model.Appointment;
import com.teknologiinformasi.restapi.appointment.service.AppointmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);

   @Autowired
   private AppointmentService appointmentService;


   // Endpoint untuk mengambil semua produk
   @GetMapping
   public List<Appointment> getAllAppointment() {
    log.info("GET /api/appointment accessed");
       return appointmentService.getAllAppointment();
   }


   // Endpoint untuk mengambil produk berdasarkan id
   @GetMapping("/{id}")
   public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
    log.info("GET /api/appointment/{} accessed", id);
       return appointmentService.getAppointmentById(id)
               .map(appointment -> ResponseEntity.ok().body(appointment))
               .orElse(ResponseEntity.notFound().build());
   }


   // Endpoint untuk membuat produk baru
   @PostMapping
   public Appointment createAppointment(@RequestBody Appointment appointment) {
    log.info("POST /api/appointment accessed with body: {}", appointment);
       return appointmentService.createAppointment(appointment);
   }


   // Endpoint untuk mengupdate produk yang sudah ada
   @PutMapping("/{id}")
   public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) {
    log.info("PUT /api/appointment/{} accessed with body: {}", id, appointmentDetails);
          
    try {
           Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
           return ResponseEntity.ok(updatedAppointment);
       } catch (RuntimeException e) {
        log.warn("PUT /api/appointment/{} failed: {}", id, e.getMessage());
           return ResponseEntity.notFound().build();
       }
   }


//     Endpoint untuk menghapus produk
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAppointment(@PathVariable Long id) {
        log.info("DELETE /api/appointment/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            appointmentService.deleteAppointment(id);
            response.put("message", "Appointment berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Appointment tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/appointment/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}