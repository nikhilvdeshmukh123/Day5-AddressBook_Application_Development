package com.bridgelabz.addressbookapp.repository;

import com.bridgelabz.addressbookapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<Person, Integer> {
    @Query(value = "select * from addressbook order by city", nativeQuery = true)
    List<Person> findContactsByCityOrderBy();

    @Query(value = "select * from addressbook where state=:state", nativeQuery = true)
    List<Person> sortContactByState(String state);
}
