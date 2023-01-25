public class Main {
    public static void main(String[] args) {
        try {
//            MainMenu mainMenu = new MainMenu();
//            mainMenu.showMenu();
            ViewClass viewClass = new ViewClass();
            viewClass.menu();

        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
