package pl.shonsu.restapi.model;

import javax.persistence.*;
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
    private Set<Person> persons;

    public Adress() {
    }

    public Adress(String city, String streetName, String houseNumber, Integer flatNumber) {
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public static final class AdressBuilder {
        private long id;
        private String city;
        private String street;
        private String houseNumber;
        private Integer flatNumber;
        private Set<Person> persons;

        private AdressBuilder() {
        }

        public static AdressBuilder anAdress() {
            return new AdressBuilder();
        }

        public AdressBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public AdressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AdressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AdressBuilder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AdressBuilder withFlatNumber(Integer flatNumber) {
            this.flatNumber = flatNumber;
            return this;
        }

        public AdressBuilder withPersons(Set<Person> persons) {
            this.persons = persons;
            return this;
        }

        public Adress build() {
            Adress adress = new Adress();
            adress.flatNumber = this.flatNumber;
            adress.houseNumber = this.houseNumber;
            adress.city = this.city;
            adress.persons = this.persons;
            adress.id = this.id;
            adress.street = this.street;
            return adress;
        }
    }
}
