package pl.shonsu.restapi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String houseNumber;
    private Integer flatNumber;
    @ManyToMany(mappedBy = "adresses", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Person> persons = new HashSet<>();

    public Adress() {
    }

    public Adress(String city, String street, String houseNumber, Integer flatNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
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

    public Long getId() {
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

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", flatNumber=" + flatNumber +
                ", persons=" + persons +
                '}';
    }

    public static final class AdressBuilder {
        private Long id;
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

        public AdressBuilder withId(Long id) {
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
