package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;
import model.ContactModel;
import model.Ui;

import java.io.IOException;
import java.util.Scanner;

import static model.Ui.showExit;

public class ContactsService extends RemoveNonChar {

    // TODO: 17/09/2020 remove UI from ContactService
    public static void showContactListAge(Scanner scanner) {
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        Ui.showContactAge(sourceJsonFile, newContact);
        showExit(scanner);
    }
    //Adds contact from Ui to database
    public static void addContactToDatabase(Contact contact) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        newContact.addToContactList(contact);
        SourceJsonFile.writeToJson(newContact, mapper);
    }

    // TODO: 17/09/2020 remove Ui
    public static void deleteContact() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel contactsInJsonFile = sourceJsonFile.sourceContactFile();
        Scanner scanner = Ui.showContactId(mapper, sourceJsonFile, contactsInJsonFile);
        if (scanner == null) return;
        showExit(scanner);
    }

    public static String findContactId() throws IOException {
        return Integer.toString(SourceJsonFile.getJsonNode().get("contactList").size());
    }

    public static void deleteIdFromList(ObjectMapper mapper, SourceJsonFile sourceJson, ContactModel newContact, String customerId) throws IOException {
        var contactList = Contact.deleteContactId(sourceJson, customerId);
        newContact.setContactList(contactList);
        SourceJsonFile.writeToJson(newContact, mapper);
    }
}

