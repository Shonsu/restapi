package pl.shonsu.adressbook.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.adressbook.controller.dto.AdressDto;
import pl.shonsu.adressbook.controller.dto.AdressRequestDto;
import pl.shonsu.adressbook.controller.mapper.AdressDtoMapper;
import pl.shonsu.adressbook.controller.mapper.AdressMapper;
import pl.shonsu.adressbook.exceptionshandling.exceptions.AdressNotFoundException;
import pl.shonsu.adressbook.model.Adress;
import pl.shonsu.adressbook.repository.AdressRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdressService {
    private static final Long EMPTY_ID = null;
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public Set<AdressDto> getAdresses() {
        return AdressDtoMapper.mapToAdressesDto(new HashSet<>(adressRepository.findAll()));
    }


    public AdressDto getAdressById(Long id) {
        Adress adress = adressRepository.findById(id)
                .orElseThrow(() -> new AdressNotFoundException(id));
        return AdressDtoMapper.mapToAdressDto(adress);
    }

    @Transactional
    public AdressDto addAdress(AdressRequestDto adressRequestDto) {
        Adress adress = adressRepository.save(AdressMapper.mapToAdressFromAdressRequestDto(EMPTY_ID, adressRequestDto));
        return AdressDtoMapper.mapToAdressDto(adress);
    }

    @Transactional
    public AdressDto updateAdress(Long id, AdressRequestDto adressRequestDto) {
        Adress adress = adressRepository.findById(id)
                .orElseThrow(() -> new AdressNotFoundException(id));
        adress = adressRepository.save(AdressMapper.mapToAdressFromAdressRequestDto(adress.getId(), adressRequestDto));
        return AdressDtoMapper.mapToAdressDto(adress);
    }

    public AdressDto getAdressWithPersonById(Long id) {
        Adress adress = adressRepository.findById(id)
                .orElseThrow(() -> new AdressNotFoundException(id));
        return AdressDtoMapper.mapToAdressDtoWithPersons(adress, adress.getPersons());
    }


    public void deleteAdress(Long id) {
        if (!adressRepository.existsById(id)) throw new AdressNotFoundException((id));
        adressRepository.deleteById(id);
    }
}
