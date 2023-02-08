package org.example.model;

import javax.persistence.*;
import lombok.*;

import static javax.persistence.CascadeType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "region_name")
    private String regionName;
    private String street;
    @Column(name = "home_number")
    private int homeNumber;

    @ManyToOne(cascade = {MERGE,REFRESH,DETACH,PERSIST})
    private Countries countries;
    @OneToOne(mappedBy = "address", cascade = {MERGE,REFRESH,DETACH,PERSIST})
    private Programmer programmer;

    public Address(String regionName, String street, int homeNumber) {
        this.regionName = regionName;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    @Override
    public String toString() {
        return "\nAddress: "+
                "\nid = " + id +
                "\nregionName = '" + regionName +
                "\nstreet = '" + street +
                "\nhomeNumber = " + homeNumber;
    }
}
