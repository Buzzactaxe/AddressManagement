package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;
import model.ContactModel;
import model.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Ui.showExit;

public class ContactService extends RemoveNonChar {

    // TODO: 17/09/2020 remove UI from ContactService
    public static void showContactListAge(Scanner scanner) {
        ContactDao contactDao = new ContactDao();
        ContactModel newContact = contactDao.readJsonDao();
        Ui.showContactAge(contactDao, newContact);
        showExit(scanner);
    }

    //Adds contact from Ui to database
    //DONE!
    public static void addContactToDatabase(Contact contact) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ContactDao contactDao = new ContactDao();
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
        ContactDao contactDAO = new ContactDao();
        var contactList = deleteContactId(customerId);
        ContactModel newContact = contactDAO.readJsonDao();
        newContact.setContactList(contactList);
        ContactDao.writeToJson(newContact, mapper);
    }

    public static List<Contact> deleteContactId(String customerId) {
        List<Contact> contactList = new ArrayList<>();
        ContactDao contactDao = new ContactDao();
        for (Contact contact : contactDao.readJsonDao().getContactList()) {
            if (!contact.getContactId().equals(customerId)) {
                contactList.add(contact);
            }
        }
        return contactList;
    }
}

