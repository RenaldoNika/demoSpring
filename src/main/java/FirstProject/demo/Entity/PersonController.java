package FirstProject.demo.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;

@RestController
public class PersonController {


    @Autowired
    PersonRepository personRepository;



    @PostMapping("Create")
    public ResponseEntity<String>personResponseEntity(@RequestBody Person person){
        if (personRepository.existsByName(person.getName())) {
            throw new PersonNotFoundException("Personi me këtë emër ekziston tashmë: " + person.getName());
        }
        personRepository.save(person);
        return new ResponseEntity<>(person.getName(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Integer id) {
        Optional<Person> product = personRepository.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        throw new PersonNotFoundException("Product with id " + id + " not found");

    }


    @GetMapping("getAll")
    public List<Person>personList(){
        List<Person>personList=personRepository.findAll();
        System.out.println(personList);

        return personList;
    }
}