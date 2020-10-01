package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "contact")
public class Contact {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @XmlElement(name = "id")
    private String contactId;

    @XmlElement(name = "name")
    private String contactName;

    @XmlElement(name = "surname")
    private String contactSurname;

    @XmlElement(name = "age")
    private String contactAge;

    @XmlElement(name = "Address")
    private Address contactAddress;

    @XmlElement(name = "PhoneNumbers")
    private PhoneNumbers contactPhoneNumbers;

    public Contact() {
    }

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
        this.contactPhoneNumbers = contactPhoneNumbers;
    }

    public String getContactId() {
        return contactId;
    }

    public void setCustomerID(String contactId) {
        this.contactId = contactId;
    }

    public void printContactId() {
        System.out.println(this.contactId);
    }

    public String getContactAge() {
        return contactAge;
    }

    public void setContactAge(String contactAge) {
        this.contactAge = contactAge;
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
        return contactPhoneNumbers;
    }

    public void setContactPhoneNumbers(PhoneNumbers contactPhoneNumbers) {
        this.contactPhoneNumbers = contactPhoneNumbers;
    }

    @Override
    public String toString() {
        return String.format("유 Name: %s \n  Surname: %s \n  Age: %s \n    ╦╣ Address: %s \n     ☏ Phone Numbers:%s", getContactName(), getContactSurname(), getContactAge(), getContactAddress(), getContactPhoneNumbers());
    }
}
