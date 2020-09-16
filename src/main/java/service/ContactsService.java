package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import org.codehaus.plexus.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Ui.showExit;
import static model.Ui.showUI;

public class ContactsService extends RemoveNonChar {

    public static void showContactList(Scanner scanner) {
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        for(Contact contact : newContact.getContactList()){
            if (newContact.getContactList() == null){
                System.out.println("There are no contacts available");
            }
            System.out.println("| Contact ID: " + contact.getContactId() + "\n" + contact.toString() + "\n------|");
        }
        showExit(scanner);
    }

    public static void showContactListAge(Scanner scanner) {
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        //Ui logic
        Ui.uiShowContactAge(sourceJsonFile, newContact);
        showExit(scanner);
    }


    public static void showAddOrDeleteUi() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String intro = "\n What would you like to do next?\n" +
                "- 1: [ Add Contact ]  - 2: [ Delete Contact ]\n"+
                "- 3: [ Main Menu ]    - 4: [ Exit ]\n";
        System.out.println(intro);
        switch(scanner.nextLine()) {
            case "1":
                addContact();
                break;
            case "2":
                deleteContact();
                break;
            case "3":
                showUI();
                break;
            case "4":
                System.exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scanner.nextLine());
        }
    }


    public static void addContact(){
            SourceJsonFile sourceJsonFile = new SourceJsonFile();
            ContactModel newContact = sourceJsonFile.sourceContactFile();
            ObjectMapper mapper = new ObjectMapper();
        try {
            var scanner = new Scanner(System.in);
            System.out.println("( ͡° ͜ʖ ͡°) Please insert the details required to add a new contact\n");
            System.out.println("유 Name");
            //Add Contact name and surname
            String addName = RemoveNonChar.removeNonAlphabetChars(scanner.nextLine());
            while(addName.isBlank()){
                System.out.println("You may not continue without a first name for new contact");
                addName = removeNonAlphabetChars(scanner.nextLine());
            }
            //Add Contact surname
            System.out.println("유 Surname");
            var addSurname = StringUtils.capitalise(removeNonAlphabetChars(scanner.nextLine()));
            while(addSurname.isBlank()){
                System.out.println("You may not continue without a Surname for new contact");
                addSurname = StringUtils.capitalise(removeNonAlphabetChars(scanner.nextLine()));
            }
            //Add Contact age
            System.out.println("유 Age (num format)");
            String addContactAge = removeAlphabetChars(scanner.nextLine());

            //Add Contact Street Name
            System.out.println("╦╣ Street Name");
            String addStreetName = removeNonAlphabetChars(scanner.nextLine());

            //Add Contact House Number
            System.out.println("╦╣ House Number");
            String addHouseNumber = removeAlphabetChars(scanner.nextLine());

            //Add Post Code
            System.out.println("╦╣ Post Code");
            String addPostCode = removeAlphabetChars(scanner.nextLine());

            //Add City
            System.out.println("╦╣ City");
            String addCity = removeNonAlphabetChars(scanner.nextLine());

            //Add Home Phone number
            System.out.println("☏ Home Phone Number");
            String addHomePhone = removeAlphabetChars(scanner.nextLine());

            //Add House number
            System.out.println("☏ Mobile Number");
            String addMobilePhone = removeAlphabetChars(scanner.nextLine());

            Address contactAddress = new Address(addStreetName, addHouseNumber, addPostCode, addCity);
            PhoneNumbers contactPhone = new PhoneNumbers(addHomePhone, addMobilePhone);
            JsonNode jsonNode = mapper.readTree(new FileReader("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"));
            String id = Integer.toString(jsonNode.get("contactList").size());
            Contact contact = new Contact(addName, addSurname, addContactAge, contactAddress, contactPhone, id);
            System.out.println("\nConfirm details »»--------►\n" + contact);
            System.out.println("\n- 1: [ Add Contact]\n- 2: [ Cancel operation ]");
            System.out.println(contact.getContactId());
            switch (scanner.nextLine()){
                case "1":
                    newContact.addToContactList(contact);
                    mapper.writeValue(new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"), newContact);
                    System.out.println("\n(¯`·._.· " + contact.getContactName() + " " + contact.getContactSurname() + " Added ·._.·´¯)\n");
                    showAddOrDeleteUi();
                    break;
                case "2":
                    newContact.deleteFromContactList(contact);
                    System.out.println("Progress has not been saved");
                    showUI();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteContact() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel contactsInJsonFile = sourceJsonFile.sourceContactFile();
        if(contactsInJsonFile.getContactList().isEmpty()){
            System.out.println("there are no contacts to delete");
            showAddOrDeleteUi();
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter  ID of Contact you want to delete");
        System.out.println("Please Enter integer numbers");
        String customerId = removeAlphabetChars(scanner.nextLine());
        while(customerId.isBlank()) {
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
        showExit(scanner);
    }
}

