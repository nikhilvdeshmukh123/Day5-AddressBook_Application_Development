package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.entity.AddressBookData;
import com.bridgelabz.addressbookapp.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbookservice")
@Slf4j
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @RequestMapping(value = {"", "/", "/get"})
    public ResponseEntity<ResponseDTO> getAddressBookData(@RequestHeader String token) {
        List<AddressBookData> addressBookDataList = null;
        addressBookDataList = addressBookService.getAddressBookData(token);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", 200, addressBookDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping("/firstName")
    public ResponseEntity<ResponseDTO> getPersonByFirstName(@RequestHeader String token, @RequestParam String firstName) {
        List<AddressBookData> addressBookDataList = null;
        addressBookDataList = addressBookService.getPersonByFirstName(firstName);
        ResponseDTO responseDTO = new ResponseDTO("Request Call For First Name Successfully", 200, addressBookDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping("/lastName")
    public ResponseEntity<ResponseDTO> getPersonByLastName(@RequestHeader String token, @RequestParam String lastName) {
        List<AddressBookData> addressBookDataList = null;
        addressBookDataList = addressBookService.getPersonByLastName(lastName);
        ResponseDTO responseDTO = new ResponseDTO("Request Call For Last Name Successfully", 200, addressBookDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{personId}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@RequestHeader String token, @PathVariable("personId") long personId) {
        AddressBookData addressBookData = null;
        addressBookData = addressBookService.getAddressBookDataById(personId);
        ResponseDTO responseDTO = new ResponseDTO("Get Call For Id Successful!!", 200, addressBookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping("/emailId")
    public ResponseEntity<ResponseDTO> getPersonByEmailId(@RequestParam String emailId) {
        Optional<AddressBookData> addressBookDataList = null;
        addressBookDataList = addressBookService.getPersonByEmailId(emailId);
        ResponseDTO responseDTO = new ResponseDTO("Request Call For EmailId Successfully", 200, addressBookDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addAddressBookData(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.debug("User Dto: " + addressBookDTO.toString());
        AddressBookData addressBookData = null;
        addressBookData = addressBookService.createAddressBookData(addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Created Address Book Data Successfully: ", 200, addressBookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{personId}")
    public ResponseEntity<ResponseDTO> updateAddressBookData(@PathVariable("personId") int personId, @Valid @RequestBody AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = null;
        addressBookData = addressBookService.updateAddressBookData(personId, addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Address Book Data Successfully:", 200, addressBookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity<ResponseDTO> deleteAddressBookData(@PathVariable("personId") int personId) {
        addressBookService.deleteAddressBookData(personId);
        ResponseDTO responseDTO = new ResponseDTO("Address Book Data Deleted Successfully: ", 200, "Delete Id:" + personId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseDTO loginAddressBook(@RequestParam String emailId, @RequestParam String password) {
        return addressBookService.login(emailId, password);
    }

    @GetMapping(value = {"/get/sortbycity"})
    public ResponseEntity<ResponseDTO> getContactsByCityUsingOrderBy() {
        List<AddressBookData> addressBookDataList = addressBookService.sortContactsByCityOrderBy();
        ResponseDTO responseDTO = new ResponseDTO("Success call for City!!!", 400, addressBookDataList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/sortbystate/{state}")
    public ResponseEntity<ResponseDTO> getContactsByState(@PathVariable("state") String state) {
        List<AddressBookData> personList = null;
        personList = addressBookService.sortContactsByState(state);
        ResponseDTO responseDTO = new ResponseDTO("Get Call For get by state Successful", 400, personList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}