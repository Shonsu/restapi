package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.repository.AdressRepository;

import java.util.List;

@Service
public class AdressService {
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public List<Adress> getAdresses() {
        return adressRepository.findAll();
    }

    public Adress getSingleAdress(long id) {
        return adressRepository.findById(id).orElseThrow();
    }

    public Adress addAdress(Adress adress) {
        return adressRepository.save(adress);
    }
}
