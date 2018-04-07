package MyMusic.Control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Retrieve {

    Connection C;
    Statement S;
    ResultSet RS;
    BufferedImage image;
    String sql;

    ArrayList<Audio> playlist;

    public Retrieve() {
        playlist = new ArrayList<>();
        C = OpenConnection.getConnection();
    }

    public ArrayList getList() {
        sql = "select * from songs s, album a, artist ar, labels l, genre g where "
                + "g.genreid = s.genreid and "
                + "l.labelid = ar.labelid and "
                + "ar.artistid = a.artistid and "
                + "a.albumid = s.albumid "
                + "order by s.musicid asc";
        try {
            S = C.createStatement();
            RS = S.executeQuery(sql);
            while (RS.next()) {
                playlist.add(retrieveMusic(RS, new Audio()));
            }
            RS.close();
            S.close();
        } catch (SQLException e) {
            e.toString();
        }
        return playlist;
    }

    //experimental overloading
    public ArrayList getList(String param) {
        sql = "select * from songs s, album a, artist ar, labels l, genre g where "
                + "s.title like '" + param + "%' and "
                + "g.genreid = s.genreid and "
                + "l.labelid = ar.labelid and "
                + "ar.artistid = a.artistid and "
                + "a.albumid = s.albumid "
                + "order by s.musicid asc";
        try {
            S = C.createStatement();
            RS = S.executeQuery(sql);
            while (RS.next()) {
                playlist.add(retrieveMusic(RS, new Audio()));
            }
            RS.close();
            S.close();
        } catch (SQLException e) {
            e.toString();
        }
        return playlist;
    }

    public ArrayList getList(Session session, boolean all) {
        sql = "select * from offline_songs o, account a where "
                + "a.userid = '" + session.getUsername() + "' and "
                + "a.userid = o.userid "
                + "order by o.utrack";
        try {
            S = C.createStatement();
            RS = S.executeQuery(sql);
            while (RS.next()) {
                playlist.add(retrieveUserLibrary(RS, new Audio()));
            }
            RS.close();
            S.close();
            if (all) {
                getPurchased(session);
            }
        } catch (SQLException e) {
            e.toString();
        }
        return playlist;
    }

    public void getPurchased(Session session) {
        sql = "select * from purchase p, purchased pr, songs s, album a, artist ar, labels l, genre g where "
                + "p.userid = '" + session.getUsername() + "' and "
                + "p.transid = pr.transid and "
                + "pr.musicid = s.musicid and "
                + "g.genreid = s.genreid and "
                + "l.labelid = ar.labelid and "
                + "ar.artistid = a.artistid and "
                + "a.albumid = s.albumid "
                + "order by s.musicid asc";
        try {
            S = C.createStatement();
            RS = S.executeQuery(sql);
            while (RS.next()) {
                playlist.add(retrieveMusic(RS, new Audio()));
            }
            RS.close();
            S.close();
        } catch (SQLException e) {
            e.toString();
        }
    }

    public Audio retrieveMusic(ResultSet RS, Audio music) {
        try {
            music.setID(RS.getString("s.musicid"));
            music.setTitle(RS.getString("s.title"));
            music.setArtist(RS.getString("ar.artist"));
            music.setAlbum(RS.getString("a.albumname"));
            music.setLabel(RS.getString("l.label"));
            music.setGenre(RS.getString("g.genretype"));
            music.setComposer(RS.getString("s.composer"));
            music.setTrack(RS.getInt("s.track"));
            music.setYear(RS.getInt("s.year"));
            music.setDescription(RS.getString("a.albuminfo"));
            music.setAvailabilty("ONLINE");//music.setAvailabilty(RS.getString(""));//edit
            music.setOwner(RS.getString("s.copyright"));
            music.setPath(RS.getString("s.pathlocation"));
            music.setArtwork(ImageIO.read(RS.getBinaryStream("s.artwork")));
        } catch (SQLException | IOException e) {
            e.toString();
        }
        return music;
    }

    public Audio retrieveUserLibrary(ResultSet RS, Audio music) {
        try {
            music.setID(RS.getString("o.umusicid"));
            music.setTitle(RS.getString("o.utitle"));
            music.setArtist(RS.getString("o.uartist"));
            music.setAlbum(RS.getString("o.ualbum"));
            music.setLabel(RS.getString("o.ulabel"));
            music.setGenre(RS.getString("o.ugenre"));
            music.setComposer(RS.getString("o.ucomposer"));
            music.setTrack(RS.getInt("o.utrack"));
            music.setYear(RS.getInt("o.uyear"));
            music.setAvailabilty("OFFLINE");
            music.setOwner(RS.getString("o.copyright"));
            music.setPath(RS.getString("o.upathlocation"));
            music.setDescription(RS.getString("o.unote"));
            music.setLyric(RS.getString("o.ulyric"));
            music.setArtwork(ImageIO.read(RS.getBinaryStream("o.uartwork")));
        } catch (SQLException | IOException e) {
            e.toString();
        }
        return music;
    }
}
