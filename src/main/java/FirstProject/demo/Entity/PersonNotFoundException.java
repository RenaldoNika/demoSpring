package FirstProject.demo.Entity;




public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(String message){
        super(message);
    }

    public PersonNotFoundException() {
        super("Person not found.");
    }
}
