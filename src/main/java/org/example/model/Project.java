package org.example.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "project_name")
    private String projectName;
    private String description;
    @Column(name = "date_of_start")
    private LocalDate dateOfStart;
    @Column(name = "date_of_finish")
    private LocalDate dateOfFinish;
    private int price;
    @ManyToMany(cascade = {MERGE,REFRESH,DETACH,PERSIST})
    @JoinTable(name = "project_programmers",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "programmer_id"))
    private List<Programmer>programmers;

    public Project(String projectName, String description, LocalDate dateOfStart, LocalDate dateOfFinish, int price) {
        this.projectName = projectName;
        this.description = description;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nProject:" +
                "\nid = " + id +
                "\nprojectName = '" + projectName +
                "\ndescription = '" + description +
                "\ndateOfStart = " + dateOfStart +
                "\ndateOfFinish = " + dateOfFinish +
                "\nprice = " + price;
    }
}
