package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;
import model.ContactModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDao implements IFileManager {
    public static final String CONTACTS_JSON = "C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\contactList_jackson.json";

    ObjectMapper mapper = new ObjectMapper();
    //Instance will load only when needed
    private static JsonDao instance = null;

    JsonDao() {
    }

    public static JsonDao getInstance() {
        if (instance == null) instance = new JsonDao();
        return instance;
    }

    @Override
    public void addNew(Contact c) throws IOException {
        ContactModel contactModel = readFile();
        contactModel.addToContactList(c);
        mapper.writeValue(new File(CONTACTS_JSON), contactModel);
    }

    @Override
    //Reads data from inputStream and connects to class
    public ContactModel readFile() {
        try {
            return mapper.readValue(new File(CONTACTS_JSON), ContactModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant read json file");
        }
        return null;
    }

    @Override
    public List<Contact> findAllContacts() {
        return readFile().getContactList();
    }

    @Override
    public List<Contact> findContactsAge() {
        return readFile().getContactList();
    }

    @Override
    public void deleteContact(ContactModel c) throws IOException {
        mapper.writeValue(new File(CONTACTS_JSON), c);
    }

    @Override
    public String findId() {
        int contactId = readFile().getContactList().size();
        return Integer.toString(contactId);
    }

}

