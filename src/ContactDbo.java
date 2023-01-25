import java.sql.*;

public class ContactDbo {
    public boolean showContactDetails(String query) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        boolean flag = false;
        while(resultSet.next()){
            flag = true;
            System.out.println("\n\n---------------------------------------");
            System.out.println("\t\t\t"+resultSet.getString(2));
            System.out.println("---------------------------------------");
            System.out.printf("%-15s%s%s\n","Mobile number",": ", resultSet.getString(1));
            System.out.printf("%-15s%s%s\n","Email",": ", resultSet.getString(3));
            System.out.printf("%-15s%s%s\n","RelationShip",": ", resultSet.getString(4));
            System.out.printf("%-15s%s%s\n","DOB",": ", resultSet.getString(5));
        }
        return flag;
    }

    public void addContact(Contact contact) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "insert into contact(mobileNumber, name,email,relationship,DOB) values(?,?,?,?,?)";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1,contact.getMobileNumber());
        pst.setString(2,contact.getName());
        pst.setString(3,contact.getEmail());
        pst.setString(4,contact.getRelationShip());
        pst.setString(5,contact.getDOB());
        int rows = pst.executeUpdate();
        System.out.println(rows+" rows Affected");
    }

    public void showContactList() throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "select id,name,mobileNumber from contact";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("\n\t\t****** CONTACTS *******\n");
        System.out.println("-------------------------------------");
        System.out.printf("|%-4s| %-15s| %-12s|\n"," id","Name","Phone");
        System.out.println("-------------------------------------");
        while(resultSet.next()){
            System.out.printf("| %-3d| %-15s| %-12s|\n",resultSet.getInt(1),
                    resultSet.getString(2),resultSet.getString(3));
        }
        System.out.println("-------------------------------------");
    }

    public int deleteContact(int id) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "delete from contact where id = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1,id);
        return pst.executeUpdate();
    }

    public boolean searchContact(String name,String query) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("\n\t\t****** CONTACTS *******\n");
        System.out.println("-----------------------");
        System.out.printf("|%-4s| %-15s|\n"," id","Name");
        System.out.println("-----------------------");
        boolean flag = false;
        while(resultSet.next()){
            System.out.printf("| %-3d| %-15s|\n",resultSet.getInt(1),
                    resultSet.getString(2));
            flag = true;
        }
        System.out.println("-----------------------");


        return flag;
    }

    public boolean editContact(int id,String newName) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        PreparedStatement pst = connection.prepareStatement("update contact set name = ? where id = ?");
        pst.setString(1,newName);
        pst.setInt(2,id);
        return pst.executeUpdate()>0;
    }
    public void addToFavourite(int id) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "insert into favourites(contact_id) values(?)";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1,id);
        pst.executeUpdate();
    }

    public void showFavouriteList() throws SQLException {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        Statement statement = connection.createStatement();
        String query = "select contact.id,contact.name,contact.mobileNumber from contact inner join favourites on favourites.contact_id = contact.id";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("\n\t\t****** CONTACTS *******\n");
        System.out.println("-------------------------------------");
        System.out.printf("|%-4s| %-15s| %-12s|\n"," id","Name","Phone");
        System.out.println("-------------------------------------");
        while(resultSet.next()){
            System.out.printf("| %-3d| %-15s| %-12s|\n",resultSet.getInt(1),
                    resultSet.getString(2),resultSet.getString(3));
        }
        System.out.println("-------------------------------------");
    }
}
