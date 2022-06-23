package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.repository.AdressRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdressService {
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public Set<Adress> getAdresses() {
        return new HashSet<>(adressRepository.findAll());
    }

    public Adress getSingleAdress(long id) {
        return adressRepository.findById(id).orElseThrow();
    }

    @Transactional

    public Adress addAdress(Adress adress) {
        return adressRepository.save(adress);
    }

    public Adress getAdressById(Long id) {
        return adressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find adress by given id " + id));
    }
}
