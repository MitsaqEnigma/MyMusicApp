package MyMusic.Control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Login {

    Connection C;
    Statement S;
    ResultSet RS;

    Session session;

    public Login() {
        C = OpenConnection.getConnection();
    }

    public Session Login(String username, String password) {
        String sql = "select * from account where userid='" + username + "' ";
        try {
            S = C.createStatement();
            RS = S.executeQuery(sql);
            if (RS.next()) {
                if (RS.getString("password").equals(password)) {
                    session = new Session();
                    session.setUsername(username);
                    session.setPassword(password);
                    session.setName(RS.getString("fname") + " " + RS.getString("lname"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please check your credentials", "Login Problem", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User ID not found", "Login Problem", 0);
            }
            RS.close();
            S.close();
        } catch (SQLException e) {
            e.toString();
        }
        return session;
    }
}
