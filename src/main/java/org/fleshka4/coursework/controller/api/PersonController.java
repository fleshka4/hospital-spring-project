package org.fleshka4.coursework.controller.api;


import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.service.DiagnosisService;
import org.fleshka4.coursework.service.PersonService;
import org.fleshka4.coursework.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private WardService wardService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        return new ResponseEntity<>(personService.listPeople(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(personService.findPerson(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<Person>> getPeopleByLastName(@PathVariable("lastname") String lastName) {
        return new ResponseEntity<>(personService.findPeopleByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Person>> getPeopleByFullName(@RequestParam String firstname,
                                                            @RequestParam String lastname,
                                                            @RequestParam(required = false) String middlename) {
        return new ResponseEntity<>(personService.findPeopleByFullName(firstname, lastname, middlename),
                HttpStatus.OK);
    }

    @GetMapping("/numberwithdiagnosis/{id}")
    public ResponseEntity<Long> getCountByDiagnosisId(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(personService.countByDiagnosisId(diagnosisService.findDiagnosis(id)),
                    HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/numberinward/{id}")
    public ResponseEntity<Long> getCountByWardId(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(personService.countByWardId(wardService.findWard(id)),
                    HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PostMapping(value = "/addwith", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person createPerson(@RequestParam String firstname,
                               @RequestParam String lastname,
                               @RequestParam(required = false) String middlename) {
        return personService.createPerson(firstname, lastname, middlename);
    }

    @PatchMapping("/{id}/diagnosis/{diagnosisId}")
    public ResponseEntity<Person> updateDiagnosisId(@PathVariable("id") Integer id, @PathVariable("diagnosisId") Integer wardId) {
        try {
            personService.updateDiagnosisIdById(diagnosisService.findDiagnosis(wardId), id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(personService.findPerson(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/ward/{wardId}")
    public ResponseEntity<Person> updateWardId(@PathVariable("id") Integer id, @PathVariable("wardId") Integer wardId) {
        try {
            final Ward ward = wardService.findWard(wardId);
            if (ward.getMaxCount() <= ward.getPeople().size()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ward is full");
            }
            personService.updateWardIdById(ward, id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(personService.findPerson(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePersonById(@PathVariable("id") Integer id) {
        try {
            personService.deletePersonById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        final Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
