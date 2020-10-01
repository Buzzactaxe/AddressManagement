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
                ifm = new XmlDao();
                break;
            case 3:
                System.out.println("Nothing, your out");
                System.exit(0);
                break;
            default:
                System.exit(0);
        }
    }


    public void addContact(Contact contact) throws IOException {
        ifm.addNew(contact);
    }

    public String getCurrentContactId() throws IOException {
        return ifm.findId();
    }

    public List<Contact> getContactList() {
        return ifm.findAllContacts();
    }

    public List<Contact> getContactAgeList() {

        return ifm.findContactsAge();
    }

    public void deleteIdFromList(String customerId) throws IOException {
        var contactList = deleteContactId(customerId);
        ContactModel newContact = ifm.readFile();
        newContact.setContactList(contactList);
        ifm.deleteContact(newContact);
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

