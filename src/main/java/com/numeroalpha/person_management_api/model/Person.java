package com.numeroalpha.person_management_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_address_id", referencedColumnName = "personAddressId")
    @JsonProperty("person_address")
    private PersonAddress personAddress;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull Integer getAge() {
        return age;
    }

    public void setAge(@NotNull Integer age) {
        this.age = age;
    }

    public PersonAddress getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(PersonAddress personAddress) {
        this.personAddress = personAddress;
    }

    public Person(Integer personId, String name, Integer age, PersonAddress personAddress) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.personAddress = personAddress;
    }

    public Person() {
    }
}
