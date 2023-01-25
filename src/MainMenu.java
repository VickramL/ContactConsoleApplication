import java.sql.SQLException;

public class MainMenu {
    public void showMenu() throws SQLException {
        ViewClass viewClass = new ViewClass();
        viewClass.menu();
    }
}
