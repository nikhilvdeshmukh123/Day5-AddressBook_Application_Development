package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.entity.Person;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;
    private List<Person> personList = new ArrayList<>();

    @Override
    public List<Person> getAddressBookDetails() {
        return personList;
    }

    @Override
    public Person getAddressDetailsById(int personId) {
        return getAddressBookDetails().stream()
                .filter(personData -> personData.getPersonId() == personId)
                .findFirst()
                .orElseThrow(() -> new AddressBookException("Person Not Found"));
    }

    @Override
    public Person createAddressBookDetails(AddressBookDto addressBookDto) {
        Person person = null;
        person = new Person( addressBookDto);
        personList.add(person);
        log.debug("Person data: " + person.toString());
        return addressBookRepository.save(person);
    }

    @Override
    public Person updateAddressBookDetails(int personId, AddressBookDto addressBookDto) {
        Person personDetails = this.getAddressDetailsById(personId);
        personDetails.setFirstName(addressBookDto.firstName);
        personDetails.setLastName(addressBookDto.lastName);
        personDetails.setGender(addressBookDto.gender);
        personDetails.setAddress(addressBookDto.address);
        personDetails.setCity(addressBookDto.city);
        personDetails.setState(addressBookDto.state);
        personDetails.setZipCode(addressBookDto.zipCode);
        personDetails.setEmailId(addressBookDto.emailId);
        personDetails.setPhoneNumber(addressBookDto.phoneNumber);
        personList.set(personId-1, personDetails);
        return personDetails;
    }

    @Override
    public void deleteAddressDetails(int personId) {
        Person personDetails = this.getAddressDetailsById(personId);
        personList.remove(personDetails);
    }
}