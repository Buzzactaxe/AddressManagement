package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import model.Contact;
import model.ContactModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDao implements IFileManager {
    public static final String CONTACTS_JSON = "C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\contactList_jackson.json";

    ObjectMapper mapper = new ObjectMapper().registerModule(new Jdk8Module());
    //Instance will load only when needed
    private static JsonDao instance = null;

    JsonDao() {
    }

    public static JsonDao getInstance() {
        if (instance == null) instance = new JsonDao();
        return instance;
    }

    @Override
    public void addNew(Contact c) {
        ContactModel contactModel = readFile();
        contactModel.addToContactList(c);
        try {
            mapper.writeValue(new File(CONTACTS_JSON), contactModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //Reads data from inputStream and connects to class
    public ContactModel readFile() {
        try {
            return mapper.readValue(new File(CONTACTS_JSON), ContactModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot read Json file");
        }
        return null;
    }

    @Override
    public List<Contact> findAllContacts() {
        try {
            return readFile().getContactList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void saveAll(ContactModel c) throws IOException {
        mapper.writeValue(new File(CONTACTS_JSON), c);
    }

    @Override
    public Integer findId() {
        if (readFile().getContactList() != null) {
            return readFile().getContactList().size();
        }
        return 0;
    }

}

