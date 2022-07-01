package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.controller.dto.AdressDto;
import pl.shonsu.restapi.controller.dto.AdressRequestDto;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.repository.AdressRepository;

import java.util.HashSet;
import java.util.Set;

import static pl.shonsu.restapi.controller.mapper.AdressDtoMapper.*;
import static pl.shonsu.restapi.controller.mapper.AdressMapper.mapToAdressFromAdressRequestDto;

@Service
public class AdressService {
    private static final Long EMPTY_ID = null;
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public Set<AdressDto> getAdresses() {
        return mapToAdressesDto(new HashSet<>(adressRepository.findAll()));
    }


    public AdressDto getAdressById(Long id) {
        Adress adress = adressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find adress by given id " + id));
        return mapToAdressDto(adress);
    }

    @Transactional
    public AdressDto addAdress(AdressRequestDto adressRequestDto) {
        Adress adress =  adressRepository.save(mapToAdressFromAdressRequestDto(EMPTY_ID, adressRequestDto));
        return mapToAdressDto(adress);
    }


    public AdressDto updateAdress(Long id, AdressRequestDto adressRequestDto) {
        Adress adress = adressRepository.getReferenceById(id);
        adress = adressRepository.save(mapToAdressFromAdressRequestDto(adress.getId(), adressRequestDto));
        return mapToAdressDto( adress);
    }

    public Adress getReferenceById(Long id) {
        return adressRepository.getReferenceById(id);
    }

    public AdressDto getPersonById(Long id) {
        Adress adress = adressRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find person by given id " + id));
        return mapToAdressDtoWithPersons(adress, adress.getPersons());
    }


}
