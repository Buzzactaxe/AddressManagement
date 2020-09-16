package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import org.codehaus.plexus.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public static void addContact(){
            SourceJsonFile sourceJsonFile = new SourceJsonFile();
            ContactModel newContact = sourceJsonFile.sourceContactFile();
            ObjectMapper mapper = new ObjectMapper();
            var scanner = new Scanner(System.in);
        try {
            System.out.println("( ͡° ͜ʖ ͡°) Please insert the details required to add a new contact\n");
            System.out.println("유 Name");
            //Add Contact name and surname
            var addName = RemoveNonChar.removeNonAlphabetChars(scanner.nextLine());
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
            var addContactAge = removeAlphabetChars(scanner.nextLine());

            //Add Contact Street Name
            System.out.println("╦╣ Street Name");
            var addStreetName = removeNonAlphabetChars(scanner.nextLine());

            //Add Contact House Number
            System.out.println("╦╣ House Number");
            var addHouseNumber = removeAlphabetChars(scanner.nextLine());

            //Add Post Code
            System.out.println("╦╣ Post Code");
            var addPostCode = removeAlphabetChars(scanner.nextLine());

            //Add City
            System.out.println("╦╣ City");
            var addCity = removeNonAlphabetChars(scanner.nextLine());

            //Add Home Phone number
            System.out.println("☏ Home Phone Number");
            var addHomePhone = removeAlphabetChars(scanner.nextLine());

            //Add House number
            System.out.println("☏ Mobile Number");
            var addMobilePhone = removeAlphabetChars(scanner.nextLine());

            Address contactAddress = new Address(addStreetName, addHouseNumber, addPostCode, addCity);
            PhoneNumbers contactPhone = new PhoneNumbers(addHomePhone, addMobilePhone);
            JsonNode jsonNode = mapper.readTree(new FileReader("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"));
            var id = Integer.toString(jsonNode.get("contactList").size());
            Contact contact = new Contact(addName, addSurname, addContactAge, contactAddress, contactPhone, id);
            System.out.println("\nConfirm details »»--------►\n" + contact);
            System.out.println("\n- 1: [ Add Contact]\n- 2: [ Cancel operation ]");
            System.out.println(contact.getContactId());
            switch (scanner.nextLine()){
                case "1":
                    newContact.addToContactList(contact);
                    mapper.writeValue(new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\jsonContacts.json"), newContact);
                    System.out.println("\n(¯`·._.· " + contact.getContactName() + " " + contact.getContactSurname() + " Added ·._.·´¯)\n");
                    Ui.showAddOrDeleteUi();
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
        Scanner scanner = ContactModel.getContactId(mapper, sourceJsonFile, contactsInJsonFile);
        if (scanner == null) return;
        showExit(scanner);
    }

}

