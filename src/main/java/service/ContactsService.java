package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Contact;
import model.ContactModel;
import model.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static model.Ui.showExit;

public class ContactsService extends RemoveNonChar {

    public static void showContactListAge(Scanner scanner) {
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        //Ui logic
        Ui.uiShowContactAge(sourceJsonFile, newContact);
        showExit(scanner);
    }

    public static void addNewContact() {
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        ObjectMapper mapper = new ObjectMapper();
        var scanner = new Scanner(System.in);
        try {
            Ui.newContactForList(newContact, mapper, scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveContactToJson(ContactModel newContact, ObjectMapper mapper, Contact contact) throws IOException {
        newContact.addToContactList(contact);
        mapper.writeValue(new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"), newContact);
    }

    public static void deleteContact() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel contactsInJsonFile = sourceJsonFile.sourceContactFile();
        Scanner scanner = Ui.showContactId(mapper, sourceJsonFile, contactsInJsonFile);
        if (scanner == null) return;
        showExit(scanner);
    }
}

