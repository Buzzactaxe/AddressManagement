package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    private String streetName;
    private String houseNumber;
    private String postCode;
    private String city;

    public Address() {
    }

    public Address(@JsonProperty("streetName") String streetName,
                   @JsonProperty("houseNumber") String houseNumber,
                   @JsonProperty("postCode") String postCode,
                   @JsonProperty("city") String city) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
        this.city = city;
    }

    //@XmlElement(name = "streetName")
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    //@XmlElement(name = "houseNumber")
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    //@XmlElement(name = "postCode")
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    //@XmlElement(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {

        StringBuilder contactDetails = new StringBuilder();
        contactDetails.append(String.format("\n      - Street: %s\n      - House Number: %s  \n      - Post Code: %s \n      - Country: %s", getStreetName(), getHouseNumber(), getPostCode(), getCity()));
        return contactDetails.toString();

    }
}
