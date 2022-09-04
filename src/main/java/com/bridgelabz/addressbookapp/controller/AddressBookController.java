package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDto;
import com.bridgelabz.addressbookapp.dto.ResponseDto;
import com.bridgelabz.addressbookapp.entity.Person;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addressbookservice")
@Slf4j
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDto> getAddressBookDetails() {
        List<Person> personList = addressBookService.getAddressBookDetails();
        ResponseDto responseDto = new ResponseDto("Get Call Success", personList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{personId}")
    public ResponseEntity<ResponseDto> getAddressBookDetailsById(@PathVariable int personId) {
        Person person = addressBookService.getAddressDetailsById(personId);
        ResponseDto responseDto = new ResponseDto("Get call success for ID", person);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAddressBookDetails(@Valid @RequestBody AddressBookDto addressBookDto) {
        log.debug("AddressBook DTO: " + addressBookDto.toString());
        Person person = addressBookService.createAddressBookDetails(addressBookDto);
        ResponseDto responseDto = new ResponseDto("Post call success for Create", person);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateAddressBookDetails(@Valid @PathVariable("id") int personId, @RequestBody AddressBookDto addressBookDto) {
        Person person = addressBookService.updateAddressBookDetails(personId, addressBookDto);
        ResponseDto responseDto = new ResponseDto("Updates Address Book data successfully !", person);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteAddressBookDetails(@PathVariable("id") int personId) {
        addressBookService.deleteAddressDetails(personId);
        ResponseDto responseDto = new ResponseDto("Deleted Successfully: ", "Delete Id: " + personId);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = {"/get/sortbycity"})
    public ResponseEntity<ResponseDto> getContactsByCityUsingOrderBy() {
        List<Person> addressBookDataList = addressBookService.sortContactsByCityOrderBy();
        ResponseDto responseDTO = new ResponseDto("Success call for City!!!", addressBookDataList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/sortbystate/{state}")
    public ResponseEntity<ResponseDto> getContactsByState(@PathVariable("state") String state) {
        List<Person> personList = null;
        personList = addressBookService.sortContactsByState(state);
        ResponseDto responseDTO = new ResponseDto("Get Call For get by state Successful", personList);
        return new ResponseEntity<ResponseDto>(responseDTO, HttpStatus.OK);
    }
}