package MyMusic.Frame;

import MyMusic.Control.Audio;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Info extends javax.swing.JDialog {
    
    Lyrics lyric;

    public Info(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Info(java.awt.Frame parent, boolean modal, Audio music) {
        super(parent, modal);
        initComponents();
        setInfo(music);
    }

    public final void setInfo(Audio music) {
        artwork.setIcon(new ImageIcon(music.getArtwork().getScaledInstance(134, 134, Image.SCALE_SMOOTH)));
        title.setText("~" + music.getTitle());
        artist.setText(music.getArtist());
        album.setText(music.getAlbum());
        genre.setText(music.getGenre());
        composer.setText(music.getComposer());
        track.setText("Track#" + Integer.toString(music.getTrack()));
        year.setText(Integer.toString(music.getYear()));
        copyright.setText(music.getOwner());
        label.setText(music.getLabel());
        information.setText(music.getDescription());
        availability.setText(music.getAvailabilty());
        lyric = new Lyrics(null, rootPaneCheckingEnabled, music);
        //
        this.setTitle(music.getTitle() + " by " + music.getArtist());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        artwork = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        artist = new javax.swing.JLabel();
        album = new javax.swing.JLabel();
        genre = new javax.swing.JLabel();
        composer = new javax.swing.JLabel();
        track = new javax.swing.JLabel();
        year = new javax.swing.JLabel();
        copyright = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        information = new javax.swing.JTextArea();
        availability = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        title.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        title.setText("Title");

        artist.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        artist.setText("Artist");

        album.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        album.setText("Album");

        genre.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        genre.setText("Genre");

        composer.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        composer.setText("Composer");

        track.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        track.setText("Track");

        year.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        year.setText("Year");

        copyright.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        copyright.setText("Copyright");

        label.setFont(new java.awt.Font("Segoe UI Symbol", 0, 11)); // NOI18N
        label.setText("Label");

        information.setEditable(false);
        information.setColumns(20);
        information.setLineWrap(true);
        information.setRows(5);
        information.setWrapStyleWord(true);
        jScrollPane1.setViewportView(information);

        availability.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        availability.setText("Availability");

        jLabel1.setText("Lyrics >");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(artwork, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(artist, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(album, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(composer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(track, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(copyright, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(availability)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artist)
                            .addComponent(album))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genre)
                            .addComponent(composer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(track)
                            .addComponent(year))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(copyright)
                            .addComponent(label)))
                    .addComponent(artwork, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availability)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        lyric.setLocationRelativeTo(this);
        lyric.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Info dialog = new Info(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel album;
    private javax.swing.JLabel artist;
    private javax.swing.JLabel artwork;
    private javax.swing.JLabel availability;
    private javax.swing.JLabel composer;
    private javax.swing.JLabel copyright;
    private javax.swing.JLabel genre;
    private javax.swing.JTextArea information;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel title;
    private javax.swing.JLabel track;
    private javax.swing.JLabel year;
    // End of variables declaration//GEN-END:variables
}
