package pl.shonsu.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.shonsu.restapi.model.Adress;
import pl.shonsu.restapi.repository.AdressRepository;

import java.util.List;
@Transactional
@Service
public class AdressService {
    private final AdressRepository adressRepository;

    public AdressService(AdressRepository adressRepository) {
        this.adressRepository = adressRepository;
    }

    public List<Adress> getAdresses() {
        return adressRepository.findAllAdresses();
    }

    public Adress getSingleAdress(long id) {
        return adressRepository.findById(id).orElseThrow();
    }


    public Adress addAdress(Adress adress) {
        return adressRepository.save(adress);
    }
}
