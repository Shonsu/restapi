package pl.shonsu.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shonsu.restapi.model.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {

}
