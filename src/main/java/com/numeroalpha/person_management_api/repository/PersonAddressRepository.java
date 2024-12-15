package com.numeroalpha.person_management_api.repository;

import com.numeroalpha.person_management_api.model.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, Integer> {
}
