package service;

import model.Contact;
import model.ContactModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlContactDao {
    public static final String CONTACTS_JSON = "C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\contactList_jaxb.xml";

    static ContactModel convertJavaObjectToXml(ContactModel contactModel){
        try {
            JAXBContext writeXmlContent = JAXBContext.newInstance(Contact.class);
            Marshaller m = writeXmlContent.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(contactModel, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


}
