package FirstProject.demo.Entity;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PersonController {


    @Autowired
    PersonRepository personRepository;


    @PostMapping("Create")
    public ResponseEntity<?> personResponseEntity(@Valid @RequestBody Person person, BindingResult result) {
        Optional<Person> person1 = personRepository.findByName(person.getName());
        if (person1.isPresent()) {
            throw new PersonNotFoundException("Personi me këtë emër ekziston tashmë: " + person.getName());
        } else if (result.hasErrors()) {

            StringBuilder errors = new StringBuilder("Gabim në validim: ");

            result.getFieldErrors().forEach(error -> {
                errors.append(error.getField()).append("  -  ").append(error.getDefaultMessage()).append(" - ");
            });
            return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
        } else
            personRepository.save(person);
        return new ResponseEntity<>(person.getName(), HttpStatus.ACCEPTED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Integer id) {
        Optional<Person> product = personRepository.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        throw new PersonNotFoundException("Product with id " + id + " not found");

    }


    @GetMapping("/name/{name}")
    public ResponseEntity<Person> name(@PathVariable String name) {
        Optional<Person> person = personRepository.findByName(name);
        if (person.isPresent()) {
            Person personFind=person.get();
            return ResponseEntity.ok(personFind);
        }
        throw new PersonNotFoundException("PERSONI ME EMER " + name + " nuk egziston");
    }

    @DeleteMapping("del/{id}")
    public void del(@PathVariable Integer id) {
        personRepository.deleteById(id);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<String> pUpdate(@PathVariable Integer id, @RequestBody Person person) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {

            Person person1 = existingPerson.get();
            person1.setName(person.getName());
            personRepository.save(person1);
            return new ResponseEntity<>(person.getName(), HttpStatus.OK);
        } else {
            throw new PersonNotFoundException("ID NOT FOUND : " + id);
        }
    }


    @GetMapping("getAll")
    public List<Person> personList() {
        List<Person> personList = personRepository.findAll();
        System.out.println(personList);

        return personList;
    }

}