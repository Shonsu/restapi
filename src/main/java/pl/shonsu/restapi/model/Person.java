package pl.shonsu.restapi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "adress_person",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "adress_id"))
    private Set<Adress> adresses = new HashSet<>();

    public Person() {
    }

    public Person(Long id, String firstName, String lastName, LocalDate birthDate, Set<Adress> adresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresses = adresses;
        this.birthDate = birthDate;
    }

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void addAdress(Adress adress) {
        this.adresses.add(adress);
        adress.getPersons().add(this);
    }

    public void removeAdress(Adress adress){
        this.getAdresses().remove(adress);
        adress.getPersons().remove(this);
    }
    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getFirstName() {
        return firstName;
    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }

    public String getLastName() {
        return lastName;
    }

//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }

    public void setAdresses(Set<Adress> adresses) {
        this.adresses = adresses;
    }

    public Set<Adress> getAdresses() {
        return adresses;
    }


}
