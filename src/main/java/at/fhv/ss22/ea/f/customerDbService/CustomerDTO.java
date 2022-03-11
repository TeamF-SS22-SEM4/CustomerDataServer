package at.fhv.ss22.ea.f.customerDbService;

import java.util.UUID;

public class CustomerDTO {
    private UUID id;
    private String givenName;
    private String familyName;
    private String birthDate;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private String email;
    private String phoneNumber;
    private String mobileNumber;
    private String iban;
    private String creditCardNumber;
    private String creditCardValidationCode;

    public CustomerDTO(UUID id, String givenName, String familyName, String birthDate, String street, String houseNumber, String postalCode, String city, String country, String email, String phoneNumber, String mobileNumber, String iban, String creditCardNumber, String creditCardValidationCode) {
        this.id = id;
        this.givenName = givenName;
        this.familyName = familyName;
        this.birthDate = birthDate;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.iban = iban;
        this.creditCardNumber = creditCardNumber;
        this.creditCardValidationCode = creditCardValidationCode;
    }

    public UUID getId() {
        return id;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getIban() {
        return iban;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCreditCardValidationCode() {
        return creditCardValidationCode;
    }
}
