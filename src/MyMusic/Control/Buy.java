package MyMusic.Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class Buy {

    Connection C;
    Statement S;
    PreparedStatement PS;
    ResultSet RS;
    String sql;

//    Audio music;
//    Session session;

    public Buy() {
        C = OpenConnection.getConnection();
        PS = null;
    }

    public void buyMusic(Session session, Audio music) {
        int transid = getID();
        sql = "insert into purchase values (?,?,?)";
        try {
            PS = C.prepareStatement(sql);
            PS.setString(1, Integer.toString(transid));
            PS.setString(2, session.getUsername());
            PS.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            PS.executeUpdate();
            PS.close();
            transac(transid, music);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void transac(int transid, Audio music) {
        sql = "insert into purchased values (?,?)";
        try {
            PS = C.prepareStatement(sql);
            PS.setString(1, music.getID());
            PS.setString(2, Integer.toString(transid));
            PS.executeUpdate();
            PS.close();
            JOptionPane.showMessageDialog(null, music.getTitle() + " Purchased", "Information", 1);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public final int getID() {
        int id = 0;
        try {
            S = C.createStatement();
            String sql_id = "select transid from purchase order by transid asc";
            RS = S.executeQuery(sql_id);
            while (RS.next()) {
                id = Integer.parseInt(RS.getString("transid")) + 1;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
}
