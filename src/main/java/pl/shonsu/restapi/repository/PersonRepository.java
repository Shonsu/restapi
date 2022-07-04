package pl.shonsu.restapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.shonsu.restapi.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p")
    Page<Person> findAllPersons(Pageable pageable);


    @Query("select p from Person p where p.firstName = ?1")
    Set<Person> findAllByFirstName(String name);

    @Query("select p from Person p where p.lastName = :name")
    Set<Person> findAllByLastName(@Param("name") String name);

    Set<Person> findAllByFirstNameAndLastName(String firstName, String lastName);

    Optional<Person> findFirstPersonByAdressesIdIsNull();

    List<Person> findByAdressesIsNull();
}
