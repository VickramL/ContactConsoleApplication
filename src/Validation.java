import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static int validInt(){
        int number = 0;
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("\nEnter Input : ");
            number = input.nextInt();

        }
        catch (Exception e){
            System.out.println("Invalid Input : ");
        }
        return number;
    }
    public static boolean isValidMobileNumber(String mobileNumber){
//        String regex = "/^(\\+\\d{1,3}[- ]?)?\\d{10}$/";
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher m = pattern.matcher(mobileNumber);
        return m.matches();
    }

    public static boolean isValidEmail(String email){
//        String regex = "/^(\\+\\d{1,3}[- ]?)?\\d{10}$/";
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    public static boolean isValidDate(String date)
    {
        String regex = "^(3[01]"
                + "|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence)date);
        return matcher.matches();
    }

}
