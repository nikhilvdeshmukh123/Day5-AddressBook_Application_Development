package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.entity.Person;

import java.util.List;

public interface AddressBookService {
    List<Person> getAddressBookDetails();
    Person getAddressDetailsById(int personId);
    Person createAddressBookDetails(AddressBookDto addressBookDto);
    Person updateAddressBookDetails(int personId, AddressBookDto addressBookDto);
    void deleteAddressDetails(int personId);

    List<Person> sortContactsByCityOrderBy();
    List<Person> sortContactsByState(String state);
}
