package at.fhv.ss22.ea.f.customerDbService;

import java.util.Objects;
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

    private CustomerDTO() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CustomerDTO customer;
        Builder() {
            this.customer = new CustomerDTO();
        }

        public CustomerDTO build() {
            Objects.requireNonNull(this.customer.id, "CustomerDTO requires an ID");
            return this.customer;
        }

        public Builder id(UUID id) {
            this.customer.id = id;
            return this;
        }

        public Builder givenName(String name) {
            this.customer.givenName = name;
            return this;
        }
        public Builder familyName(String name) {
            this.customer.familyName = name;
            return this;
        }
        public Builder birthDate(String birthDate) {
            this.customer.birthDate = birthDate;
            return this;
        }
        public Builder street(String street) {
            this.customer.street = street;
            return this;
        }
        public Builder houseNumber(String houseNumber) {
            this.customer.houseNumber = houseNumber;
            return this;
        }
        public Builder postalCode(String postalCode) {
            this.customer.postalCode = postalCode;
            return this;
        }
        public Builder city(String city) {
            this.customer.city = city;
            return this;
        }
        public Builder country(String country) {
            this.customer.country = country;
            return this;
        }
        public Builder email(String email) {
            this.customer.email = email;
            return this;
        }
        public Builder phoneNumber(String phoneNumber) {
            this.customer.phoneNumber = phoneNumber;
            return this;
        }
        public Builder mobileNumber(String mobileNumber) {
            this.customer.mobileNumber = mobileNumber;
            return this;
        }
        public Builder iban(String iban) {
            this.customer.iban = iban;
            return this;
        }
        public Builder creditCardNumber(String creditCardNumber) {
            this.customer.creditCardNumber = creditCardNumber;
            return this;
        }
        public Builder creditCardValidationCode(String creditCardValidationCode) {
            this.customer.creditCardValidationCode = creditCardValidationCode;
            return this;
        }

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
