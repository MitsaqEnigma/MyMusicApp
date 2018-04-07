package MyMusic.Frame;

import MyMusic.Control.OpenConnection;
import MyMusic.Control.Audio;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Metafile extends javax.swing.JDialog {

    private Image img;
    private File imgPath;
    private boolean insert;
    private FileNameExtensionFilter fileExtension;

    private Audio music;

    private final Connection C = OpenConnection.getConnection();
    private PreparedStatement PS;

    public Metafile(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBtnIco();
    }

    public Metafile(java.awt.Frame parent, boolean modal, Audio music) {
        super(parent, modal);
        initComponents();
        setBtnIco();
        Cover.setText(null);
        this.music = music;
        path.setText(music.getPath());
        title.setText(music.getTitle());
        artist.setText(music.getArtist());
        album.setText(music.getAlbum());
        track.setText(Integer.toString(music.getTrack()));
        year.setText(Integer.toString(music.getYear()));
        composer.setText(music.getComposer());
        label.setText(music.getLabel());
        genre.setSelectedItem(music.getGenre());
        note.setText(music.getDescription());
        if (music.getArtwork() != null) {
            Cover.setIcon(new ImageIcon(music.getArtwork().getScaledInstance(Cover.getWidth(), Cover.getHeight(), Image.SCALE_SMOOTH)));
        }
    }

    public final void setBtnIco() {
        try {
            btnBrowse.setIcon(new javax.swing.ImageIcon(ImageIO.read(getClass().getResource("/MyMusic/Img/001-folder.png")).getScaledInstance(btnBrowse.getWidth(), btnBrowse.getHeight(), Image.SCALE_SMOOTH)));
            this.setIconImage(ImageIO.read(getClass().getResource("/MyMusic/Img/005-compact-disc.png")).getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public boolean isInsert() {
        return insert;
    }

    public void isInsert(boolean insert) {
        this.insert = insert;
    }

    public Audio setMusic(Audio music, String ID) throws FileNotFoundException {
        music.setID(ID);
        music.setTitle(title.getText());
        music.setArtist(artist.getText());
        music.setAlbum(album.getText());
        music.setTrack(Integer.parseInt(track.getText()));
        music.setYear(Integer.parseInt(year.getText()));
        music.setGenre(genre.getSelectedItem().toString());
        music.setComposer(composer.getText());
        music.setLabel(label.getText());
        music.setDescription(note.getText());
        music.setPath(path.getText());
        music.setArtwork(img);
        if (imgPath != null) {
            music.setImageOnBinary(new FileInputStream(imgPath));
            music.setImageSize((int) imgPath.length());
        }
        //add label, change comment to textarea
        return music;
    }

    public void insertMusic() {
        try {
            Add.list.add(setMusic(new Audio(), null));
            int x = JOptionPane.showConfirmDialog(this, "Your music has been saved\nDo you want to add more?", "Information", JOptionPane.YES_NO_OPTION);
            if (x != JOptionPane.YES_OPTION) {
                this.dispose();
            }
        } catch (HeadlessException | FileNotFoundException e2) {
            JOptionPane.showMessageDialog(null, e2.toString(), "E2 ERROR", 0);
        }
    }

    public void updateMusicPre() throws SQLException {
        PS = null;
        try {
            music = setMusic(new Audio(), music.getID());//use return value at setMusic
            try {
                String sql = "update offline_songs set upathlocation=?, utitle=?, uartist=?, "
                        + "ualbum=?, utrack=?, uyear=?, "
                        + "ugenre=?, ucomposer=?, "
                        + "ulabel=?, unote=? "
                        + "where umusicid=? ";
                PS = C.prepareStatement(sql);
                PS.setString(1, music.getPath());
                PS.setString(2, music.getTitle());
                PS.setString(3, music.getArtist());
                PS.setString(4, music.getAlbum());
                PS.setInt(5, music.getTrack());
                PS.setInt(6, music.getYear());
                PS.setString(7, music.getGenre());
                PS.setString(8, music.getComposer());
                PS.setString(9, music.getLabel());
                PS.setString(10, music.getDescription());
                PS.setString(11, music.getID());
                PS.executeUpdate();
                if (imgPath != null) {
                    PS.close();
                    sql = "update offline_songs set uartwork=? where umusicid=?";
                    PS = C.prepareStatement(sql);
                    PS.setBinaryStream(1, music.getImageOnBinary(), music.getImageSize());
                    PS.setString(2, music.getID());
                    PS.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "All Changes has been Saved", "Information", 1);
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", 0);
            } finally {
                PS.close();
            }
        } catch (HeadlessException | FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "ERROR", 0);
        }
    }

    public FileNameExtensionFilter exFilter(int extension) {
        this.myFilePicker.removeChoosableFileFilter(fileExtension);
        if (extension == 1) {
            fileExtension = new FileNameExtensionFilter("waveform audio format | *.wav;*.wave;", "wav", "wave");
        } else {
            fileExtension = new FileNameExtensionFilter("image file | *.jpg;*.jpeg;*.png;*.bmp;*.gif;", "jpg", "jpeg", "png", "bmp", "gif");
        }
        return fileExtension;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myFilePicker = new javax.swing.JFileChooser();
        title = new javax.swing.JTextField();
        artist = new javax.swing.JTextField();
        album = new javax.swing.JTextField();
        track = new javax.swing.JTextField();
        year = new javax.swing.JTextField();
        genre = new javax.swing.JComboBox<>();
        note = new javax.swing.JTextField();
        btnDone = new javax.swing.JButton();
        Cover = new javax.swing.JLabel();
        btnBrowse = new javax.swing.JLabel();
        path = new javax.swing.JTextField();
        composer = new javax.swing.JTextField();
        label = new javax.swing.JTextField();
        addLyric = new javax.swing.JLabel();

        myFilePicker.setName(""); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add or Edit Music");
        setResizable(false);

        title.setEditable(false);
        title.setText("Title");
        title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleMouseClicked(evt);
            }
        });

        artist.setEditable(false);
        artist.setText("Artist");
        artist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                artistMouseClicked(evt);
            }
        });

        album.setEditable(false);
        album.setText("Album");
        album.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumMouseClicked(evt);
            }
        });

        track.setEditable(false);
        track.setText("Track");
        track.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trackMouseClicked(evt);
            }
        });

        year.setEditable(false);
        year.setText("Year");
        year.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yearMouseClicked(evt);
            }
        });

        genre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genre", "Pop", "Rock", "Jazz", "Metal", "Blues", "Regae", "Electro", "HipHop", "Classic", "Keroncong" }));
        genre.setEnabled(false);
        genre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genreMouseClicked(evt);
            }
        });

        note.setEditable(false);
        note.setText("Note");
        note.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noteMouseClicked(evt);
            }
        });

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        Cover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cover.setText("Artwork (Click to add)");
        Cover.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 255), null));
        Cover.setFocusable(false);
        Cover.setRequestFocusEnabled(false);
        Cover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CoverMouseClicked(evt);
            }
        });

        btnBrowse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBrowseMouseClicked(evt);
            }
        });

        path.setEditable(false);
        path.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        path.setText("/path");
        path.setMaximumSize(new java.awt.Dimension(30, 19));

        composer.setEditable(false);
        composer.setText("Composer");
        composer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                composerMouseClicked(evt);
            }
        });

        label.setEditable(false);
        label.setText("Label");
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMouseClicked(evt);
            }
        });

        addLyric.setText("Add Lyric");
        addLyric.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addLyric.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addLyricMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(Cover, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(artist, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(album, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(track, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(composer, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addLyric)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDone))
                            .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Cover, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(artist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(track, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(composer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(note, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDone)
                    .addComponent(addLyric))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void titleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleMouseClicked
        title.setEditable(true);
        title.setText(null);
    }//GEN-LAST:event_titleMouseClicked

    private void artistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistMouseClicked
        artist.setEditable(true);
        artist.setText(null);
    }//GEN-LAST:event_artistMouseClicked

    private void albumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumMouseClicked
        album.setEditable(true);
        album.setText(null);
    }//GEN-LAST:event_albumMouseClicked

    private void trackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trackMouseClicked
        track.setEditable(true);
        track.setText(null);
    }//GEN-LAST:event_trackMouseClicked

    private void yearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearMouseClicked
        year.setEditable(true);
        year.setText(null);
    }//GEN-LAST:event_yearMouseClicked

    private void genreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genreMouseClicked
        genre.setEnabled(true);
    }//GEN-LAST:event_genreMouseClicked

    private void noteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noteMouseClicked
        note.setEditable(true);
        note.setText(null);
    }//GEN-LAST:event_noteMouseClicked

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        try {
            if (isInsert()) {
                insertMusic();
            } else {
                updateMusicPre();
            }
        } catch (SQLException e) {
            e.toString();
        }
    }//GEN-LAST:event_btnDoneActionPerformed

    private void CoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CoverMouseClicked
        this.myFilePicker.setFileFilter(exFilter(2));
        if (myFilePicker.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Cover.setText(null);
                imgPath = new File(myFilePicker.getSelectedFile().getAbsolutePath());
                img = ImageIO.read(imgPath).getScaledInstance(Cover.getWidth(), Cover.getHeight(), Image.SCALE_SMOOTH);
                Cover.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR : " + e.toString(), "", 0);
            }
        }
    }//GEN-LAST:event_CoverMouseClicked

    private void btnBrowseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseClicked
        this.myFilePicker.setFileFilter(exFilter(1));
        if (myFilePicker.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path.setText(myFilePicker.getSelectedFile().getAbsolutePath());
            path.setToolTipText(path.getText());
        }
    }//GEN-LAST:event_btnBrowseMouseClicked

    private void composerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_composerMouseClicked
        composer.setEditable(true);
        composer.setText(null);
    }//GEN-LAST:event_composerMouseClicked

    private void labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMouseClicked
        label.setEditable(true);
        label.setText(null);
    }//GEN-LAST:event_labelMouseClicked

    private void addLyricMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addLyricMouseClicked
        
    }//GEN-LAST:event_addLyricMouseClicked

    public static void main(String args[]) {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Metafile dialog = new Metafile(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cover;
    private javax.swing.JLabel addLyric;
    private javax.swing.JTextField album;
    private javax.swing.JTextField artist;
    private javax.swing.JLabel btnBrowse;
    private javax.swing.JButton btnDone;
    private javax.swing.JTextField composer;
    private javax.swing.JComboBox<String> genre;
    private javax.swing.JTextField label;
    private javax.swing.JFileChooser myFilePicker;
    private javax.swing.JTextField note;
    private javax.swing.JTextField path;
    private javax.swing.JTextField title;
    private javax.swing.JTextField track;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables
}
