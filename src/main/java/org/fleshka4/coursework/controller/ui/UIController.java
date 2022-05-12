package org.fleshka4.coursework.controller.ui;

import org.fleshka4.coursework.model.Diagnosis;
import org.fleshka4.coursework.model.Person;
import org.fleshka4.coursework.model.User;
import org.fleshka4.coursework.model.Ward;
import org.fleshka4.coursework.model.request.DiagAndWardRequest;
import org.fleshka4.coursework.repository.DiagnosisRepository;
import org.fleshka4.coursework.repository.PersonRepository;
import org.fleshka4.coursework.repository.UserRepository;
import org.fleshka4.coursework.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;

@Controller
public class UIController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @GetMapping
    public String viewHomePage() {
        return "/index";
    }

    @GetMapping("/login")
    public String showSignInForm(final Model model) {
        model.addAttribute("user", new User());
        return "/sign_in";
    }

    @GetMapping("/users")
    public String listUsers(final Model model) {
        model.addAttribute("userList", userRepository.findAll());
        return "/users";
    }

    @GetMapping("/people")
    public String listPeople(final Model model) {
        final List<Person> people = ((List<Person>) personRepository.findAll())
                .stream()
                .filter(person -> {
                    try {
                        return isNotNull(person);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
        model.addAttribute("peopleList", people);
        return "/people/people";
    }

    @GetMapping("/wards")
    public String listWards(final Model model) {
        final List<Ward> wards = ((List<Ward>) wardRepository.findAll())
                .stream()
                .filter(ward -> {
                    try {
                        return isNotNull(ward);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
        model.addAttribute("wardList", wards);
        return "/wards/wards";
    }

    @GetMapping("/diagnoses")
    public String listDiagnoses(final Model model) {
        final List<Diagnosis> diagnoses = ((List<Diagnosis>) diagnosisRepository.findAll())
                .stream()
                .filter(diagnosis -> {
                    try {
                        return isNotNull(diagnosis);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).toList();
        model.addAttribute("diagnosisList", diagnoses);
        return "/diagnoses/diagnoses";
    }

    @GetMapping("/diagnoses/{id}")
    public String showDiagnosis(@PathVariable("id") Integer id, final Model model) {
        final Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("people", diagnosis.getPeople());
        return "/diagnoses/diagnosis";
    }

    @GetMapping("/wards/{id}")
    public String showWard(@PathVariable("id") Integer id, final Model model) {
        final Ward ward = wardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        model.addAttribute("ward", ward);
        model.addAttribute("people", ward.getPeople());
        return "/wards/ward";
    }

    @GetMapping("/people/add")
    public String showPersonRegistration(final Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("diagAndWard", new DiagAndWardRequest());
        model.addAttribute("diagnoses", diagnosisRepository.findAll());
        model.addAttribute("wards", wardRepository.findAll());
        return "/people/new_person";
    }

    @PostMapping("/people/add")
    public String processPersonRegistration(final Person person, final DiagAndWardRequest diagAndWardRequest) {
        final Diagnosis diagnosis = diagnosisRepository.findByName(diagAndWardRequest.getDiagnosisName())
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        person.setDiagnosisId(diagnosis);
        final Ward ward = wardRepository.findByName(diagAndWardRequest.getWardName())
                .orElseThrow(() -> new EntityNotFoundException("Ward not found"));
        if (ward.getMaxCount() <= ward.getPeople().size()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ward is full");
        }
        person.setWardId(ward);
        personRepository.save(person);
        return "/people/people";
    }

    @GetMapping("/diagnoses/add")
    public String showDiagnosisRegistration(final Model model) {
        model.addAttribute("diagnosis", new Diagnosis());
        return "/diagnoses/new_diagnosis";
    }

    @PostMapping("/diagnoses/add")
    public String processDiagnosisRegistration(final Diagnosis diagnosis) {
        diagnosisRepository.save(diagnosis);
        return "/diagnoses/diagnoses";
    }

    @GetMapping("/wards/add")
    public String showWardRegistration(final Model model) {
        model.addAttribute("ward", new Ward());
        return "/wards/new_ward";
    }

    @PostMapping("/wards/add")
    public String processWardRegistration(final Ward ward) {
        wardRepository.save(ward);
        return "/wards/wards";
    }

    public static boolean isNotNull(final Object obj) throws IllegalAccessException {
        for (final Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) != null)
                return true;
        }
        return false;
    }
}