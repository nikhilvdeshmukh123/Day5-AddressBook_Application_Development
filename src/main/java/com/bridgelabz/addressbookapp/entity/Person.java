package com.bridgelabz.addressbookapp.entity;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addressbook")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private int personId;
    @Column(name = "first_name")
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private Long phoneNumber;
    private String emailId;

    public Person ( AddressBookDto addressBookDto) {
        this.updateAddressBookData(addressBookDto);
    }

    public void updateAddressBookData(AddressBookDto addressBookDto) {
        this.firstName = addressBookDto.firstName;
        this.lastName = addressBookDto.lastName;
        this.gender = addressBookDto.gender;
        this.address = addressBookDto.address;
        this.city = addressBookDto.city;
        this.state = addressBookDto.state;
        this.zipCode = addressBookDto.zipCode;
        this.phoneNumber = addressBookDto.phoneNumber;
        this.emailId = addressBookDto.emailId;
    }

    public Person() {

    }
}