package be.abis.twohelloworld.controller;


import be.abis.twohelloworld.model.Person;
import be.abis.twohelloworld.service.PersonServiceDisk;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
public class PersonsApiController {

    @Autowired
    PersonServiceDisk personService;

    @GetMapping(path="/persons/query")
    public Person findPersonByMailAndPwd(@RequestParam("email") String email,@RequestParam("pwd") String pwd) {
        return personService.findPersonByEmailAddress(email);
    }

    @GetMapping(path="/persons")
    public List<Person> findAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping(path="/persons/{id}")
    public Person findPersonById(@PathParam("id") int id) {
        return personService.findPersonById(id);
    }

    @PostMapping(path="/persons")
    public ResponseEntity<Person> addPerson(@RequestBody HashMap<String,String> c) {
        // TODO migrate the creation / validation part in the service or even repository as it needs to generate an new PersonId
        HashMap<String,String> errors=new HashMap<String,String>();
        Person person = new Person();

        person.setPersonId(personService.findAllPersons().size() + 1);
        try{ person.setPersonId(Integer.parseInt(c.get("personId")));}catch(Throwable th){errors.put("personId",th.getMessage());}
        try{ person.setFirstName(c.get("firstName"));}catch(Throwable th){errors.put("firstName",th.getMessage());}
        try{ person.setLastName(c.get("lastName"));}catch(Throwable th){errors.put("lastName",th.getMessage());}
        try{ person.setBirthday(LocalDate.parse(c.get("birthday")));}catch(Throwable th){errors.put("birthday",th.getMessage());}
        try{ person.setEmailAddress(c.get("emailAddress"));}catch(Throwable th){errors.put("emailAddress",th.getMessage());}
        try{ person.setHomeAddress(c.get("homeAddress"));}catch(Throwable th){errors.put("homeAddress",th.getMessage());}
        try{ person.setLanguage(c.get("language"));}catch(Throwable th){errors.put("language",th.getMessage());}
        try{ person.setPhone(c.get("phone"));}catch(Throwable th){errors.put("phone",th.getMessage());}
        try{ person.setMobile(c.get("mobile"));}catch(Throwable th){errors.put("mobile",th.getMessage());}
        try{ person.setStreet(c.get("street"));}catch(Throwable th){errors.put("street",th.getMessage());}
        try{ person.setNumber(c.get("number"));}catch(Throwable th){errors.put("number",th.getMessage());}
        try{ person.setZipCode(c.get("zipCode"));}catch(Throwable th){errors.put("zipCode",th.getMessage());}
        try{ person.setCity(c.get("city"));}catch(Throwable th){errors.put("city",th.getMessage());}
        if(errors.isEmpty()){
            personService.addPerson(person);
            return ResponseEntity.ok(person);
        }else {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path="/persons")
    public ResponseEntity<Person> updatePerson(@RequestBody HashMap<String,String> c) {

        // TODO migrate the creation / validation part in the service or even repository as it needs to generate an new PersonId
        HashMap<String,String> errors=new HashMap<String,String>();
        Person person = new Person();
        try{ person.setPersonId(Integer.parseInt(c.get("personId")));}catch(Throwable th){errors.put("personId",th.getMessage());}
        try{ person.setFirstName(c.get("firstName"));}catch(Throwable th){errors.put("firstName",th.getMessage());}
        try{ person.setLastName(c.get("lastName"));}catch(Throwable th){errors.put("lastName",th.getMessage());}
        try{ person.setBirthday(LocalDate.parse(c.get("birthday")));}catch(Throwable th){errors.put("birthday",th.getMessage());}
        try{ person.setEmailAddress(c.get("emailAddress"));}catch(Throwable th){errors.put("emailAddress",th.getMessage());}
        try{ person.setHomeAddress(c.get("homeAddress"));}catch(Throwable th){errors.put("homeAddress",th.getMessage());}
        try{ person.setLanguage(c.get("language"));}catch(Throwable th){errors.put("language",th.getMessage());}
        try{ person.setPhone(c.get("phone"));}catch(Throwable th){errors.put("phone",th.getMessage());}
        try{ person.setMobile(c.get("mobile"));}catch(Throwable th){errors.put("mobile",th.getMessage());}
        try{ person.setStreet(c.get("street"));}catch(Throwable th){errors.put("street",th.getMessage());}
        try{ person.setNumber(c.get("number"));}catch(Throwable th){errors.put("number",th.getMessage());}
        try{ person.setZipCode(c.get("zipCode"));}catch(Throwable th){errors.put("zipCode",th.getMessage());}
        try{ person.setCity(c.get("city"));}catch(Throwable th){errors.put("city",th.getMessage());}
        if(errors.isEmpty()){
            personService.addPerson(person);
            return ResponseEntity.ok(person);
        }else {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path="/persons")
    public HashMap<String,String> deletePerson(int id) {
        personService.deletePerson(id);
        return new HashMap<String,String>(){{
            put("deleted",Integer.valueOf(id).toString());
        }};
    }
}
