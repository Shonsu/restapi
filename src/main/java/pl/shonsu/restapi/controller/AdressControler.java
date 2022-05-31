package pl.shonsu.restapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.service.AdressService;

import java.util.List;

@RestController
public class AdressControler {

    private final AdressService adressService;
    public AdressControler(AdressService adressService){
        this.adressService = adressService;
    }
    @GetMapping("/adresses")
    public List<Adress> getAdresses() {
        return adressService.getAdresses();
    }

    @GetMapping("/adresses/{id}")
    public Adress getSingleAdress(@PathVariable long id) {
        return adressService.getSingleAdress(id);
    }
}
