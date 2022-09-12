package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.entity.AddressBookData;

import java.util.List;
import java.util.Optional;

public interface AddressBookService {
    List<AddressBookData> getAddressBookData(String token);

    AddressBookData getAddressBookDataById(long personId);

    List<AddressBookData> getPersonByFirstName(String firstName);

    List<AddressBookData> getPersonByLastName(String lastName);

    AddressBookData createAddressBookData(AddressBookDTO addressBookDTO);

    AddressBookData updateAddressBookData(int personId, AddressBookDTO addressBookDTO);

    void deleteAddressBookData(int personId);

    ResponseDTO login(String emailId, String password);

    List<AddressBookData> sortContactsByCityOrderBy();

    List<AddressBookData> sortContactsByState(String state);

    Optional<AddressBookData> getPersonByEmailId(String emailId);
}
