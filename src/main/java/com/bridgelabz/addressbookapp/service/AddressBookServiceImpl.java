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
        return addressBookRepository.findAll();
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
        personDetails.updateAddressBookData(addressBookDto);
        return addressBookRepository.save(personDetails);
    }

    @Override
    public void deleteAddressDetails(int personId) {
        Person personDetails = this.getAddressDetailsById(personId);
        addressBookRepository.delete(personDetails);
    }

    @Override
    public List<Person> sortContactsByCityOrderBy() {
        return addressBookRepository.findContactsByCityOrderBy();
    }

    @Override
    public List<Person> sortContactsByState(String state) {
        return addressBookRepository.sortContactByState(state);
    }
}