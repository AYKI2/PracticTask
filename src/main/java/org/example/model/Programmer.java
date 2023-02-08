package org.example.model;

import javax.persistence.*;
import lombok.*;
import org.example.enums.Status;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "programmers")
public class Programmer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(cascade = {MERGE,REFRESH,DETACH,PERSIST})
    private Address address;
    @ManyToMany(mappedBy = "programmers",
            cascade = {MERGE,REFRESH,DETACH,PERSIST})
    private List<Project>projects;

    public Programmer(String fullName, String email, LocalDate dateOfBirth, Status status) {
        this.fullName = fullName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nProgrammer:" +
                "\nid = " + id +
                "\nfullName = '" + fullName +
                "\nemail = '" + email +
                "\ndateOfBirth = " + dateOfBirth +
                "\nstatus = " + status;
    }
}
