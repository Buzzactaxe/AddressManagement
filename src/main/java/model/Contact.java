package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {
    private String contactName;
    private String contactSurname;
    private String contactAge;
    private Address contactAddress;
    private String contactId;
    private PhoneNumbers ContactPhoneNumbers;

    public Contact(@JsonProperty("name") String contactName,
                   @JsonProperty("surname") String contactSurname,
                   @JsonProperty("age") String contactAge,
                   @JsonProperty("address") Address contactAddress,
                   @JsonProperty("phoneNumbers") PhoneNumbers contactPhoneNumbers,
                   @JsonProperty("contactId") String contactId) {
        this.contactName = contactName;
        this.contactSurname = contactSurname;
        this.contactAddress = contactAddress;
        this.contactAge = contactAge;
        this.contactId = contactId;
        ContactPhoneNumbers = contactPhoneNumbers;
    }

    public String getContactAge() {
        return contactAge;
    }

    public void printContactId() {
        System.out.println(this.contactId);
    }

    public void setContactAge(String contactAge) {
        this.contactAge = contactAge;
    }

    public String getContactId() {
        return contactId;
    }

    public void setCustomerID(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactSurname() {
        return contactSurname;
    }

    public void setContactSurname(String contactSurname) {
        this.contactSurname = contactSurname;
    }

    public Address getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
    }

    public PhoneNumbers getContactPhoneNumbers() {
        return ContactPhoneNumbers;
    }

    public void setContactPhoneNumbers(PhoneNumbers contactPhoneNumbers) {
        ContactPhoneNumbers = contactPhoneNumbers;
    }



    @Override
    public String toString() {
        return String.format("유 Name: %s \n  Surname: %s \n  Age: %s \n    ╦╣ Address: %s \n     ☏ Phone Numbers:%s", getContactName(), getContactSurname(), getContactAge(), getContactAddress(), getContactPhoneNumbers());
    }
}
