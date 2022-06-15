package pl.shonsu.restapi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable(
            name = "adress_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "adress_id"))
    private List<Adress> adresses;
    public Person() {
    }

    public Person(Long id, String firstName, String lastName, List<Adress> adresses, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.birthDate = birthDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public List<Adress> getAdresses() {
        return adresses;
    }


}
