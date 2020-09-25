package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;
import model.ContactModel;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonDao implements IFileManager {
    public static final String CONTACTS_JSON = "C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\contactList_jackson.json";

    ObjectMapper mapper = new ObjectMapper();
    ContactModel contactsFromJson;
    //Instance will load only when needed
    private static JsonDao instance = null;

    JsonDao() {
    }

    public static JsonDao getDao() {
        if (instance == null) instance = new JsonDao();
        return instance;
    }

    @Override
    public void addNew(Contact contact) throws IOException {
        mapper.writeValue(new File(CONTACTS_JSON), contactsFromJson);
    }

    @Override
    //Reads data from inputStream and connects to class
    public ContactModel readFile() {
        try {
            contactsFromJson = mapper.readValue(new File(CONTACTS_JSON), ContactModel.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant read json file");
        }
        return contactsFromJson;
    }

    @Override
    public void getAllContacts() {
        for (Contact contact : readFile().getContactList())
            if (contactsFromJson.getContactList().isEmpty()) {
                System.out.println("There are no contacts available");
                break;
            } else {
                System.out.println("| Contact ID: " + contact.getContactId() + "\n" + contact.toString() + "\n------|");
            }
    }

    @Override
    public void getAllContactAge() {
        for (Contact ageOfContacts : readFile().getContactList()) {
            if (contactsFromJson.getContactList() == null) {
                System.out.println("There are no contacts available");
            }
            System.out.println("| Contact Name: " + ageOfContacts.getContactName() + "\n  Age: " + ageOfContacts.getContactAge() + "\n------|");
        }
    }

    @Override
    public void remove(ContactModel contactModel) throws IOException {
        mapper.writeValue(new File(CONTACTS_JSON), contactsFromJson);
    }

    @Override
    public String findId() throws IOException {
        return Integer.toString(getDao().getFileData().get("contactList").size());
    }

    @Override
    public JsonNode getFileData() throws IOException {
        return mapper.readTree(new FileReader(CONTACTS_JSON));
    }

}

