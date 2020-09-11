package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

    @JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumbers {
    private  String housePhone;
    private String mobilePhone;


    public PhoneNumbers(@JsonProperty("housePhone")String housePhone,@JsonProperty("mobilePhone") String mobilePhone) {
        this.housePhone = housePhone;
        this.mobilePhone = mobilePhone;

    }

    public String getHousePhone() {
        return housePhone;
    }

    public void setHousePhone(String housePhone) {
        this.housePhone = housePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }


    @Override
    public String toString() {
        StringBuilder contactDetails = new StringBuilder();
        contactDetails.append(String.format("\n          - Land Line Number: %s \n          - Mobile: %s ", getHousePhone(), getMobilePhone()));
        return contactDetails.toString();
    }
}
