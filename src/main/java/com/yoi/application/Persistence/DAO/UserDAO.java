package com.yoi.application.Persistence.DAO;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * @author Yoi
 * @date 2025/04/17
 * @description UserDAO class representing the user entity in the database.
 */

@Entity
@Table(name = "USERS")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, message = "name should be at least 3 characters")
    @Column(name = "NAME")
    private String name;

    @Nullable
    @Column(name = "EMAIL")
    private String email;

    public UserDAO(){}

    public UserDAO(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getEmail(){ return email; }

    public void setEmail(String email){ this.email = email; }

}
