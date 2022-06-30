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
    public AdressDto getSingleAdress(@PathVariable long id) {
        Adress adress = adressService.getAdressById(id);
        return AdressDto.AdressDtoBuilder.anAdressDto()
                .withId(adress.getId())
                .withCity(adress.getCity())
                .withStreet(adress.getStreet())
                .withHouseNumber(adress.getHouseNumber())
                .withFlatNumber(adress.getFlatNumber())
                .build();
    }

    @PostMapping("/adresses")
    public Adress createAdress(@RequestBody AdressDto adressDto) {
        Adress adress = mapToAdress(EMPTY_ID, adressDto);
        return adressService.addAdress(adress);
    }

    @PostMapping("/adresses/{id}")
    public void updateAdress(@RequestParam Long id, @RequestBody AdressDto adressDto) {
        Adress adress = mapToAdress(id, adressDto);
        adressService.updateAdress(adress);
    }

}
