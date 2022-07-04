package pl.shonsu.restapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressRequestDto;
import pl.shonsu.restapi.service.AdressService;

import java.net.URI;
import java.util.Set;

@RestController
public class AdressControler {

    private final AdressService adressService;

    public AdressControler(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping("/adresses")
    @ResponseStatus(HttpStatus.OK)
    Set<AdressDto> getAdresses() {
        return adressService.getAdresses();
    }

    @GetMapping("/adresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdressDto getSingleAdress(@PathVariable long id) {
        return adressService.getAdressById(id);
    }

    @PostMapping("/adresses")
    public ResponseEntity<AdressDto> createAdress(@RequestBody AdressRequestDto adressRequestDto) {
        AdressDto adressDto = adressService.addAdress(adressRequestDto);
        return ResponseEntity.created(URI.create("/adresses")).body(adressDto);
    }

    @PutMapping("/adresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdressDto updateAdress(@RequestParam Long id, @RequestBody AdressRequestDto adressRequestDto) {
        return adressService.updateAdress(id, adressRequestDto);
    }

    @GetMapping("/adress/{id}/persons")
    @ResponseStatus(HttpStatus.OK)
    public AdressDto getAdressWithPersonsById(@PathVariable Long id){
        return adressService.getPersonById(id);
    }

}
