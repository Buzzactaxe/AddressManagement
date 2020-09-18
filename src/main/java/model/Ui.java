package model;

import org.codehaus.plexus.util.StringUtils;
import service.ContactsService;
import service.RemoveNonChar;
import service.ContactDao;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static service.ContactsService.*;

public class Ui {

    public static void showMainMenuUi() {
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput = StringUtils.capitalise(scanner.nextLine());
            switch (userInput) {
                case "1":
                    showContactListUi(scanner);
                    break;

                case "2":
                    showContactListAge(scanner);
                    showExitUi(scanner);
                    break;

                case "Exit":
                    System.out.println("༼ つ ◕_◕ ༽つ You left the project, Goodbye!!");
                    System.exit(0);
                    break;

                case "4":
                    addContactUi();
                    break;

                case "5":
                    deleteContactUi();
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
        Ui.showMainMenuUi();
    }

    public static void showAddOrDeleteUi() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String intro = "\n What would you like to do next?\n" +
                "- 1: [ Add Contact ]     - 2: [ Delete Contact ]\n" +
                "- 3: [ Show Contacts ]   - 4: [ Main Menu ]\n\n- 5: [ Exit ]\n";
        System.out.println(intro);
        switch (scanner.nextLine()) {
            case "1":
                addContactUi();
                break;
            case "2":
                deleteContactUi();
                break;
            case "3":
                showContactListUi(scanner);
                break;
            case "4":
                showUI();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("༼ つ ◕_◕ ༽つ You left the project, Goodbye!!");
                throw new IllegalStateException("Unexpected value: " + scanner.nextLine());
        }
    }

    public static void showContactListUi(Scanner scanner) {
        ContactDao contactDAO = new ContactDao();
        ContactModel newContact = contactDAO.readJsonDao();
        for (Contact contact : newContact.getContactList()) {
            if (newContact.getContactList() == null) {
                System.out.println("There are no contacts available");
                break;
            }
            System.out.println("| Contact ID: " + contact.getContactId() + "\n" + contact.toString() + "\n------|");
        }
        showExitUi(scanner);
    }

    public static void showContactAge(ContactDao contactDAO, ContactModel newContact) {
        for (Contact ageOfContacts : contactDAO.readJsonDao().getContactList()) {
            if (newContact.getContactList() == null) {
                System.out.println("There are no contacts available");
            }
            System.out.println("| Contact Name: " + ageOfContacts.getContactName() + "\n  Age: " + ageOfContacts.getContactAge() + "\n------|");
        }
    }

    public static void showExitUi(Scanner s) throws NoSuchElementException {
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

    //WORKING
    public static void addContactUi() throws IOException {
        var scanner = new Scanner(System.in);
        System.out.println("( ͡° ͜ʖ ͡°) Please insert the details required to add a new contact\n");
        System.out.println("유 Name");
        //Add Contact name and surname
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
        var id = findCurrentContactId();
        Contact contact = new Contact(addName, addSurname, addContactAge, contactAddress, contactPhone, id);
        System.out.println("\nConfirm details »»--------►\n" + contact);
        System.out.println("\n- 1: [ Add Contact]\n- 2: [ Cancel operation ]");
        System.out.println(contact.getContactId());
        switch (scanner.nextLine()) {
            case "1":
                ContactsService.addContactToDatabase(contact);
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

                                "\n(¯`·._.· " + contact.getContactName().toUpperCase() + " " + contact.getContactSurname().toUpperCase() + " WS ADDED ·._.·´¯)");
                Ui.showAddOrDeleteUi();
                break;
            case "2":
                System.out.println("Progress has not been saved");
                showUI();
                break;
        }
    }

    //WORKING
    public static void deleteContactUi() throws IOException {
        if (!ContactsService.findContactList().isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter  ID of Contact you want to delete");
            System.out.println("Please Enter integer numbers");
            var customerId = removeAlphabetChars(scanner.nextLine());
            while (customerId.isBlank()) {
                System.out.println("Incorrect data, num ID required to proceed with contact removal.");
                customerId = removeAlphabetChars(scanner.nextLine());
            }
            ContactsService.deleteIdFromList(customerId);
            System.out.println("Contact " + customerId + " Deleted!");
        } else {
            System.out.println("Contact List is empty");
        }
        showAddOrDeleteUi();
    }
}
