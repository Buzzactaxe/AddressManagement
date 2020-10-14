package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement


@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumbers {
    private String housePhone;
    private String mobilePhone;

    public PhoneNumbers() {
    }

    public PhoneNumbers(@JsonProperty("housePhone") String housePhone, @JsonProperty("mobilePhone") String mobilePhone) {
        this.housePhone = housePhone;
        this.mobilePhone = mobilePhone;
    }

    //@XmlElement(name = "housePhone")
    public String getHousePhone() {
        return housePhone;
    }

    public void setHousePhone(String housePhone) {
        this.housePhone = housePhone;
    }

    //@XmlElement(name = "mobilePhone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    @Override
    public String toString() {
        return String.format("\n          - Land Line Number: %s \n          - Mobile: %s ", getHousePhone(), getMobilePhone());
    }
}
