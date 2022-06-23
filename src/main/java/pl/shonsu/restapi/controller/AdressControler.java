package pl.shonsu.restapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressDtoMapper;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.service.AdressService;

import java.util.Set;

@RestController
public class AdressControler {

    private final AdressService adressService;

    public AdressControler(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping("/adresses")
    public Set<AdressDto> getAdresses() {
        return AdressDtoMapper.mapToAdressDtos(adressService.getAdresses());
    }


    @GetMapping("/adresses/{id}")
    public Adress getSingleAdress(@PathVariable long id) {
        return adressService.getSingleAdress(id);
    }
}
