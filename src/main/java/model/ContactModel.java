package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactModel {

    @XmlElementWrapper(name = "contactList")
    @XmlElement(name = "contact")

    private List<Contact> contactList = new ArrayList<>();


    public List<Contact> getContactList() {
        return contactList;
    }

    public void addToContactList(Contact contact) {
        contactList.add(contact);
    }

    public void deleteFromContactList(Contact contact) {
        contactList.remove(contact);
    }

    public void setContactList(List<Contact> cList) {
        this.contactList = cList;
    }

}
