package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.plexus.util.StringUtils;
import org.jetbrains.annotations.Nullable;
import service.ContactsService;
import service.RemoveNonChar;
import service.SourceJsonFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static service.ContactsService.*;

public class Ui {

    public static void showMainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput = StringUtils.capitalise(scanner.nextLine());
            switch (userInput) {
                case "1":
                    showContactList(scanner);
                    break;

                case "2":
                    showContactListAge(scanner);
                    showExit(scanner);
                    break;

                case "Exit":
                    System.out.println("༼ つ ◕_◕ ༽つ You left the project, Goodbye!!");
                    System.exit(0);
                    break;

                case "4":
                    addContactUi();
                    break;

                case "5":
                    deleteContact();
                    break;
                default:
                    showUI();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showUI() {
        System.out.println(
                "┌───── •✧✧• ─────┐\n" +
                        " -Contact Manager- \n" +
                        "└───── •✧✧• ─────┘");
        String intro = "\n" + "1 = [ All Contacts ]\n" + "2 = [ Contacts Age ]\n" + "Exit = [ Exit ] \n" + "\n4 = [ Add Contact ] \n" + "5 = [ Delete Contact ]";
        System.out.println(intro);
        Ui.showMainMenu();
    }

    public static void showAddOrDeleteUi() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String intro = "\n What would you like to do next?\n" +
                "- 1: [ Add Contact ]  - 2: [ Delete Contact ]\n" +
                "- 3: [ Main Menu ]    - 4: [ Exit ]\n";
        System.out.println(intro);
        switch (scanner.nextLine()) {
            case "1":
                addContactUi();
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
                System.out.println("༼ つ ◕_◕ ༽つ You left the project, Goodbye!!");
                throw new IllegalStateException("Unexpected value: " + scanner.nextLine());

        }
    }

    public static void showContactList(Scanner scanner) {
        SourceJsonFile sourceJsonFile = new SourceJsonFile();
        ContactModel newContact = sourceJsonFile.sourceContactFile();
        for (Contact contact : newContact.getContactList()) {
            if (newContact.getContactList() == null) {
                System.out.println("There are no contacts available");
                break;
            }
            System.out.println("| Contact ID: " + contact.getContactId() + "\n" + contact.toString() + "\n------|");
        }
        showExit(scanner);
    }

    public static void showContactAge(SourceJsonFile sourceJsonFile, ContactModel newContact) {
        for (Contact ageOfContacts : sourceJsonFile.sourceContactFile().getContactList()) {
            if (newContact.getContactList() == null) {
                System.out.println("There are no contacts available");
            }
            System.out.println("| Contact Name: " + ageOfContacts.getContactName() + "\n  Age: " + ageOfContacts.getContactAge() + "\n------|");
        }
    }

    public static void showExit(Scanner s) throws NoSuchElementException {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDo you want to go back to the main menu?\n1 = [ Main Menu ] 2 = [ Exits Program ] ");
        String inputUser = input.nextLine().toUpperCase();
        switch (inputUser) {
            case "1":
                showUI();
                break;
            case "2":
                System.out.println("༼ つ ◕_◕ ༽つ You are out of the project, Goodbye!!");
                System.exit(0);
                break;
            default:
                System.out.println("༼ つ ◕_◕ ༽つ You left the project, Goodbye!!");
                throw new NoSuchElementException("Unexpected value: " + s.nextLine());
        }
    }

    public static void addContactUi() {
        ContactModel newContact = new ContactModel();
        ObjectMapper mapper = new ObjectMapper();
        try (var scanner = new Scanner(System.in)) {

            System.out.println("( ͡° ͜ʖ ͡°) Please insert the details required to add a new contact\n");
            System.out.println("유 Name");
            //Add Contact name
            var addName = RemoveNonChar.removeNonAlphabetChars(scanner.nextLine());
            while (addName.isBlank()) {
                System.out.println("You may not continue without a first name for new contact");
                addName = removeNonAlphabetChars(scanner.nextLine());
            }
            //Add Contact surname
            System.out.println("유 Surname");
            var addSurname = StringUtils.capitalise(removeNonAlphabetChars(scanner.nextLine()));
            while (addSurname.isBlank()) {
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
            String id = ContactsService.getContactId(mapper);
            Contact contact = new Contact(addName, addSurname, addContactAge, contactAddress, contactPhone, id);
            System.out.println("\nConfirm details »»--------►\n" + contact);
            System.out.println("\n- 1: [ Add Contact]\n- 2: [ Cancel operation ]");
            // TODO: just for testing if Id is correct remove later
            System.out.println(contact.getContactId());
            switch (scanner.nextLine()) {
                case "1":
                    ContactsService.saveContactToJson(newContact, mapper, contact);
                    System.out.println(
                            "\n░░░░░░░░░░░░░░░░░░░░░░█████████░░░░░░░░░\n" +
                                    "░░███████░░░░░░░░░░███▒▒▒▒▒▒▒▒███░░░░░░░\n" +
                                    "░░█▒▒▒▒▒▒█░░░░░░░███▒▒▒▒▒▒▒▒▒▒▒▒▒███░░░░\n" +
                                    "░░░█▒▒▒▒▒▒█░░░░██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░░\n" +
                                    "░░░░█▒▒▒▒▒█░░░██▒▒▒▒▒██▒▒▒▒▒▒██▒▒▒▒▒███░\n" +
                                    "░░░░░█▒▒▒█░░░█▒▒▒▒▒▒████▒▒▒▒████▒▒▒▒▒▒██\n" +
                                    "░░░█████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██\n" +
                                    "░░░█▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒██\n" +
                                    "░██▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒██▒▒▒▒▒▒▒▒▒▒██▒▒▒▒██\n" +
                                    "██▒▒▒███████████▒▒▒▒▒██▒▒▒▒▒▒▒▒██▒▒▒▒▒██\n" +
                                    "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒████████▒▒▒▒▒▒▒██\n" +
                                    "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░\n" +
                                    "░█▒▒▒███████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██░░░\n" +
                                    "░██▒▒▒▒▒▒▒▒▒▒████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█░░░░░\n" +
                                    "░░████████████░░░█████████████████░░░░░░" +
                                    "\n(¯`·._.· " + contact.getContactName() + " " + contact.getContactSurname() + " Added ·._.·´¯)");
                    showAddOrDeleteUi();
                    break;
                case "2":
                    System.out.println("Progress has not been saved");
                    showUI();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Scanner deleteContactUi(ObjectMapper mapper, SourceJsonFile sourceJson, ContactModel newContact) throws IOException {
        @Nullable Scanner result = null;
        if (!newContact.getContactList().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter  ID of Contact you want to delete");
            System.out.println("Please Enter integer numbers");
            String customerId = removeAlphabetChars(scanner.nextLine());
            while (customerId.isBlank()) {
                System.out.println("Incorrect data, num ID required to proceed with contact removal.");
                customerId = removeAlphabetChars(scanner.nextLine());
            }
            ContactsService.deleteIdFromList(mapper, sourceJson, newContact, customerId);
            System.out.println("Contact " + customerId + " Deleted!");
            result = scanner;
        } else {
            System.out.println("Contact List is empty");
            showAddOrDeleteUi();
        }
        return result;
    }
}
