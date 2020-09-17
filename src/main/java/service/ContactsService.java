package service;

import com.fasterxml.jackson.databind.JsonNode;
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
    //DONE!
    public static void addContactToDatabase(Contact contact) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        newContact.addToContactList(contact);
        SourceJsonFile.writeToJson(newContact, mapper);
    }

    public static String findCurrentContactId() throws IOException {
        return Integer.toString(SourceJsonFile.getJsonNode().get("contactList").size());
    }

    public static JsonNode findContactList() throws IOException {
        return SourceJsonFile.findJsonList();
    }

    public static void deleteIdFromList(String customerId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        var contactList = Contact.deleteContactId(customerId);
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        newContact.setContactList(contactList);
        SourceJsonFile.writeToJson(newContact, mapper);
    }
}

