import java.sql.SQLException;
import java.util.Scanner;

public class ViewClass {
    Scanner input = new Scanner(System.in);
    public void menu() throws SQLException {
        while (true) {
            System.out.println("\n\t\t****** OPTIONS ******\n");
            System.out.println("\t1. Add contact\n\t2. Delete\n\t3. Search\n\t4. Edit\n\t" +
                    "5. View All contacts\n\t6. Add to Favourite\n\t7. Favourites\n\t8. Exit");
            int userOption = 0;
            while (userOption < 1 || userOption > 8) {
                userOption = Validation.validInt();
            }
            switch (userOption) {
                case 1:
                    addContact();
                    break;

                case 2:
                    deleteContact();
                    break;

                case 3:
                    searchContact();
                    break;

                case 4:editContact();
                    break;

                case 5:new ContactDbo().showContactList();
                break;
                case 6:addFavourite();
                break;

                case 7:showFavourites();
                break;
                case 8:System.exit(1);
            }
        }
    }




    public void addContact() throws SQLException {
//        Scanner input = new Scanner(System.in);
        String mobileNumber;
        while(true) {
            System.out.print("\nMobile Number : ");
            mobileNumber = input.next();
            if (Validation.isValidMobileNumber(mobileNumber))
                break;
            else
                System.out.println("Invalid Mobile Number !!!");
        }
        System.out.print("\nName : ");
        String name = input.next();
        String email;
        while(true) {
            System.out.print("\nEmail : ");
            email = input.next();
            if (Validation.isValidEmail(email))
                break;
            else
                System.out.println("Invalid Email !!!");
        }
        System.out.println("\nRelationShip : ");
        System.out.println("\t1. Friend\n\t2. Family\n\t3. CoWorkers");
        int userInput = 0;
        while(userInput<1 || userInput>3){
            userInput = Validation.validInt();
        }
        String relationship;
        if(userInput == 1)
            relationship = "Friend";
        else if(userInput == 2)
            relationship = "Family";
        else
            relationship = "Co Worker";
        String date;
        while(true) {
            System.out.print("\nDOB(dd/MM/yyyy) : ");
            date = input.next();
            if (Validation.isValidDate(date))
                break;
            else
                System.out.println("Invalid DOB !!!");
        }
//        Contact contact = new Contact(mobileNumber,name,email,relationship,date);
//        ContactDbo contactDbo = new ContactDbo();
//        contactDbo.addContact(contact);
    }

    public void deleteContact() throws SQLException {
        ContactDbo contactDbo = new ContactDbo();
        contactDbo.showContactList();
        int id;
        while (true) {
            id = Validation.validInt();
            int rows = contactDbo.deleteContact(id);
            if(rows>1){
                System.out.println("Contact deleted");
                break;
            }
            System.out.println("Contact not found");
        }
    }

    private void searchContact() throws SQLException {
        System.out.println("Search contact : ");
        System.out.println("\t1. By Name\n\t2. By Number");
        int userOption = 0;
        while(userOption<1 || userOption>2){
            userOption = Validation.validInt();
        }
        ContactDbo contactDbo = new ContactDbo();
        if(userOption == 1){
            System.out.print("Name : ");
            String name = input.next();
            String query = "select id,name from contact where name like '%"+name+"%' order by name";
            if(contactDbo.searchContact(name,query)){
                int id = Validation.validInt();
                String query1 ="select * from contact where id = "+id+" and name like '%"+name+"%' order by name" ;
                if(!contactDbo.showContactDetails(query1))
                    System.out.println("Invalid id");
            }
            else
                System.out.println("No contact Found");
        }
        else{
            System.out.print("Mobile Number : ");
            String mobileNumber = input.next();
            String query = "select id,mobileNumber from contact where mobileNumber like '%"+ mobileNumber +"%' order by mobileNumber";
            if(contactDbo.searchContact(mobileNumber,query)){
                int id = Validation.validInt();
                String query1 ="select * from contact where id = "+id+" and mobileNumber like '%"+ mobileNumber +"%' order by mobileNumber" ;
                if(!contactDbo.showContactDetails(query1))
                    System.out.println("invalid id");
            }
            else
                System.out.println("No contact Found");
        }

    }

    public void editContact() throws SQLException {
        System.out.println("Edit contact : ");
        System.out.println("\t1. By Name\n\t2. By Number");
        int userOption = 0;
        while(userOption<1 || userOption>2){
            userOption = Validation.validInt();
        }
        ContactDbo contactDbo = new ContactDbo();
        if(userOption == 1){

//            String query = "select id,newName from contact";
            contactDbo.showContactList();
            int id = Validation.validInt();
            System.out.print("New Name : ");
            String newName = input.next();
//            String query1 ="update contact set name ="+ newName +" where id ="+id;
            if(!contactDbo.editContact(id,newName))
                System.out.println("Invalid id");

//            else
//                System.out.println("No contact Found");
        }
        else{
//            String query = "select id,mobileNumber from contact";
            contactDbo.showContactList();
            int id = Validation.validInt();
            System.out.print("New Mobile Number : ");
            String newName = input.next();
//            String query1 ="update contact set mobileNumber ="+name+" where id ="+id;
            if(!contactDbo.editContact(id,newName))
                System.out.println("Invalid id");
        }
    }

    private void addFavourite() {
        try {
            ContactDbo contactDbo = new ContactDbo();
            contactDbo.showContactList();
            int id = Validation.validInt();
            contactDbo.addToFavourite(id);
            System.out.println("Contact added into favourites successfully ");
        }catch (Exception e){
            System.out.println("This contact is already added into favourites");
        }
    }

    private void showFavourites() throws SQLException {
        ContactDbo contactDbo = new ContactDbo();
        contactDbo.showFavouriteList();
    }
}
