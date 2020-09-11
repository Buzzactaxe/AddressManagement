package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactModel;

import java.io.IOException;

public class SourceJsonFile {

    public ContactModel sourceContactFile(){
        ContactModel contactsFromJson;
        ObjectMapper mapper = new ObjectMapper();
        try (var contactInputStream = ContactsService.class.getClassLoader().getResourceAsStream("jsonTest.json")) {
            /*Reads data from inputStream and SnailsModel class*/

            assert contactInputStream != null;
            contactsFromJson = mapper.readValue(contactInputStream.readAllBytes(), ContactModel.class);

        } catch (IOException ex) {
            throw new RuntimeException("Can´t read the file 'jsonTest.json'", ex);
        }
        return contactsFromJson;
    }
}

