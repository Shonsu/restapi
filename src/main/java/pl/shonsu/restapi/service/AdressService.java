package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.repository.AdressRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.shonsu.restapi.controller.mapper.AdressDtoMapper.mapToAdressesDto;

@Service
public class AdressService {
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public Set<AdressDto> getAdresses() {
        return mapToAdressesDto(new HashSet<>(adressRepository.findAll()));
    }


    @Transactional
    public Adress addAdress(Adress adress) {
        return adressRepository.save(adress);
    }

    public Adress getAdressById(Long id) {
        Adress adress = adressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find adress by given id " + id));
        return adress;
    }


    public Adress updateAdress(Adress adress) {
        return adressRepository.save(adress);
    }
}
