package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ContactModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ContactDao {
    public static String CONTACTS_JSON = "C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\contactList_jackson.json";

    ObjectMapper mapper = new ObjectMapper();
    ContactModel contactsFromJson;
    //Instance will load only when needed
    private static ContactDao instance = null;

    private ContactDao() {
    }

    public static ContactDao getContactDoa() {
        if (instance == null) instance = new ContactDao();
        return instance;
    }

    //Reads data from inputStream and connects to class
    public ContactModel readJsonDao() {
        try {
            contactsFromJson = mapper.readValue(new File(CONTACTS_JSON), ContactModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant read json file");
        }
        return contactsFromJson;
    }

    public static JsonNode getJsonObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new FileReader(CONTACTS_JSON));
    }

    public static void writeToJson(ContactModel newContact, ObjectMapper mapper) throws IOException {
        mapper.writeValue(new File(CONTACTS_JSON), newContact);
    }


}

