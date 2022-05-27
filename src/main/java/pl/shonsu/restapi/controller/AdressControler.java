package pl.shonsu.restapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.service.AdressService;

import java.util.List;

@RestController
public class AdressControler {

    @GetMapping("/adresses")
    public List<Adress> getAdresses() {
        try {
            throw new IllegalAccessException("Not implemented yet");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/adresses/{id}")
    public List<Adress> getSingleAdress(@PathVariable long id) {
        try {
            throw new IllegalAccessException("Not implemented yet");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
