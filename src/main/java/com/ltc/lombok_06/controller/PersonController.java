package com.ltc.lombok_06.controller;

import com.ltc.lombok_06.dto.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("HR")
public class PersonController {
    PersonDTO new_person = new PersonDTO(1, "first_person", "lastname of first", 22);
    PersonDTO not_new_person = new PersonDTO(2, "a person", "Mr Y", 17);

    ArrayList<PersonDTO> persons = new ArrayList<>(List.of(new_person, not_new_person));

    //CRUD
    //CREATE
    //READ
    //UPDATE
    //DELETE
    @GetMapping("hey")
    private String welcome() {
        PersonDTO second_person = PersonDTO.builder()
                .id(3)
                .firstName("Builder Person")
                .lastName("Mr X")
                .age(42)
                .build();

        persons.add(second_person);
        return "hey there " + second_person;

    }


    @GetMapping("all")
    public List<PersonDTO> getAllPersons() {
        return persons;
    }

    @GetMapping("id")
    public List<PersonDTO> getPersonById(@RequestParam int id) {
        return persons
                .stream()
                .filter(person -> person.getId() > id)
                .collect(Collectors.toList());

    }

    @PostMapping("add")
    public ArrayList<PersonDTO> addPerson(@RequestBody PersonDTO newPerson) {
        persons.add(newPerson);
        return persons;
    }


    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam int id) {
        persons.removeIf(personDTO -> personDTO.getId() == id);
        return ResponseEntity.ok("Ugurlu silindi");
    }

    @PutMapping
    public ArrayList<PersonDTO> updatePerson(@PathVariable int id, @RequestBody PersonDTO updatedPerson) {
        for (int i = 0; i < persons.size(); i++) {
            PersonDTO personDto = persons.get(i);
            if (personDto.getId() == id) {
                personDto.setId(updatedPerson.getId());
                personDto.setFirstName(updatedPerson.getFirstName());
                personDto.setLastName(updatedPerson.getLastName());
                personDto.setAge(updatedPerson.getAge());
                persons.set(i, personDto);
                break;
            }
        }

        return persons;
    }

}
