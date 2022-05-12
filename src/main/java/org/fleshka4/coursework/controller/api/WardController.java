package org.fleshka4.coursework.controller.api;

import org.fleshka4.coursework.model.Ward;
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
@RequestMapping("/api/wards")
public class WardController {
    @Autowired
    private WardService wardService;

    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping
    public ResponseEntity<List<Ward>> getAllWards() {
        return new ResponseEntity<>(wardService.listWards(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(wardService.findWard(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/diagnosis/{id}")
    public ResponseEntity<List<Ward>> getWardsWithDiagnosis(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(wardService.listWardsWithDiagnosis(diagnosisService.findDiagnosis(id)),
                    HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/greater/{number}")
    public ResponseEntity<List<Ward>> getWardsByMaxCountGreaterThan(@PathVariable("number") Integer number) {
        return new ResponseEntity<>(wardService.findByMaxCountGreaterThan(number), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Ward createWard(@RequestBody Ward ward) {
        try {
            return wardService.createWard(ward);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping(value = "/addwith", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ward createWard(@RequestParam String name, @RequestParam Integer maxcount) {
        try {
            return wardService.createWard(name, maxcount);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteWardById(@PathVariable("id") Integer id) {
        try {
            wardService.deleteWardById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
