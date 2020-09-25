package service;

import com.fasterxml.jackson.databind.JsonNode;
import model.Contact;
import model.ContactModel;

import java.io.IOException;

public interface IFileManager {

    void addNew(Contact contact) throws IOException;
    void remove(ContactModel contactModel) throws IOException;
    ContactModel readFile();
    JsonNode getFileData() throws IOException;
    String findId() throws IOException;


}
