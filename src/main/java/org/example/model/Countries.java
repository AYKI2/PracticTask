package org.example.model;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries")
public class Countries {
    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String description;
    @OneToMany(mappedBy = "countries", cascade = {CascadeType.ALL})
    private List<Address> addresses;

    public Countries(String country, String description) {
        this.country = country;
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nCountry:" +
                "\nid = " + id +
                "\ncountry = '" + country +
                "\ndescription = '" + description;
    }
}
