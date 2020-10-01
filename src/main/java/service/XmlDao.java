package service;

import model.Contact;
import model.ContactModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class XmlDao implements IFileManager {

    private static XmlDao instance = null;

    XmlDao() {

    }

    File JAXB_XML_FILE = new File("C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\contactList_jaxb.xml");

    @Override
    public void addNew(Contact c) {
        ContactModel contactModel = readFile();
        contactModel.addToContactList(c);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactModel.class);
            Marshaller m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(contactModel, new FileOutputStream(JAXB_XML_FILE));

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static XmlDao getDao() {
        if (instance == null) instance = new XmlDao();
        return instance;
    }

    @Override
    public void deleteContact(ContactModel c) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactModel.class);
            Marshaller m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(c, new FileOutputStream(JAXB_XML_FILE));

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //writes to XML file
    public ContactModel readFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactModel.class);
            Unmarshaller unmarshallFile = jaxbContext.createUnmarshaller();
            return (ContactModel) unmarshallFile.unmarshal(JAXB_XML_FILE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String findId() {
        int contactId = readFile().getContactList().size();
        return Integer.toString(contactId);
    }

    @Override
    public List<Contact> findAllContacts() {
        return readFile().getContactList();
    }

    @Override
    public List<Contact> findContactsAge() {

        return readFile().getContactList();
    }
}
