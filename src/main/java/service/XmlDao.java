package service;

import model.ContactModel;

public class XmlDao{
    ContactModel contactsFromJson;
    public static final String CONTACTS_JSON = "C:\\Users\\ffsamuellupori\\AddressManagement\\src\\main\\resources\\contactList_jaxb.xml";

//    public ContactModel write(ContactModel contactModel){
//        try {
//            JAXBContext writeXmlContent = JAXBContext.newInstance(Contact.class);
//            Marshaller m = writeXmlContent.createMarshaller();
//            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            m.marshal(contactModel, System.out);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
