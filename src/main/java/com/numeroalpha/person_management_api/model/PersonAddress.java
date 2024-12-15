package com.numeroalpha.person_management_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class PersonAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personAddressId;

    @NotNull
    private String street;

    @NotNull
    private String state;

    public Integer getPersonAddressId() {
        return personAddressId;
    }

    public void setPersonAddressId(Integer personAddressId) {
        this.personAddressId = personAddressId;
    }

    public @NotNull String getStreet() {
        return street;
    }

    public void setStreet(@NotNull String street) {
        this.street = street;
    }

    public @NotNull String getState() {
        return state;
    }

    public void setState(@NotNull String state) {
        this.state = state;
    }

    public PersonAddress(Integer personAddressId, String street, String state) {
        this.personAddressId = personAddressId;
        this.street = street;
        this.state = state;
    }

    public PersonAddress() {
    }
}
