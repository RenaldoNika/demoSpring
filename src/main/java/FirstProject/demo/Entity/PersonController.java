package FirstProject.demo.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {


    @Autowired
    PersonRepository personRepository;


    @PostMapping("Create")
    public ResponseEntity<String> personResponseEntity(@RequestBody Person person) {
        if (personRepository.existsByName(person.getName())) {
            throw new PersonNotFoundException("Personi me këtë emër ekziston tashmë: " + person.getName());
        }
        personRepository.save(person);
        return new ResponseEntity<>(person.getName(), HttpStatus.ACCEPTED);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Person personupdate) {
        Optional<Person> personexciste = personRepository.findById(id);
        if (personexciste.isPresent()) {
            Person personex = personexciste.get();
            personex.setName(personupdate.getName());
            personRepository.save(personex);
            return new ResponseEntity<>(personex, HttpStatus.OK);
        }
        throw new PersonNotFoundException("Person not found");
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id){
        personRepository.deleteById(id);
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
    public ResponseEntity<?>getbyname(@PathVariable String name){
        Optional<Person>person=personRepository.findByName(name);

        if (person.isPresent()){
            return new ResponseEntity<>(person,HttpStatus.OK);
        }
        throw new PersonNotFoundException("Person with name "+name+" not found");
    }


    @GetMapping("getAll")
    public List<Person> personList() {
        List<Person> personList = personRepository.findAll();
        System.out.println(personList);

        return personList;
    }
}