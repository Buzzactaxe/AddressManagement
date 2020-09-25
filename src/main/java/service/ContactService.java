package service;

import model.Contact;
import model.ContactModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactService extends RemoveNonChar {
    //Giving null will not load until it is needed
    private static ContactService instance = null;
    private IFileManager ifm;

    private ContactService() {
    }

    public static ContactService getInstance() {
        if (instance == null) instance = new ContactService();
        return instance;
    }

    public void setInterfaceType(int i) {
        switch (i) {
            case 1:
                ifm = new JsonDao();
                break;
            case 2:
                // TODO Add xml when implemented
                System.out.println("Program in XL will come here");
                break;
            case 3:
                System.out.println("Nothing, your out");
                System.exit(0);
                break;
            default:
                System.exit(0);
        }
    }

    //Adds contact from Ui to database
    public void addContact(Contact contact) throws IOException {
        ContactModel newContact = ifm.readFile();
        newContact.addToContactList(contact);
        ifm.addNew(contact);
    }

    public String findCurrentContactId() throws IOException {
        return ifm.findId();
    }

    public void getContactList() {
        ifm.getAllContacts();
    }

    public void showContactAgeList() {
        ifm.getAllContactAge();
    }

    public void deleteIdFromList(String customerId) throws IOException {
        var contactList = deleteContactId(customerId);
        ContactModel newContact = ifm.readFile();
        newContact.setContactList(contactList);
        ifm.remove(newContact);
    }

    public List<Contact> deleteContactId(String customerId) {
        List<Contact> contactList = new ArrayList<>();
        for (Contact contact : ifm.readFile().getContactList()) {
            if (!contact.getContactId().equals(customerId)) {
                contactList.add(contact);
            }
        }
        return contactList;
    }


}

