package pl.shonsu.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.service.PersonService;

@RequestMapping("/api/faker")
@RestController
public class FakerController {
    PersonService personService;

    public FakerController(PersonService personService) {
        this.personService = personService;
    }

//    @GetMapping("/persons/{count}")
//    public void

}
