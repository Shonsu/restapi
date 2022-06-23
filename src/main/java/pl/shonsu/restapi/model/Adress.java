package pl.shonsu.restapi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private Integer flatNumber;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Person> persons = new HashSet<>();

//    public void setPersons(Set<Person> persons) {
//        this.persons = persons;
//    }

    public Set<Person> getPersons() {
        return persons;
    }

    public Adress() {
    }

    public Adress(long id, String city, String street, String houseNumber, Integer flatNumber, Set<Person> persons) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.persons = persons;
    }

    public Adress(String city, String street, String houseNumber, Integer flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
        person.getAdresses().add(this);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
        person.getAdresses().remove(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }


}
