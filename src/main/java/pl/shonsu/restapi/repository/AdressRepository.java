package pl.shonsu.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.shonsu.restapi.model.Adress;

import java.util.List;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
    public List<Adress> findAllAdressesByPersonsIdIn(List<Long> ids);
   //@Query("select a from Adress a")
   // public List<Adress> findAll();

}
