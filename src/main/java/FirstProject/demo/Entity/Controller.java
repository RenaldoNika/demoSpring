package FirstProject.demo.Entity;


import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {




    @GetMapping("/index")
    public String abc(){
        return "html";
    }
}
