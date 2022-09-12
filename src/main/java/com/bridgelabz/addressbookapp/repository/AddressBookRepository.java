package com.bridgelabz.addressbookapp.repository;

import com.bridgelabz.addressbookapp.entity.AddressBookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookData, Long> {

    List<AddressBookData> findByFirstName(String firstName);

    List<AddressBookData> findByLastName(String lastName);

    Optional<AddressBookData> findByEmailId(String emailId);

    @Query(value = "select * from addressbook order by city", nativeQuery = true)
    List<AddressBookData> findContactsByCityOrderBy();

    @Query(value = "select * from addressbook where state=:state", nativeQuery = true)
    List<AddressBookData> sortContactByState(String state);
}
