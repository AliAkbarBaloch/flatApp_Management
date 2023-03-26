package com.aliakbar.fleetappmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aliakbar.fleetappmanagement.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
