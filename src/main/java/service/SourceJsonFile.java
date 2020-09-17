package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SourceJsonFile {

    ObjectMapper mapper = new ObjectMapper();
    ContactModel contactsFromJson;

    //Reads data from inputStream and connects to class
    public ContactModel sourceContactFile() {
        try {
            contactsFromJson = mapper.readValue(new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"), ContactModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant read json file");
        }
        return contactsFromJson;
    }

    public static JsonNode getJsonNode() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new FileReader("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"));
    }

    public static JsonNode findJsonList() throws IOException {
        return getJsonNode().get("contactList");
    }

    public static void writeToJson(ContactModel newContact, ObjectMapper mapper) throws IOException {
        mapper.writeValue(new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"), newContact);
    }


}

