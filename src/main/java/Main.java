import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(final Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/vladimir/workspace/coursework/receptionDep")) {
            if (connection.isValid(1)) {
                System.out.println("Connected successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
