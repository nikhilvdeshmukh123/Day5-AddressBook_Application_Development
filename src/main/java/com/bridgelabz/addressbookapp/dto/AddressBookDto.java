package com.bridgelabz.addressbookapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
public class AddressBookDto {
    @NotEmpty(message="First name cant be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid First Name")
    public String firstName;
    @NotEmpty(message="Last name cant be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid Last Name")
    public String lastName;
    @Pattern(regexp = "male|female", message = "Gender needs to be male or female !")
    public String gender;
    @NotBlank(message = "Address cannot be empty")
    public String address;
    @NotBlank(message = "City cannot be empty")
    public String city;
    @NotBlank(message = "State cannot be empty")
    public String state;
    @NotNull(message="Zip code cant be empty")
    //@Pattern(regexp = "^[1-9][0-9]{6}$", message = "Zip Code is Invalid !")
    public int zipCode;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email ID is Invalid !")
    public String emailId;
    @NotNull(message="Phone Number cant be empty")
    //@Pattern(regexp = "^[7-9]{1}[0-9]{9}", message = "Mobile Number is Invalid !")
    public Long phoneNumber;
}