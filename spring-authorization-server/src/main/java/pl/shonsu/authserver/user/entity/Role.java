package pl.shonsu.authserver.user.entity;

import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //fetch = FetchType.LAZY,

    @ManyToMany( fetch = FetchType.EAGER, mappedBy = "roles", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonIgnore
    private Collection<User> users;

    public Role(Integer id, String name) {
        this.id=id;
        this.name = name;
    }
}
