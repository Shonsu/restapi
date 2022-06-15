package pl.shonsu.restapi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.shonsu.restapi.model.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p")
    List<Person> findAllPerson(Pageable pageable);

    @Query("select p from Person p where p.firstName = ?1")
    List<Person> findAllByFirstName(String name);

    @Query("select p from Person p where p.lastName = :name")
    List<Person> findAllByLastName(@Param("name") String name);

    List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<Person> findPersonsWithAdresses(Pageable pageable);
}
