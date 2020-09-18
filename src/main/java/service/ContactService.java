package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;
import model.ContactModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactService extends RemoveNonChar {
    //Giving null will not load until it is needed
    private static ContactService instance = null;

    private ContactService() {
    }

    //Instance will load only when needed
    public static ContactService getInstance() {
        if (instance == null) instance = new ContactService();
        return instance;
    }

    //Adds contact from Ui to database
    //DONE!
    public static void addContactToDatabase(Contact contact) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ContactDao contactDao = ContactDao.getContactDoa();
        ContactModel newContact = contactDao.readJsonDao();
        newContact.addToContactList(contact);
        ContactDao.writeToJson(newContact, mapper);
    }

    public static String findCurrentContactId() throws IOException {
        return Integer.toString(ContactDao.getJsonObject().get("contactList").size());
    }

    public static JsonNode findContactList() throws IOException {
        return ContactDao.getJsonObject().get("contactList");
    }

    public static void deleteIdFromList(String customerId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ContactDao contactDao = ContactDao.getContactDoa();
        var contactList = deleteContactId(customerId);
        ContactModel newContact = contactDao.readJsonDao();
        newContact.setContactList(contactList);
        ContactDao.writeToJson(newContact, mapper);
    }

    public static List<Contact> deleteContactId(String customerId) {
        List<Contact> contactList = new ArrayList<>();
        ContactDao contactDao = ContactDao.getContactDoa();
        for (Contact contact : contactDao.readJsonDao().getContactList()) {
            if (!contact.getContactId().equals(customerId)) {
                contactList.add(contact);
            }
        }
        return contactList;
    }


}

