package org.fleshka4.coursework.controller.api;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.service.DiagnosisService;
import org.fleshka4.coursework.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private WardService wardService;

    @GetMapping
    public ResponseEntity<List<Diagnosis>> getAllDiagnoses() {
        return new ResponseEntity<>(diagnosisService.listDiagnoses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(diagnosisService.findDiagnosis(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/ward/{id}")
    public ResponseEntity<List<Diagnosis>> getDiagnosesInWard(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(diagnosisService.listDiagnosesInWard(wardService.findWard(id)), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Diagnosis createDiagnosis(@RequestBody Diagnosis diagnosis) {
        try {
            return diagnosisService.createDiagnosis(diagnosis);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping(value = "/addwith", produces = MediaType.APPLICATION_JSON_VALUE)
    public Diagnosis createDiagnosis(@RequestParam String name) {
        try {
            return diagnosisService.createDiagnosis(name);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDiagnosisById(@PathVariable("id") Integer id) {
        try {
            diagnosisService.deleteDiagnosisById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
