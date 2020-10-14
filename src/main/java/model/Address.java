package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("\n      - Street: %s\n      - House Number: %s  \n      - Post Code: %s \n      - Country: %s", getStreetName(), getHouseNumber(), getPostCode(), getCity());

    }
}
