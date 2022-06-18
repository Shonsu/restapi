package pl.shonsu.restapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private String street;
    private String houseNumber;
    private Integer flatNumber;

    @ManyToMany(mappedBy = "adresses")//, cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    private List<Person> persons = new ArrayList<>();

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Adress() {
    }

    public Adress(long id, String city, String street, String houseNumber, Integer flatNumber, List<Person> persons) {
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

    public void addPerson(Person person){
        this.persons.add(person);
        person.getAdresses().add(this);
    }

    public void removePerson(Person person){
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
