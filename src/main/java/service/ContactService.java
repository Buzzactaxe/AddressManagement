package service;

import model.Contact;
import model.ContactModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                System.out.println("Your out of the program");
                System.exit(0);
                break;
            default:
                setInterfaceType(3);
        }
    }

    public void addContact(Contact contact) throws IOException {
        ifm.addNew(contact);
    }

    public Integer getCurrentContactId() throws IOException {
        return ifm.findId();
    }

    public List<Contact> getContactList() {
        return ifm.findAllContacts();
    }

    public void deleteIdFromList(String customerId) throws IOException {
        var contactList = deleteContactId(customerId);
        ContactModel newContact = ifm.readFile();
        newContact.setContactList(contactList);
        ifm.saveAll(newContact);
    }

    // Old function also not working well
    public List<Contact> deleteContactId(String customerId) {
        List<Contact> contactList = new ArrayList<>();
        for (Contact contact : ifm.readFile().getContactList()) {
            if (!contact.getContactId().toString().equals(customerId)) {
                contactList.add(contact);
            }
        }
        return contactList;
    }

    public Optional<Contact> findById(String customerId) {
        var contactFound = getContactList();
        for(Contact contact : contactFound){
            if(contact.getContactId().toString().equals(customerId)){
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }


}

