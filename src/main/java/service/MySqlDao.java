package service;

import model.Contact;
import model.ContactModel;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDao extends Component implements IFileManager{

    private static final String user = "root";
    private static final String password = "29021988lv";
    static String dataBaseName = "contact_manager";
    static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/" + dataBaseName + "?autoReconnect=true&useSSL=false";

    Connection mySqlConnection = null;
    PreparedStatement pst = null;
    ResultSet dataSet = null;



    @Override
    public void addNew(Contact c) {
        try{
            Class.forName(driver);
            String queryContact = "SELECT * FROM contact";
            Connection mySqlConnection = DriverManager.getConnection(url, user, password);
            mySqlConnection.prepareStatement(queryContact);
            PreparedStatement pstContact;
            pstContact = mySqlConnection.prepareStatement("INSERT INTO contact(name,surname,age)values(?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pstContact.setString(1, c.getContactName());
            pstContact.setString(2, c.getContactSurname());
            pstContact.setString(3, c.getContactAge());

            pstContact.executeUpdate();
            System.out.println("Contact Was added to Database.");

            ResultSet rs = pstContact.getGeneratedKeys();
            int contactId = 0;
            if (rs.next()) {
                contactId = rs.getInt(1);
            }

            String queryAddress = ("SELECT * FROM address");
            mySqlConnection.prepareStatement(queryAddress);
            PreparedStatement pstAddress;
            pstAddress = mySqlConnection.prepareStatement("INSERT INTO address(street_name,house_number,postcode,city,contact_id)values(?,?,?,?,?);");
            pstAddress.setString(1, c.getContactAddress().getStreetName());
            pstAddress.setString(2, c.getContactAddress().getHouseNumber());
            pstAddress.setString(3, c.getContactAddress().getPostCode());
            pstAddress.setString(4, c.getContactAddress().getCity());
            pstAddress.setInt(5,contactId);

            pstAddress.executeUpdate();
            System.out.println("Address Was added to Contact in Database.");

            String queryNumbers = ("SELECT * FROM numbers");
            mySqlConnection.prepareStatement(queryNumbers);
            PreparedStatement pstNumbers;
            pstNumbers = mySqlConnection.prepareStatement("INSERT INTO numbers(house_phone,mobile_number,contact_id)values(?,?,?);");
            pstNumbers.setString(1, c.getContactPhoneNumbers().getHousePhone());
            pstNumbers.setString(2, c.getContactPhoneNumbers().getMobilePhone());
            pstNumbers.setInt(3,contactId);

            pstNumbers.executeUpdate();
            System.out.println("Phone Numbers ware added to Contact in Database.");

        } catch (SQLException sq) {
            System.out.println("Database error!");
            sq.printStackTrace();
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver Error!");
        } finally {
            try {
                if (dataSet != null) {
                    dataSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (mySqlConnection != null) {
                    mySqlConnection.close();
                }
            } catch (SQLException sq) {
                sq.printStackTrace();
            }
        }
    }

    @Override
    public void saveAll(ContactModel cM) {

    }

    @Override
    public ContactModel readFile() {
        return null;
    }

    @Override
    public Integer findId() {
        return null;
    }

    @Override
    public List<Contact> findAllContacts() {
        try{
            Class.forName(driver);

            String query = "SELECT contact.c_id, contact.name, contact.surname, contact.age, address.street_name, address.house_number, address.postcode, address.city, numbers.house_phone, numbers.mobile_number\n" +
                    "FROM contact\n" +
                    "INNER JOIN address ON contact.c_id=address.contact_id\n" +
                    "INNER JOIN numbers ON contact.c_id=numbers.contact_id";

            //Create Connection using the DB url, password of server, Username of server
            Connection mySqlConnection = DriverManager.getConnection(url, user, password);
            //Create query to speak with DB
            PreparedStatement pstContact = mySqlConnection.prepareStatement(query);

            ResultSet dataSet = pstContact.executeQuery();

            while (dataSet.next()) {
                System.out.println("----\n유 \n ID: " + dataSet.getInt("c_id") + " \n Name: " + dataSet.getString("name") + " \n  Surname: "+ dataSet.getString("surname") + " \n  Age: " + dataSet.getInt("age") + " \n    " +
                        "╦╣ Address: \n" +
                        "      - Street: " + dataSet.getString("street_name")+ " \n      - House Number: " + dataSet.getString("house_number") + " \n      - Post Code: " +dataSet.getString("postcode") + " \n      - City: " + dataSet.getString("city") + " \n     " +
                        "☏ Phone Numbers:\n " +
                        "        - Land Line Number: " + dataSet.getString("house_number") + " \n         - Mobile: " + dataSet.getString("mobile_number") + "     ");

//                System.out.println("----\n유 \n ID: " + dataSet.getInt("c_id") + " \n Name: " + dataSet.getString("name") + " \n Surname: " + dataSet.getString("surname") + " \n Age: " + dataSet.getInt("age") + " \n Street: " + dataSet.getString("street_name") + " ");
            }

        } catch (SQLException sq) {
            System.out.println("Database error!");
            sq.printStackTrace();
        } catch (ClassNotFoundException ce) {
            System.out.println("Driver Error!");
        } finally {
            try {
                if (dataSet != null) {
                    dataSet.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (mySqlConnection != null) {
                    mySqlConnection.close();
                }
            } catch (SQLException sq) {
                sq.printStackTrace();
            }
        }


        return new ArrayList<>();
    }
}
