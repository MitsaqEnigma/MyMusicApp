package MyMusic.Control;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class OpenConnection {

    static private Connection koneksi;

    public static Connection getConnection() {
        if (koneksi == null) {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mymusicinfo", "root", "");
                /*JOptionPane.showMessageDialog(null, "url: jdbc:mysql://localhost:3306/buku_sehat\n"
                        + "user: root", "Connection Status", 1);*/
            } catch (Exception e) {
                koneksi = null;
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage() + "\nPlease check the Connection", "ERROR", 0);
            }
        }
        return koneksi;
    }
}
