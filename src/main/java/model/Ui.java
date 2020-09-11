package model;

import org.codehaus.plexus.util.StringUtils;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static service.ContactsService.*;

public class Ui {

    public static void showMainMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            //Automatically capitalises first Letter
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
                    addContact();
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

    public static void showExit(Scanner s) throws NoSuchElementException {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDo you want to go back to the main menu?\n1 = [ Main Menu ] 2 = [ Exits Program ] ");
        String inputUser = input.nextLine().toUpperCase();
        switch (inputUser){
            case "1":
                showUI();
                break;
            case "2":
                System.out.println("༼ つ ◕_◕ ༽つ You are out of the project, Goodbye!!");
                System.exit(0);
                break;
            default:
                throw new NoSuchElementException("Unexpected value: " + s.nextLine()
                );
        }
    }

    public static void showUI() {
        System.out.println("   (-(-_(-_-)_-)-)");
        String intro = "-- Contact Manager -- \n" + "1 = [ All Contacts ]\n" + "2 = [ Contacts Age ]\n" + "Exit = [ Exit ] \n" + "\n4 = [ Add Contact ] \n" + "5 = [ Delete Contact ]";
        System.out.println(intro);
        Ui.showMainMenu();
    }

}
