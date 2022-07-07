package pl.shonsu.restapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressRequestDto;
import pl.shonsu.restapi.service.AdressService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.Set;

@Validated
@RequestMapping("/api/doc")
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
    public AdressDto getSingleAdress(@PathVariable @Positive long id) {
        return adressService.getAdressById(id);
    }

    @PostMapping("/adresses")
    public ResponseEntity<AdressDto> createAdress(@RequestBody @Valid AdressRequestDto adressRequestDto) {
        AdressDto adressDto = adressService.addAdress(adressRequestDto);
        return ResponseEntity.created(URI.create("/adresses")).body(adressDto);
    }

    @PutMapping("/adresses/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdressDto updateAdress(@RequestParam @Positive Long id, @RequestBody @Valid AdressRequestDto adressRequestDto) {
        return adressService.updateAdress(id, adressRequestDto);
    }

    @DeleteMapping("/adresses/{id}")
    public void deleteAdress(@RequestParam @Positive Long id) {
        adressService.deleteAdress(id);
    }

    @GetMapping("/adresses/{id}/persons")
    @ResponseStatus(HttpStatus.OK)
    public AdressDto getAdressWithPersonsById(@PathVariable @Positive Long id) {
        return adressService.getAdressWithPersonById(id);
    }

}
