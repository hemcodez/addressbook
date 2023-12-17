import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements DAO {

    // -- Method to hydrate (populate) the ContactPerson object
    private ContactPerson createContactPerson(ResultSet rs) {


        // -- Create a new contact person object
        ContactPerson p = new ContactPerson();

        // -- Set the attributes of the contact person object using the data from the database
        try {
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setNickName(rs.getString("nick_name"));
            p.setAddress(rs.getString("address"));
            p.setHomePhone(rs.getString("home_phone"));
            p.setWorkPhone(rs.getString("work_phone"));
            p.setCellphone(rs.getString("cell_phone"));
            p.setEmail(rs.getString("email"));
            p.setBirthDate(rs.getDate("birthday"));
            p.setWebSite(rs.getString("web_site"));
            p.setProfession(rs.getString("profession"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        } //END TRY CATCH

        // -- Return the ContactPerson
        return p;

    } //END METHOD

    // -- This method gets a list of contacts from the database
    public List<ContactPerson> getContacts() {
        // -- Select query to retrieve records from the database
        String sql = "SELECT * FROM contact ORDER BY name;";

        // -- Create a lust of contacts
        List<ContactPerson> list = new ArrayList<>();

        try {
            // -- Connect to the database

            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

            // -- Create the SQL statement
            Statement stmt = con.createStatement();

            // -- Execute the SQL SELECT query and get a result set containing the records from the database
            ResultSet rs = stmt.executeQuery(sql);

            // -- Loop through the resultset
            while (rs.next()) {

                // -- Create a contactperson from the current record in the resultset
                ContactPerson p = createContactPerson(rs);

                // -- Add the contact person to the list of contactpersons
                list.add(p);
            } //END WHILE

            // -- Close the resultset and the database connection
            rs.close();
            con.close();

            // Catch any exceptions and print the stack trace
        } catch (SQLException ex) {
            ex.printStackTrace();
        } //END TRY CATCH


        // -- return the list of contactpersons
        return list;

    } //END METHOD


    // -- This method returns a list of contacts with a specified name
    public List<ContactPerson> getContactsForName(String name) {
        // -- Select statement to retrieve contacts from the database with the given name
        String sql = "SELECT * FROM contact where name like '%" + name + "%'";

        // -- Create a new list to hold the contacts
        List<ContactPerson> list = new ArrayList<>();

        try{

            // -- Connect to the database
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

            // -- Create the SQL statement
            Statement stmt = con.createStatement();

            // -- Execute the SQL statement to retrieve the records from the database
            ResultSet rs = stmt.executeQuery(sql);

            // -- Loop through the resutset containing the records
            while(rs.next()) {
                // -- Create a new contactperson from the current record and add it to the list of contactpersons
                ContactPerson p = createContactPerson(rs);
                list.add(p);
            } //END WHIE

            // -- Close the resultset and the database connection
            rs.close();
            con.close();

            // -- Catch any exceptions and print the stack trace
        } catch(SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } //END TRY CATCH

        // -- return the list of contact persons
        return list;

    } //END METHOD


} //END CLASS




