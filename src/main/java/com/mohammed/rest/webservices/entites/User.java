package com.mohammed.rest.webservices.entites;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 3, max = 30, message = "name should be longer than 3 chars")
    @NotBlank
    private String name;
    @Past
    private Date birthDay;

    public User(String name, Date birthDay){
        this.name= name;
        this.birthDay = birthDay;
    }
    public User(String name){
        this.name= name;
        this.birthDay = new Date();
    }
}
