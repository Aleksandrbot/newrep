package com.example.userbook.models;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "last_name")
    String last_name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> book;
}
