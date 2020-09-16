package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;
import service.SourceJsonFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static service.RemoveNonChar.removeAlphabetChars;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactModel {
    public static ObjectMapper mapper;
    private static ContactModel contactModel;
    private Contact contact;

    private  List<Contact> contactList = new ArrayList<>();

    public ContactModel() {}

    public List<Contact> getContactList() {

        return contactList;
    }

    public void addToContactList(Contact contact) {
        contactList.add(contact);
    }

    public void deleteFromContactList(Contact contact) {
        contactList.remove(contact);
    }

    public static Scanner getContactId(ObjectMapper mapper, SourceJsonFile sourceJsonFile, ContactModel contactsInJsonFile) throws IOException {
        @Nullable Scanner result = null;
        if (!contactsInJsonFile.getContactList().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter  ID of Contact you want to delete");
            System.out.println("Please Enter integer numbers");
            String customerId = removeAlphabetChars(scanner.nextLine());
            while (customerId.isBlank()) {
                System.out.println("Incorrect data, num ID required to proceed with contact removal.");
                customerId = removeAlphabetChars(scanner.nextLine());
            }
            List<Contact> contactList = new ArrayList<>();
            for (Contact contact : sourceJsonFile.sourceContactFile().getContactList()) {
                if (!contact.getContactId().equals(customerId)) {
                    contactList.add(contact);
                }
            }
            contactsInJsonFile.setContactList(contactList);
            mapper.writeValue(new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"), contactsInJsonFile);
            System.out.println("Contact " + customerId + " Deleted!");
            result = scanner;
        } else {
            System.out.println("Contact List is empty");
            Ui.showAddOrDeleteUi();
        }

        return result;
    }

    public void setContactList(List<Contact> cList) {
        this.contactList = cList;
    }


}
