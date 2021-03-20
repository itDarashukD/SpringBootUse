package by.darashuk.springBootUse.controllers;

import by.darashuk.springBootUse.model.Person;
import by.darashuk.springBootUse.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public int addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person>getAllPerson(){
        return personService.getAllPerson();
    }

    @GetMapping(path = "{id}")
    public Person getById(@PathVariable("id") UUID id){
        return personService.getById(id).orElse(null);
    }
    @PutMapping(path = "{id}")
    public void updateById(@PathVariable("id")UUID id, @RequestBody Person person){
          personService.updateById(id,person);
    }
    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id")UUID id){
          personService.deleteById(id);
    }



}
