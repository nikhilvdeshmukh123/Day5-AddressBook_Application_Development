package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.AddressBookDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.entity.AddressBookData;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import com.bridgelabz.addressbookapp.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AddressBookRepository addressBookRepository;
    @Autowired
    private ModelMapper modelMapper;

    private List<AddressBookData> list = new ArrayList<>();

    @Override
    public List<AddressBookData> getAddressBookData(String token) {
        Long userId = jwtUtil.decodeToken(token);
        Optional<AddressBookData> isUserPresent = addressBookRepository.findById(userId);
        if (isUserPresent.isPresent()) {
            return addressBookRepository.findAll();
        }
        throw new AddressBookException("User not present");

    }

    @Override
    public AddressBookData getAddressBookDataById(long personId) {
        return addressBookRepository.findById(personId)
                .orElseThrow(() -> new AddressBookException("User Not Found"));
    }

    @Override
    public List<AddressBookData> getPersonByFirstName(String firstName) {
        return addressBookRepository.findByFirstName(firstName);
    }

    @Override
    public List<AddressBookData> getPersonByLastName(String lastName) {
        return addressBookRepository.findByLastName(lastName);
    }

    public Optional<AddressBookData> getPersonByEmailId(String emailId) {
        return addressBookRepository.findByEmailId(emailId);
    }

    @Override
    public AddressBookData createAddressBookData(AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = null;
        String encodedPassword = bCryptPasswordEncoder.encode(addressBookDTO.getPassword());
        addressBookDTO.setPassword(encodedPassword);
        addressBookData = modelMapper.map(addressBookDTO, AddressBookData.class);
//        addressBookData = new AddressBookData(addressBookDTO);
        log.debug("User Data: " + addressBookData.toString());
        return addressBookRepository.save(addressBookData);
    }

    @Override
    public AddressBookData updateAddressBookData(int personId, AddressBookDTO addressBookDTO) {
        AddressBookData addressBookData = this.getAddressBookDataById(personId);
        modelMapper.map(addressBookDTO, addressBookData);
//        addressBookData.updateAddressBookData(addressBookDTO);
        return addressBookRepository.save(addressBookData);
    }

    @Override
    public void deleteAddressBookData(int personId) {
        AddressBookData addressBookData = this.getAddressBookDataById(personId);
        addressBookRepository.delete(addressBookData);
    }

    @Override
    public ResponseDTO login(String emailId, String password) {
        Optional<AddressBookData> isEmailPresent = addressBookRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (bCryptPasswordEncoder.matches(password, isEmailPresent.get().getPassword())) {
                String token = jwtUtil.createToken(isEmailPresent.get().getPersonId());
                return new ResponseDTO("User has logged In", 200, token);
            } else {
                throw new AddressBookException("Password wrong");
            }
        }
        throw new AddressBookException("No Contact Found");
    }

    @Override
    public List<AddressBookData> sortContactsByCityOrderBy() {
        return addressBookRepository.findContactsByCityOrderBy();
    }

    @Override
    public List<AddressBookData> sortContactsByState(String state) {
        return addressBookRepository.sortContactByState(state);
    }
}