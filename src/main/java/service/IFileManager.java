package service;

import model.Contact;
import model.ContactModel;

import java.io.IOException;
import java.util.List;

public interface IFileManager {

    void addNew(Contact c) throws IOException;

    void saveAll(ContactModel cM) throws IOException;

    ContactModel readFile();

    String findId() throws IOException;

    List<Contact> findAllContacts();



}
