package pl.shonsu.restapi.controller;


import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressRequestDto;
import pl.shonsu.restapi.service.AdressService;

import java.util.Set;

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
    public AdressDto getSingleAdress(@PathVariable long id) {
        return adressService.getAdressById(id);
    }

    @PostMapping("/adresses")
    public AdressDto createAdress(@RequestBody AdressRequestDto adressRequestDto) {
        return adressService.addAdress(adressRequestDto);
    }

    @PutMapping("/adresses/{id}")
    public AdressDto updateAdress(@RequestParam Long id, @RequestBody AdressRequestDto adressRequestDto) {
        return adressService.updateAdress(id, adressRequestDto);
    }

    @GetMapping("/adress/{id}/persons")
    public AdressDto getAdressWithPersonsById(@PathVariable Long id){
        return adressService.getPersonById(id);
    }

}
