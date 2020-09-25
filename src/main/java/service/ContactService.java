package service;

import com.fasterxml.jackson.databind.JsonNode;
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
//    public void setFileManager(String i){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("1 = Json Program \n 2 = Exit");
//        switch (scanner.nextLine()) {
//            case "1":
//                ifm = new JsonContactDao();
//
//                break;
//            case "2":
//                System.out.println("Nothing");
//                System.exit(0);
//                break;
//
//        }
//    }


    // TODO: 25/09/2020
    public void setInterfaceType(int i) {
        switch (i) {
            case 1:
                ifm = new JsonContactDao();
                break;
            case 2:
                System.out.println("Nothing");
                System.exit(0);
                break;
        }
    }

    //Adds contact from Ui to database
    //DONE!
//    public static void addContactToDatabase(Contact contact) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        //use interface DAO instead of static
//        ifm.getDao();
//        ContactModel newContact = ifm.read();
//        //ContactModel newContact = XmlContactDao.convertJavaObjectToXml();
//        newContact.addToContactList(contact);
//        JsonContactDao.writeToJson(newContact, mapper);
//    }
    public void addContact(Contact contact) throws IOException {
        ContactModel newContact = ifm.readFile();
        newContact.addToContactList(contact);
        ifm.addNew(contact);
    }

    public String findCurrentContactId() throws IOException {
        return ifm.findId();
    }

    public JsonNode findContactList() throws IOException {
        return ifm.getFileData().get("contactList");
    }

    public void deleteIdFromList(String customerId) throws IOException {
        var contactList = deleteContactId(customerId);
        ContactModel newContact = ifm.readFile();
        newContact.setContactList(contactList);
        ifm.remove(newContact);
    }

    public List<Contact> deleteContactId(String customerId) {
        List<Contact> contactList = new ArrayList<>();
        JsonContactDao jsonContactDao = JsonContactDao.getDao();
        for (Contact contact : jsonContactDao.readFile().getContactList()) {
            if (!contact.getContactId().equals(customerId)) {
                contactList.add(contact);
            }
        }
        return contactList;
    }


}

