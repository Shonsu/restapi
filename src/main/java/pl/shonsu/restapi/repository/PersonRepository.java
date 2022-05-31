package pl.shonsu.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.shonsu.restapi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
