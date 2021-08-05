package jm.task.core.jdbc.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Пользователи", schema = "db")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return id + " " + name +
                " " + lastName +
                " " + age + "\n";
    }
}

