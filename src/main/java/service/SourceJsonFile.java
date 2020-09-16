package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactModel;

import java.io.File;
import java.io.IOException;

public class SourceJsonFile {

    ObjectMapper mapper = new ObjectMapper();
    ContactModel contactsFromJson;

    public ContactModel sourceContactFile() {
        /*Reads data from inputStream and ContactModel class*/
        try {
            contactsFromJson = mapper.readValue(new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"), ContactModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant read json file");
        }
        return contactsFromJson;
    }
}

