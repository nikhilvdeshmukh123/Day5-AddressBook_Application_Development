package com.bridgelabz.addressbookapp.repository;

import com.bridgelabz.addressbookapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<Person, Integer> {

}
