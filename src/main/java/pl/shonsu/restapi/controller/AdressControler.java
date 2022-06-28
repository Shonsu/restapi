package pl.shonsu.restapi.controller;


import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.service.AdressService;

import java.util.Set;

import static pl.shonsu.restapi.controller.mapper.AdressMapper.mapToAdress;

@RestController
public class AdressControler {

    private static final Long EMPTY_ID = null;
    private final AdressService adressService;

    public AdressControler(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping("/adresses")
    public Set<AdressDto> getAdresses() {
        return adressService.getAdresses();
    }

    @GetMapping("/adresses/{id}")
    public Adress getSingleAdress(@PathVariable long id) {
        return adressService.getSingleAdress(id);
    }

    @PostMapping("/adresses")
    public void createAdress(@RequestBody AdressDto adressDto) {
        Adress adress = mapToAdress(EMPTY_ID, adressDto);
        adressService.addAdress(adress);
    }

    @PostMapping("/adresses/{id}")
    public void updateAdress(@RequestParam Long id, @RequestBody AdressDto adressDto) {
        Adress adress = mapToAdress(id, adressDto);
        adressService.updateAdress(adress);
    }

}
